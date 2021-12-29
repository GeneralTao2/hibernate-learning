package tests;

import localhost.hibernate.StudentDaoPersistence;
import localhost.hibernate.entity.*;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;

import java.io.*;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.time.Period;
import java.util.BitSet;
import java.util.Date;
import java.util.TimeZone;

public class TestStudentDao {
    /*public static void main(String[] args) throws IOException, SQLException {
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
                "123"
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
                "345"
                //new Phone()
        );
        dao.saveStudent(student);
        dao.saveStudent(student1);
        System.out.println(dao.getStudent(1).toString());
        System.out.println(dao.getStudent(2).toString());
        Student student2 = dao.getStudent(1);
        student2.setFirstName("Updated");
        dao.updateStudent(student2);

        //CurrentUser.INSTANCE.logIn( "Alice" );
    }
*/

}
