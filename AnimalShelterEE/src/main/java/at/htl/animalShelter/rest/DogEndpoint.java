package at.htl.animalShelter.rest;

import at.htl.animalShelter.model.Dog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("dog")
@Stateless
public class DogEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getDogById(@PathParam("id") long id) {
        Dog dog = em.find(Dog.class, id);
        return Response
                .ok()
                .entity(dog)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Dog getDogByName(@PathParam("name") String name) {
        return em.createNamedQuery("Dog.findByName", Dog.class).setParameter("name", name).getSingleResult();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/age/{age}")
    public Dog getDogByAge(@PathParam("age") int age) {
        return em.createNamedQuery("Dog.findByAge", Dog.class).setParameter("age", age).getSingleResult();
    }

    @POST
    public Long putDog(Dog dog) {
        em.persist(dog);
        return dog.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteDog(@PathParam("id") long id) {
        Dog d = em.find(Dog.class, id);
        if(d != null) {
            em.remove(em.contains(d) ? d : em.merge(d));
        }
    }
}
