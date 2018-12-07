package at.htl.animalShelter.business;

import at.htl.animalShelter.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Startup
@Singleton
public class InitBean {

    @PersistenceContext
    EntityManager em;

    public InitBean(){

    }

    @PostConstruct
    private void init(){
        /*System.err.println("***************** hello");

        AnimalShelter as1 = new AnimalShelter("Luftenberg", "Holzgasse 5", 4225);
        AnimalShelter as2 = new AnimalShelter("Linz", "Bachweg 99", 4060);
        AnimalShelter as3 = new AnimalShelter("Wien", "Schmiedgasse 75", 4545);

        Cage c1 = new Cage(5, 10, null, as1);
        Cage c2 = new Cage(1, 2, null, as1);
        Cage c3 = new Cage(2, 14, null, as2);
        Cage c4 = new Cage(1, 9, null, as2);
        Cage c5 = new Cage(3, 21, null, as3);

        Dog d1 = new Dog("Mischling", 5, 10.2, "Luna", 150);
        Dog d2 = new Dog("Dackel", 14, 4.5, "Jessy", 255);
        Dog d3 = new Dog("Schaeferhund", 1, 14.8, "Rocky", 430);
        Cat cat1 = new Cat("Persisch", 2, 3.1, "Lilly", 789);
        Cat cat2 = new Cat("Hauskatze", 8, 2.8, "Timmy", 200);

        c1.setPet(d1);
        c2.setPet(d2);
        c3.setPet(d3);
        c4.setPet(cat1);
        c5.setPet(cat2);

        em.persist(as1);
        em.persist(as2);
        em.persist(as3);

        em.persist(d1);
        em.persist(d2);
        em.persist(d3);
        em.persist(cat1);
        em.persist(cat2);

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);
        em.persist(c5);*/

    }
}
