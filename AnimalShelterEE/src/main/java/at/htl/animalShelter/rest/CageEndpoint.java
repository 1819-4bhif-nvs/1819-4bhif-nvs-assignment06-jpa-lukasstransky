package at.htl.animalShelter.rest;

import at.htl.animalShelter.model.AnimalShelter;
import at.htl.animalShelter.model.Cage;
import at.htl.animalShelter.model.Pet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cage")
@Stateless
public class CageEndpoint {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Cage getCage(@PathParam("id") long id) {
        return em.find(Cage.class, id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/row/{row}")
    public Cage getCageByAge(@PathParam("row") int row) {
        return em.createNamedQuery("Cage.findByRow", Cage.class).setParameter("row", row).getSingleResult();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/column/{column}")
    public Cage getCageByColumn(@PathParam("column") int column) {
        return em.createNamedQuery("Cage.findByColumn", Cage.class).setParameter("column", column).getSingleResult();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("pet/{id}")
    public Pet getPetFromCage(@PathParam("id") long id){
        Cage c = em.createNamedQuery("Cage.findPetFromCage", Cage.class).setParameter("id", id).getSingleResult();
        return c.getPet();
    }

    @POST
    public Long putCage(Cage cage) {
        em.persist(cage);
        return cage.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteDog(@PathParam("id") long id) {
        Cage c = em.find(Cage.class, id);
        if(c != null) {
            em.remove(em.contains(c) ? c : em.merge(c));
        }
    }

}
