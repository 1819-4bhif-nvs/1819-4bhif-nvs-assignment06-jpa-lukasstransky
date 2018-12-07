package at.htl;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class AnimalShelterST {

    private Client client;
    private WebTarget target;

    @Before
    public void initClient(){
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/AnimalShelter/api/animalShelter");
    }

    @Test
    public void t01_getAllAnimalShelters(){

    }
}
