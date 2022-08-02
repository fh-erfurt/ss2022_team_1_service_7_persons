package de.fheerfurt.person.utils;

import java.util.List;

public class ListUtils {

    public static <T> boolean exists(List<T> list) {
        return list != null && list.size() > 0;
    }

}
