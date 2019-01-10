package at.htl;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CatIT {

    private Client client;
    private WebTarget target;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/AnimalShelter/api/cat");
    }

    @Test
    public void t01_getDeletedCat(){
        target
                .path("1")
                .request()
                .delete();

        Response response = target
                .path("1")
                .request(MediaType.APPLICATION_JSON)
                .get();

        //assertThat(response.getStatus(), is(204));
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void t02_putAndGetCatById(){
        JsonObject catJson = Json.createObjectBuilder()
                .add("name", "Timmy")
                .add("price", 150)
                .build();

        Response putResponse = target
                .request()
                .post(Entity.json(catJson));

        long id = putResponse.readEntity(long.class);

        Response response = target
                .path(id + "")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(result.getString("name"), is("Timmy"));
        assertThat(result.getInt("price"), is(150));
    }

    @Test
    public void t03_putAndGetCatByName(){
        JsonObject dogJson = Json.createObjectBuilder()
                .add("name", "Lilly")
                .add("price", 2700)
                .build();

        target
                .request()
                .post(Entity.json(dogJson));

        Response response = target
                .path("name")
                .path("Lilly")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(result.getString("name"), is("Lilly"));
        assertThat(result.getInt("price"), is(2700));
    }

    @Test
    public void t04_putAndGetCatByAge(){
        JsonObject dogJson = Json.createObjectBuilder()
                .add("breed", "Persisch")
                .add("age", 4)
                .add("weight", 3.5)
                .add("name", "Simba")
                .add("price", 4590)
                .build();

        target
                .request()
                .post(Entity.json(dogJson));

        Response response = target
                .path("age")
                .path("4")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(result.getString("name"), is("Simba"));
        assertThat(result.getInt("price"), is(4590));
        assertThat(result.getInt("age"), is(4));
    }
}
