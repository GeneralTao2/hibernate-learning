package localhost.hibernate;

import localhost.hibernate.entity.Student;

import javax.persistence.EntityManager;

import java.util.List;

import static localhost.hibernate.util.PersistenceUtil.getEntityManager;

public class StudentDaoPersistence {
    public StudentDaoPersistence() {}
    public Student getStudent(int id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Student student = em.find(Student.class, id);

        //em.detach(student);
        return student;
    }

    public Student getStudentAndDetach(int id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        Student student = em.find(Student.class, id);

        em.detach(student);
        return student;
    }

    public void saveStudent(Student student) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.persist(student);

        em.getTransaction().commit();
    }

    public void updateStudent(Student student) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.merge(student);

        em.getTransaction().commit();
    }

    public void deleteStudent(Student student) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.remove(student);

        em.getTransaction().commit();
    }

    public void mergeStudent(Student student) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.merge(student);

        em.getTransaction().commit();
    }

    public static void deleteAllStudents() {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM Student").executeUpdate();

        em.getTransaction().commit();
    }

    public static void dropTable() {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        em.createNativeQuery("DROP TABLE student").executeUpdate();

        em.getTransaction().commit();
    }

    public static List<Integer> getIdList() {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        List<Integer> idList = em.createQuery("SELECT id FROM Student", Integer.class).getResultList();

        em.getTransaction().commit();

        return idList;
    }

    public static List<Student> getStudentList() {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();

        List<Student> studentList = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();

        em.getTransaction().commit();

        return studentList;
    }


}