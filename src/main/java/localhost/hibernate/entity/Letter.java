package localhost.hibernate.entity;

import org.hibernate.annotations.Persister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import javax.persistence.*;

@Entity
@Persister( impl = SingleTableEntityPersister.class )
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    public Student author;


    public Letter() {
    }

    public Letter(String title, Student author) {
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Student getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Student author) {
        this.author = author;
    }
}
