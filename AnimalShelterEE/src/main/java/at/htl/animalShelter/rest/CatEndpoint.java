package at.htl.animalShelter.rest;

import at.htl.animalShelter.model.Cat;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cat")
@Stateless
public class CatEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getDrugTreatment(@PathParam("id") long id) {
        Cat cat = em.find(Cat.class, id);
        return Response
                .ok()
                .entity(cat)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Cat getCatByName(@PathParam("name") String name) {
        return em.createNamedQuery("Cat.findByName", Cat.class).setParameter("name", name).getSingleResult();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/age/{age}")
    public Cat getCatByAge(@PathParam("age") int age) {
        return em.createNamedQuery("Cat.findByAge", Cat.class).setParameter("age", age).getSingleResult();
    }

    @POST
    public Long putCat(Cat cat) {
        em.persist(cat);
        return cat.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteCat(@PathParam("id") long id) {
        Cat c = em.find(Cat.class, id);
        if(c != null) {
            em.remove(em.contains(c) ? c : em.merge(c));
        }
    }
}
