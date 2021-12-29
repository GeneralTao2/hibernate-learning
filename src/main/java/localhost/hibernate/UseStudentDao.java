package localhost.hibernate;


//import com.mysql.cj.xdevapi.Client;
import localhost.hibernate.entity.*;
import localhost.hibernate.entity.dynamicentityproxies.Cuisine;
import localhost.hibernate.entity.dynamicentityproxies.ProxyHelper;
import localhost.hibernate.entity.dynamicentityproxies.Village;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;

import javax.money.MonetaryAmount;
import javax.persistence.EntityManager;
import java.io.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;


import static localhost.hibernate.util.PersistenceUtil.getEntityManager;

public class UseStudentDao {

    public static void main(String[] args) throws IOException, SQLException {
        TimeZone.setDefault( TimeZone.getTimeZone( "UTC" ) );
        StudentDaoPersistence dao = new StudentDaoPersistence();
        BitSet bitSet = BitSet.valueOf(new long[] { 0x0A, 0x0A });
        bitSet.set(0x0A);
        bitSet.set(0x0A);
        File bigStringFile = new File("src/main/resources/bigstring.txt");
        File bigStringFile1 = new File("src/main/resources/bigstring1.txt");
        File image = new File("src/main/resources/image.jpg");

        // can be saved only once
        Clob clob = ClobProxy.generateProxy(
                new FileReader(bigStringFile),
                bigStringFile.length()
        );
        Clob clob1 = ClobProxy.generateProxy(
                new FileReader(bigStringFile1),
                bigStringFile1.length()
        );
        Blob blob = BlobProxy.generateProxy(
                new FileInputStream(image),
                image.length()
        );
        Blob blob1 = BlobProxy.generateProxy(
                new FileInputStream(image),
                image.length()
        );


        Student student = new Student(
                0,
                "1",
                "2",
                //bitSet,
                "rameshfadatare@javaguides.com1",
                new Caption("Hey"),
                clob,
                blob,
                new Date(),
                Period.of(1, 2, 3),
                "123",
                "qwerty",
                1000d,
                0.05d,
                new Phone("123-456-7890", PhoneType.MOBILE),
                new Phone("000-000-7890", PhoneType.LAND_LINE),
                new GPS(123.456,456.123)
                //new Phone()
        );
        Student student1 = new Student(
                0,
                "3",
                "4",
                //bitSet,
                "john@javaguides.com1",
                new Caption("Hi"),
                clob1,
                blob1,
                new Date(),
                Period.of(1, 2, 3),
                "345",
                "qwerty",
                2000d,
                0.02d,
                new Phone("0298-456-7890", PhoneType.LAND_LINE),
                new Phone("0298-111-1111", PhoneType.LAND_LINE),
                new GPS(2123.456,2456.123)
                //new Phone()
        );
        Country country1 = new Country(
                "India"
        );
        Country country2 = new Country(
                "Spain"
        );
        Book book1 = new Book(
                "Java",
                "author1",
                "123"
        );
        Book book2 = new Book(
                "C++",
                "author2",
                "456"
        );

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM AccountTransaction").executeUpdate();
        em.createQuery("DELETE FROM Account").executeUpdate();
        em.createQuery("DELETE FROM Book").executeUpdate();
        em.createQuery("DELETE FROM Letter").executeUpdate();
        //em.persist(country1);
        //em.persist(country2);
        country1 = em.find(Country.class, 1L);
        em.getTransaction().commit();
        StudentDaoPersistence.deleteAllStudents();
        em = getEntityManager();
        em.getTransaction().begin();

        em.persist(book1);
        em.persist(book2);
        //book1 = em.find(Book.class, 1L);
        //book2 = em.find(Book.class, 2L);

        em.getTransaction().commit();

        CurrentUser.INSTANCE.logIn( "Artiom" );
        student.getPhone1().setCountry(country1);
        student.getBooks().add(book1);
        student.getBooks().add(book2);
        dao.saveStudent(student);
        CurrentUser.INSTANCE.logOut();
        CurrentUser.INSTANCE.logIn( "Alice" );
        dao.saveStudent(student1);
        List<Integer> idList = StudentDaoPersistence.getIdList();
        System.out.println(dao.getStudent(idList.get(0)).toString());
        System.out.println(dao.getStudent(idList.get(1)).toString());
        Student student2 = dao.getStudent(idList.get(0));
        System.out.println("interest: " + student2.getCoordinates().getStudent().getInterest());
        student2.setFirstName("Updated");
        dao.updateStudent(student2);
        CurrentUser.INSTANCE.logOut();
        student = dao.getStudent(idList.get(1));
        System.out.println(student.getBooks().contains(book1));
        System.out.println(
                StudentDaoPersistence
                        .getStudentList()
                        .stream()
                        .map(
                                (s) -> new ImmutablePair<>(s.getBooks(), s.getId())
                        )
                        .collect(Collectors.toList())
        );
        System.out.println(dao.getStudent(idList.get(1)).getBooks().stream().map(Book::getTitle).collect(Collectors.toList()));
        System.out.println(student.getBooks());
        System.out.println(book1);

        student = dao.getStudent(idList.get(1));
        System.out.println(student.getBooks().contains(book1));
        em = getEntityManager();
        em.getTransaction().begin();
        Account account = new Account(student, "Checking account");
        em.persist(account);
        AccountTransaction transaction = new AccountTransaction(account, 1000,"scholarship" );
        em.persist(transaction);
        AccountSummary summary = em.createQuery("SELECT a FROM AccountSummary a", AccountSummary.class).getResultList().get(0);
        System.out.println(new ImmutablePair<>(summary.getClientName(), summary.getBalance()));
        //em = getEntityManager();
        em.getTransaction().commit();

        em = getEntityManager();
        em.getTransaction().begin();
        summary = em.find( AccountSummary.class, account.getId() );
        transaction = new AccountTransaction(account, -500,"shopping" );
        em.persist(transaction);
        em.flush();
        em.refresh( summary );
        System.out.println(new ImmutablePair<>(summary.getClientName(), summary.getBalance()));
        Identifiable student_ident = em.getReference(Identifiable.class, idList.get(0));
        System.out.println(student_ident instanceof Identifiable);
        System.out.println(student_ident instanceof Book);
        em.getTransaction().commit();

        /* TODO: How in the earth does this works? */

        /*em = getEntityManager();
        em.getTransaction().begin();
        Cuisine cuisine = ProxyHelper.newProxy( Cuisine.class, null );
        cuisine.setName( "Française" );
        Village village = ProxyHelper.newProxy( Village.class, null );
        village.setName( "Dezg" );
        //Unknown entity: com.sun.proxy.$Proxy65
        cuisine.setVillage( village );
        em.persist( cuisine );
        Long cuisisneId = cuisine.getId();
        em.getTransaction().commit();

        em = getEntityManager();
        em.getTransaction().begin();
        cuisine = em.find( Cuisine.class, cuisisneId );
        System.out.println( cuisine.getName() );
        System.out.println( cuisine.getVillage().getName() );
        em.getTransaction().commit();*/


        em = getEntityManager();
        em.getTransaction().begin();
        student = em.find(Student.class, idList.get(0));
        Letter letter = new Letter("Hello", null);
        em.persist(letter);
        student.addLetter(letter);
        em.persist(student);
        em.getTransaction().commit();
    }
}
