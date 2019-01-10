package at.htl.animalShelter.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cat.findByName", query = "select c from Cat c where c.name = :name"),
        @NamedQuery(name = "Cat.findByAge", query = "select c from Cat c where c.age = :age")
})
public class Cat extends Pet{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //region Constructors
    public Cat() {
    }

    public Cat(String name, int price) {
        super(name, price);
    }

    public Cat(String breed, int age, double weight, String name, int price) {
        super(breed, age, weight, name, price);
    }
    //endregion constructors

    //region Getter and Setter
    @Override
    public Long getId() {
        return id;
    }
    //endregion
}
