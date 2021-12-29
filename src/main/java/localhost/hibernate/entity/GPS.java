package localhost.hibernate.entity;

import org.hibernate.annotations.Parent;

import javax.persistence.Embeddable;

@Embeddable
public class GPS implements Coordinates {

    private double latitude;

    private double longitude;

    @Parent
    private Student student;

    private GPS() {
    }

    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public double x() {
        return latitude;
    }

    @Override
    public double y() {
        return longitude;
    }

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public void setStudent(Student student) {
        this.student = student;
    }
}