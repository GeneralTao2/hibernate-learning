package localhost.hibernate;

import localhost.hibernate.entity.Caption;
import localhost.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import localhost.hibernate.entity.Student;

public class StudentDao {
    public Student getStudent(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // get Student entity using get() method
            Student student = session.get(Student.class, id);

            // commit transaction
            transaction.commit();

            return student;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void printStudentByLoad(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // get Student entity using load() method
            Student student = session.load(Student.class, id);
            System.out.println(student.getFirstName());
            System.out.println(student.getEmail());

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception e2) { /* !!! */
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void printStudentByGet(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Obtain an entity using byId() method
            Student student = session.byId(Student.class).getReference(id);
            System.out.println(student.getFirstName());
            System.out.println(student.getEmail());
            //System.out.println(student.getBitSet().toString());

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception e2) { /* !!! */
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(student);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception e2) { /* !!! */
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void saveOrUpdateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.saveOrUpdate(student);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception e2) { /* !!! */
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void printStudentByCaption(Caption caption) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            Student student = session.createQuery(
                            //"select * " +
                                    "from Student " +
                                    "where upper(caption) = upper(:caption) ", Student.class )
                    .setParameter( "caption", caption.getText() )
                    .getSingleResult();
            System.out.println("Student by caption: " + student.getFirstName());
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception e2) { /* !!! */
                    e2.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }
}