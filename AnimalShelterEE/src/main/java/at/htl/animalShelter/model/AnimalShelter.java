package at.htl.animalShelter.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "AnimalShelter")
@NamedQueries({
        @NamedQuery(name = "AnimalShelter.findAll", query = "select a from AnimalShelter a"),
        @NamedQuery(name = "AnimalShelter.findByTown", query = "select a from AnimalShelter a where a.town = :town"),
        @NamedQuery(name = "AnimalShelter.findByZipCode", query = "select a from AnimalShelter a where a.post_code = :postCode")
})
public class AnimalShelter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String town;
    private String street;
    private int post_code;

    @JsonbTransient
    @OneToMany(mappedBy = "animalShelter")
    private List<Cage> cages = new ArrayList<>();

    //region Constructors
    public AnimalShelter() {

    }

    public AnimalShelter(String town, String street, int post_code) {
        this.setTown(town);
        this.setStreet(street);
        this.setPost_code(post_code);
    }
    //endregion

    //region Getter And Setter
    public Long getId() {
        return id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPost_code() {
        return post_code;
    }

    public void setPost_code(int post_code) {
        this.post_code = post_code;
    }

    public List<Cage> getCages(){
        return this.cages;
    }
    //endregion
}
