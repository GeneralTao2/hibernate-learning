package localhost.hibernate.hbm;

import java.util.Date;

public class Event {
    private Long id;
    private Date date;
    private String name;

    public Event(Long id, Date date, String name) {
        this.id = id;
        this.date = date;
        this.name = name;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
