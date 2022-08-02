package de.fheerfurt.person.resources;

import de.fheerfurt.person.utils.ListUtils;
import de.fherfurt.person.person.boundary.PersonResource;
import de.fherfurt.persons.client.objects.PersonDto;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

public class PersonWebResource {
    private final PersonResource personResource;

    public PersonWebResource() {
        this.personResource = PersonResource.of();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons(
            @QueryParam( "facultyId" ) @DefaultValue( "-1" ) int facultyId,
            @QueryParam( "semester" ) @DefaultValue( "-1" ) int semester,
            @QueryParam( "query" ) @DefaultValue( "" ) String query,
            @QueryParam( "skip" ) @DefaultValue( "-1" ) int skip,
            @QueryParam( "take" ) @DefaultValue( "-1" ) int take,
            @QueryParam( "sort_by" ) @DefaultValue( "" ) String sortBy,
            @QueryParam( "order_by" ) @DefaultValue( "" ) String orderBy
    ) {
        List<PersonDto> foundPersons = null;

        // PREVENT SQL INJECTION

        final List<String> sortableFields = List.of("firstname", "lastname", "facultyId");

        if (!sortBy.equals("") && !sortableFields.contains(sortBy)) {
            return Response
                    .status(400, "set is not sortable by this field")
                    .build();
        }

        if (!orderBy.equals("") && !(orderBy.equals("ASC") || orderBy.equals("DESC"))) {
            return Response
                    .status(400, "can only sort ascending or descending")
                    .build();
        }

        // QUERY THE PERSONS BASED ON PROVIDED PARAMETER

        if ( facultyId != -1 ) {
            foundPersons = this.personResource.findByFaculty( facultyId );
        }

        if ( semester != -1 && !ListUtils.exists( foundPersons ) ) {
            foundPersons = this.personResource.findBySemester( semester );
        }

        if ( !query.equals( "" ) && !ListUtils.exists( foundPersons ) ) {
            foundPersons = this.personResource.findByName( query );
        }

        if (!ListUtils.exists( foundPersons )) {
            foundPersons = this.personResource.findAll( sortBy, orderBy );
        }

        if (foundPersons.isEmpty()) {
            return Response.ok(foundPersons).build();
        }

        // PAGINATE THE LIST IF NEEDED

        int start = skip != -1 ? skip : 0;
        start = start < 0 || start > foundPersons.size() ? 0 : start;

        int end = take != -1 ? start + take  : foundPersons.size();
        end = end < 0 || end > foundPersons.size() ? foundPersons.size() : end;

        if (start > end) {
            return Response
                    .status(400, "something is wrong with your pagination parameter")
                    .build();
        }

        List<PersonDto> paginatedList = foundPersons.subList(start, end);

        // SORT THE LIST IF NEEDED
        return Response.ok(paginatedList).build();
    }


    @GET
    @Path("/{person_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("person_id") @DefaultValue( "-1" ) long personId) {
        Optional<PersonDto> foundPerson = this.personResource.findById(personId);

        if (personId == -1 || foundPerson.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(foundPerson.get()).build();
    }

    @POST
    public Response createPerson(PersonDto personToCreate) {
        if (personToCreate.isValidToCreate()) {
            this.personResource.save(personToCreate);
            return Response.status(Response.Status.CREATED).build();
        } else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{person_id}")
    public Response updatePerson(
        @PathParam("person_id") @DefaultValue( "-1" ) long personId,
        PersonDto newPerson
    ) {
        Optional<PersonDto> oFoundPerson = this.personResource.findById(personId);

        if (oFoundPerson.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        PersonDto foundPerson = oFoundPerson.get();
        foundPerson.merge(newPerson);
        this.personResource.save(foundPerson);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{person_id}")
    public Response deletePerson(
        @PathParam("person_id") @DefaultValue( "-1" ) long personId
    ) {
        Optional<PersonDto> foundPerson = this.personResource.findById(personId);

        if (foundPerson.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        this.personResource.deleteBy(personId);
        return Response.status(Response.Status.OK).build();
    }
}
