package localhost.hibernate.entity.dynamicentityproxies;


import javax.persistence.*;

@Embeddable
public interface Village {

    @Column(name = "VillageName")
    String getName();

    void setName(String name);
}

