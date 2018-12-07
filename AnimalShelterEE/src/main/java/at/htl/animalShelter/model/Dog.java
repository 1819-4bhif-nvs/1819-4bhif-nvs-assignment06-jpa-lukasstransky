package at.htl.animalShelter.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Dog.findByName", query = "select d from Dog d where d.name = :name"),
        @NamedQuery(name = "Dog.findByAge", query = "select d from Dog d where d.age = :age")
})
public class Dog extends Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //region Constructors
    public Dog() {
    }

    public Dog(String name, int price) {
        super(name, price);
    }

    public Dog(String breed, int age, double weight, String name, int price) {
        super(breed, age, weight, name, price);
    }
    //endregion

    @Override
    public Long getId() {
        return id;
    }
}
