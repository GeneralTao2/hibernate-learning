/*package localhost.hibernate.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity( name = "Car" )
@Proxy(proxyClass = Identifiable.class)
public class Car implements Identifiable {
    @Id
    private Long id;

    private String model;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
*/