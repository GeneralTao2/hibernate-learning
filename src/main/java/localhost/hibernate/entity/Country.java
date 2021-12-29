package localhost.hibernate.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity(name = "Country")
@Table(
        catalog = "test_ground_db",
        //schema = "university",
        name = "country"
)
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
