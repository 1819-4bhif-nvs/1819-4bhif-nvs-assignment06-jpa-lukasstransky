package at.htl.animalShelter.model;

import javax.persistence.*;

@Entity
public class Pet extends Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;

    //region Constructors
    public Pet(){

    }

    public Pet(String name, int price) {
        this.setName(name);
        this.setPrice(price);
    }

    public Pet(String breed, int age, double weight, String name, int price) {
        super(breed, age, weight);
        this.name = name;
        this.price = price;
    }

    //endregion

    //region Getter And Setter
    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    //endregion
}
