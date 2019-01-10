package at.htl;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CageST {

    private Client client;
    private WebTarget target;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/AnimalShelter/api/cage");
    }

    @Test
    public void t01_getDeletedCage(){
        target
                .path("1")
                .request()
                .delete();

        Response response = target
                .path("1")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void t02_putAndGetCageById(){
        JsonObject petJson = Json.createObjectBuilder()
                .add("breed", "Deutsche Dogge")
                .add("age", 5)
                .add("weight", 6.6)
                .add("name", "Axel")
                .add("price", 132)
                .build();

        JsonObject shelterJson = Json.createObjectBuilder()
                .add("town", "Langenstein")
                .add("street", "Hausgasse")
                .add("post_code", 4455)
                .build();

        JsonObject cageJson = Json.createObjectBuilder()
                .add("cage_row", 5)
                .add("cage_column", 21)
                .add("pet", petJson)
                .add("animalShelter", shelterJson)
                .build();

        Response putResponse = target
                .request()
                .post(Entity.json(cageJson));

        long id = putResponse.readEntity(long.class);

        Response response = target
                .path(id + "")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);
        assertThat(response.getStatus(), is(200));
        assertThat(result.getInt("cage_row"), is(5));
        assertThat(result.getInt("cage_column"), is(21));
    }

    @Test
    public void t03_putAndGetCageByRow(){
        JsonObject cageJson = Json.createObjectBuilder()
                .add("cage_row", 1)
                .add("cage_column", 12)
                .add("pet", Json.createObjectBuilder().add("name", "Goofy").add("price", 14))
                .add("animalShelter", Json.createObjectBuilder().add("town", "Innsbruck").add("street", "Brücklweg").add("post_code", 5454))
                .build();

        target
                .request()
                .post(Entity.json(cageJson));


        Response response = target
                .path("row")
                .path("1")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);
        assertThat(response.getStatus(), is(200));
        assertThat(result.getInt("cage_row"), is(1));
        assertThat(result.getInt("cage_column"), is(12));
    }

    @Test
    public void t04_putAndGetCageByColumn(){
        JsonObject cageJson = Json.createObjectBuilder()
                .add("cage_row", 4)
                .add("cage_column", 9)
                .add("pet", Json.createObjectBuilder().add("name", "Garfield").add("price", 1478))
                .add("animalShelter", Json.createObjectBuilder().add("town", "Hallstadt").add("street", "Hallgasse").add("post_code", 8771))
                .build();

        target
                .request()
                .post(Entity.json(cageJson));


        Response response = target
                .path("column")
                .path("9")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);
        assertThat(response.getStatus(), is(200));
        assertThat(result.getInt("cage_row"), is(4));
        assertThat(result.getInt("cage_column"), is(9));
    }

    @Test
    public void t05_putAndGetPetFromCage(){

    }

}
