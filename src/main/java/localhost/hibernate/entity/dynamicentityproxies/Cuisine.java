package localhost.hibernate.entity.dynamicentityproxies;


import localhost.hibernate.entity.Country;
import org.hibernate.annotations.Tuplizer;

import javax.persistence.*;

@Entity
@Tuplizer(impl = DynamicEntityTuplizer.class)
public interface Cuisine {

    @Id
    @GeneratedValue
    Long getId();
    void setId(Long id);

    String getName();
    void setName(String name);

    @Tuplizer(impl = DynamicEmbeddableTuplizer.class)
    Village getVillage();
    void setVillage(Village village);
}


