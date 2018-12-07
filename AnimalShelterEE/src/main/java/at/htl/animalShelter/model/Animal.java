package at.htl.animalShelter.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String breed;
    private int age;
    private double weight;

    //region Constructors
    public Animal() {
    }

    public Animal(String breed, int age, double weight) {
        this.setBreed(breed);
        this.setAge(age);
        this.setWeight(weight);
    }
    //endregion

    //region Getter And Setter
    public Long getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    //endregion
}
