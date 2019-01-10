package at.htl.animalShelter.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Cage.findByRow", query = "select c from Cage c where c.cage_row = :row"),
        @NamedQuery(name = "Cage.findByColumn", query = "select c from Cage c where c.cage_column = :column"),
        @NamedQuery(name = "Cage.findPetFromCage", query = "select c from Cage c join Pet p on p.id = c.pet.id where p.id = :id"),
})
public class Cage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int cage_row;
    private int cage_column;

    @OneToOne(cascade = {CascadeType.ALL})
    private Pet pet;

    //@JsonbTransient
    @ManyToOne(cascade = {CascadeType.ALL})
    private AnimalShelter animalShelter;

    //region Constructors
    public Cage() {
    }

    public Cage(int row, int column){
        this.setCage_row(row);
        this.setCage_column(column);
    }

    public Cage(int cage_row, int cage_column, Pet pet, AnimalShelter animalShelter) {
        this.setCage_row(cage_row);
        this.setCage_column(cage_column);
        this.setPet(pet);
        this.setAnimalShelter(animalShelter);
    }
    //endregion

    //region Getter And Setter
    public Long getId() { return id; }

    public int getCage_row() {
        return cage_row;
    }

    public void setCage_row(int cage_row) {
        this.cage_row = cage_row;
    }

    public int getCage_column() {
        return cage_column;
    }

    public void setCage_column(int cage_column) {
        this.cage_column = cage_column;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public AnimalShelter getAnimalShelter() {
        return animalShelter;
    }

    public void setAnimalShelter(AnimalShelter animalShelter) {
        this.animalShelter = animalShelter;
    }
    //endregion
}
