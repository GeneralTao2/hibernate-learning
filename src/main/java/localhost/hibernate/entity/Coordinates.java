package localhost.hibernate.entity;

import org.hibernate.annotations.Parent;

public interface Coordinates {
    double x();
    double y();
    Student getStudent();
    void setStudent(Student student);
}