package person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;

@Path("/person")
public class PersonService {
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons() {
        return Response.ok(preparePersons(), MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{personId}")
    public Response removePerson(@PathParam("personId") int personId) {
        int status = EntityManager.removeEntity(personId);
        return Response.status(status).entity(preparePersons()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("new/{person}")
    public Response savePerson(@PathParam("person") String person) {
        try {
            JSONObject jsonPerson = (JSONObject) new JSONParser().parse(person);
            PersonBean personBean = new PersonBean();
            personBean.setName(jsonPerson.get("name").toString());
            personBean.setSurname(jsonPerson.get("surname").toString());
            personBean.setPatronymic(jsonPerson.get("patronymic").toString());
            try {
                personBean.setDob(sdf.parse(String.valueOf(jsonPerson.get("dob"))));
            } catch (java.text.ParseException e) {
                return Response.status(500).entity(preparePersons()).build();
            }
            EntityManager.getinstance().addEntity(personBean);
            return Response.status(200).entity(preparePersons()).build();
        } catch (ParseException e) {
            return Response.status(500).entity(preparePersons()).build();
        }
    }

    private JSONArray preparePersons() {
        JSONArray persons = new JSONArray();
        JSONObject personJsonObject;
        for (PersonBean personBean : EntityManager.getinstance().getEntities()) {
            personJsonObject = new JSONObject();
            personJsonObject.put("id", personBean.getId());
            personJsonObject.put("name", personBean.getName());
            personJsonObject.put("surname", personBean.getSurname());
            personJsonObject.put("patronymic", personBean.getPatronymic());
            personJsonObject.put("dob", sdf.format(personBean.getDob()));
            persons.add(personJsonObject);
        }
        return persons;
    }
}
