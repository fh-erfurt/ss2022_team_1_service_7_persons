package de.fherfurt.person.core.persistence;

import de.fherfurt.person.core.persistence.errors.MissingPrimaryKeyException;
import de.fherfurt.person.core.persistence.errors.PersistenceException;
import de.fherfurt.person.core.persistence.errors.ToManyPrimaryKeysException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Database {

    private static Database instance;

    private final Map<String, CollectionContainer> cache = new ConcurrentHashMap<>();
    private final Map<String, Set<MapperContainer>> mappers = new ConcurrentHashMap<>();

    public static Database newInstance() {
        if (Objects.isNull(instance)) {
            instance = new Database();
        }

        return instance;
    }

    public <ENTITY> void save(final ENTITY entity) {
        try {
            final CollectionContainer target = Optional.ofNullable(cache.get(entity.getClass().getSimpleName())).orElse(CollectionContainer.of());

            final boolean insert = extractPrimaryKeyValue(entity) < 1;

            if (insert) {
                final Field primaryKey = extractPrimaryKeyField(entity);

                primaryKey.setAccessible(true);
                primaryKey.set(entity, target.count.incrementAndGet());
            } else {
                target.entries.remove(entity);
            }

            target.entries.add(entity);

            cache.put(entity.getClass().getSimpleName(), target);

            executeMappers(entity, insert ? MapperTypes.INSERT : MapperTypes.UPDATE);
        } catch (IllegalAccessException e) {
            throw new PersistenceException(e);
        }
    }

    public <ENTITY> void delete(final ENTITY entity) {
        final CollectionContainer target = Optional.ofNullable(cache.get(entity.getClass().getSimpleName())).orElse(CollectionContainer.of());

        target.entries.remove(entity);

        cache.put(entity.getClass().getSimpleName(), target);

        executeMappers(entity, MapperTypes.DELETE);
    }

    private void executeMappers(Object entity, MapperTypes type) {
        final Optional<List<BiConsumer<Database, Object>>> mapper = Optional.ofNullable(mappers.get(entity.getClass().getSimpleName()))
                .orElse(new HashSet<>())
                .stream()
                .filter(entry -> Objects.equals(entry.type, type))
                .map(entry -> entry.entries)
                .findFirst();

        if (mapper.isPresent()) {
            final Database db = this;
            mapper.get().forEach(entry -> entry.accept(db, entity));
        }
    }

    public <T> Optional<T> findBy(final Class<T> type, final int id) {
        return Optional.ofNullable(cache.get(type.getSimpleName()))
                .flatMap(target -> target.entries.stream().filter(entity -> extractPrimaryKeyValue(entity) == id)
                        .map(type::cast)
                        .findFirst());
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> findBy(final Class<T> type, Predicate<? extends T> predicate) {
        final CollectionContainer collection = cache.get(type.getSimpleName());

        if (Objects.isNull(collection)) {
            return Collections.emptyList();
        }

        return collection.entries.stream().filter((Predicate<? super Object>) predicate)
                .map(type::cast).collect(Collectors.toList());
    }

    private int extractPrimaryKeyValue(final Object entity) {
        try {
            final Field primaryKey = extractPrimaryKeyField(entity);
            primaryKey.setAccessible(true);
            return (int) primaryKey.get(entity);
        } catch (IllegalAccessException e) {
            throw new PersistenceException(e);
        }
    }

    private Field extractPrimaryKeyField(Object entity) {
        final List<Field> primaryKeys = getAllFields(entity.getClass(), field -> field.isAnnotationPresent(Id.class));
        if (primaryKeys.isEmpty()) {
            throw MissingPrimaryKeyException.of(entity.getClass());
        }

        if (primaryKeys.size() > 1) {
            throw ToManyPrimaryKeysException.of(entity.getClass());
        }

        return primaryKeys.get(0);
    }

    private List<Field> getAllFields(final Class<?> clazz, Predicate<Field> filter) {
        if (Objects.isNull(clazz)) {
            return Collections.emptyList();
        }

        List<Field> result = new ArrayList<>(getAllFields(clazz.getSuperclass(), filter));
        List<Field> filteredFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(filter).toList();
        result.addAll(filteredFields);
        return result;
    }

    public void clear() {
        cache.values().forEach(entry -> entry.entries.clear());
        cache.clear();
        mappers.values().forEach(Set::clear);
        mappers.clear();
    }

    public <T> int count(Class<T> type) {
        return Optional.ofNullable(cache.get(type.getSimpleName())).map(entry -> entry.entries.size())
                .orElseThrow(() -> new PersistenceException("Collection doesn't exist for type '" + type.getSimpleName() + "'."));
    }

    public <T> void addInsertMapper(final Class<T> type, final BiConsumer<Database, T> mapper) {
        addMapper(type, MapperTypes.INSERT, mapper);
    }

    public <T> void addUpdateMapper(final Class<T> type, final BiConsumer<Database, T> mapper) {
        addMapper(type, MapperTypes.UPDATE, mapper);
    }

    public <T> void addDeleteMapper(final Class<T> type, final BiConsumer<Database, T> mapper) {
        addMapper(type, MapperTypes.DELETE, mapper);
    }

    @SuppressWarnings("unchecked")
    private <T> void addMapper(final Class<?> type, final MapperTypes mapperType, final BiConsumer<Database, T> mapper) {

        if (!mappers.containsKey(type.getSimpleName())) {
            mappers.put(type.getSimpleName(), new HashSet<>());
        }

        final Set<MapperContainer> mapperStorage = mappers.get(type.getSimpleName());

        final Optional<MapperContainer> loadedContainer = mapperStorage.stream()
                .filter(entry -> Objects.equals(entry.type, mapperType))
                .findFirst();

        MapperContainer used;
        if (loadedContainer.isPresent()) {
            used = loadedContainer.get();
        } else {
            used = MapperContainer.of(mapperType);
            mapperStorage.add(used);
        }

        used.entries.add((BiConsumer<Database, Object>) mapper);
    }

    private static class CollectionContainer {
        final AtomicInteger count = new AtomicInteger(0);
        final Set<Object> entries = new HashSet<>();

        private CollectionContainer() {
        }

        public static CollectionContainer of() {
            return new CollectionContainer();
        }
    }

    private static class MapperContainer {
        final MapperTypes type;
        final List<BiConsumer<Database, Object>> entries = new LinkedList<>();

        private MapperContainer(final MapperTypes type) {
            this.type = type;
        }

        public static MapperContainer of(final MapperTypes type) {
            return new MapperContainer(type);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            MapperContainer that = (MapperContainer) other;
            return type == that.type;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type);
        }
    }

    private enum MapperTypes {
        INSERT, UPDATE, DELETE
    }
}