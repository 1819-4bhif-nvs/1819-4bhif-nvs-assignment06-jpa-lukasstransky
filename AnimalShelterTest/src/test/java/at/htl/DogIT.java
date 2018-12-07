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

public class DogIT {

    private Client client;
    private WebTarget target;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/AnimalShelter/api/dog");
    }

    @Test
    public void t01_getDeletedDog(){
        target
                .path("5115")
                .request()
                .delete();

        Response response = target
                .path("5115")
                .request(MediaType.APPLICATION_JSON)
                .get();
        JsonObject result = response.readEntity(JsonObject.class);
        System.out.println(result);

        assertThat(response.getStatus(), is(204)); //?????????????
    }

    @Test
    public void t02_putAndGetDogById(){
        JsonObject dogJson = Json.createObjectBuilder()
                .add("name", "Luna")
                .add("price", 150)
                .build();

        Response putResponse = target
                .request()
                .post(Entity.json(dogJson));

        long id = putResponse.readEntity(long.class);

        Response response = target
                .path(id + "")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(result.getString("name"), is("Luna"));
        assertThat(result.getInt("price"), is(150));
    }

    @Test
    public void t03_putAndGetDogByName(){
        JsonObject dogJson = Json.createObjectBuilder()
                .add("name", "Jessy")
                .add("price", 2600)
                .build();

        target
                .request()
                .post(Entity.json(dogJson));

        Response response = target
                .path("name")
                .path("Jessy")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(result.getString("name"), is("Jessy"));
        assertThat(result.getInt("price"), is(2600));
    }

    @Test
    public void t04_putAndGetDogByAge(){
        JsonObject dogJson = Json.createObjectBuilder()
                .add("breed", "Schäferhund")
                .add("age", 14)
                .add("weight", 10.5)
                .add("name", "Rocky")
                .add("price", 490)
                .build();

        target
                .request()
                .post(Entity.json(dogJson));

        Response response = target
                .path("age")
                .path("14")
                .request(MediaType.APPLICATION_JSON)
                .get();

        JsonObject result = response.readEntity(JsonObject.class);

        assertThat(response.getStatus(), is(200));
        assertThat(result.getString("name"), is("Rocky"));
        assertThat(result.getInt("price"), is(490));
        assertThat(result.getInt("age"), is(14));
    }
}
