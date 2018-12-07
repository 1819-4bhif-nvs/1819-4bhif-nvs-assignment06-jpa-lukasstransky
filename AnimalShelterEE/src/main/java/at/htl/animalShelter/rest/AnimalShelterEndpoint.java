package at.htl.animalShelter.rest;

import at.htl.animalShelter.model.AnimalShelter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("animalShelter")
@Stateless
public class AnimalShelterEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShelters(){
        TypedQuery<AnimalShelter> query = em.createNamedQuery("AnimalShelter.findAll", AnimalShelter.class);
        List<AnimalShelter> shelters = query.getResultList();
        return Response.ok().entity(shelters).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public AnimalShelter getAnimalShelter(@PathParam("id") long id) {
        return em.find(AnimalShelter.class, id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/town/{town}")
    public AnimalShelter getAnimalShelterByTown(@PathParam("town") String town) {
        return em.createNamedQuery("AnimalShelter.findByTown", AnimalShelter.class).setParameter("town", town).getSingleResult();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/postCode/{postCode}")
    public AnimalShelter getAnimalShelterByPostCode(@PathParam("postCode") String postCode) {
        return em.createNamedQuery("AnimalShelter.findByZipCode", AnimalShelter.class).setParameter("postCode", postCode).getSingleResult();
    }

    @POST
    public Long putDoctor(AnimalShelter animalShelter) {
        em.persist(animalShelter);
        return animalShelter.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteDoctor(@PathParam("id") long id) {
        AnimalShelter a = em.find(AnimalShelter.class, id);
        if(a != null) {
            em.remove(em.contains(a) ? a : em.merge(a));
        }
    }

}
