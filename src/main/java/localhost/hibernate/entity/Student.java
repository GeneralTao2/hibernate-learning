package localhost.hibernate.entity;

import org.hibernate.annotations.*;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.persister.collection.OneToManyPersister;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;

import javax.persistence.*;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Blob;
import java.sql.Clob;
import java.time.Period;
import java.util.BitSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        catalog = "test_ground_db",
        /* only in mariadb like dbs */
        //schema = "university",
        name = "student"
)
@AttributeOverrides({
        @AttributeOverride(
                name = "phone1.number",
                column = @Column(name = "phone1Number")
        ),
        @AttributeOverride(
                name = "phone2.number",
                column = @Column(name = "phone2Number")
        ),
        @AttributeOverride(
                name = "phone1.type",
                column = @Column(name = "phone1Type")
        ),
        @AttributeOverride(
                name = "phone2.type",
                column = @Column(name = "phone2Type")
        )
})
@AssociationOverrides({
        @AssociationOverride(
                name = "phone1.country",
                joinColumns = @JoinColumn(name = "phone1CountryId")
        ),
        @AssociationOverride(
                name = "phone2.country",
                joinColumns = @JoinColumn(name = "phone2CountryId")
        )
})
@Proxy(proxyClass = Identifiable.class)
@Persister( impl = SingleTableEntityPersister.class )
public class Student implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    /*@org.hibernate.annotations.Type( type = "bitset" )
    private BitSet bitSet; */ /* TODO: how to registrate SQL custom type in JPA */

    @org.hibernate.annotations.Type( type = "text" )
    private String email;

    @Convert(converter = CaptionConverter.class)
    private Caption caption;

    @Lob
    private Clob bigString;

    @Lob
    private Blob image;

    public Clob getBigString() {
        return bigString;
    }

    public void setBigString(Clob bigString) {
        this.bigString = bigString;
    }

    @Column(name = "`timestamp`")
    @Temporal(TemporalType.DATE)//TIME, TIMESTAMP
    private Date timestamp;

    @Convert(converter = PeriodStringConverter.class)
    @Column(columnDefinition = "")
    private Period span;

    @Column(name = "\"number\"")
    private String number;


    /*@Generated( value = GenerationTime.ALWAYS )
    @Column(columnDefinition =
            "AS CONCAT(" +
                    "	COALESCE(firstName, ''), " +
                    "	COALESCE(' ' + lastName, '') " +
                    ")")
    private String fullName;*/ /* TODO: script runs faster then table creation done */

    @GeneratorType( type = LoggedUserGenerator.class, when = GenerationTime.INSERT)
    private String createdBy;

    @GeneratorType( type = LoggedUserGenerator.class, when = GenerationTime.ALWAYS)
    private String updatedBy;

    @CreationTimestamp
    private Date autoTimestamp;

    @UpdateTimestamp
    private Date updatedOn;

    @Column(name = "pswd")
    /* TODO: Value was not an array [java.lang.String] */
    /*@ColumnTransformer(
            read = "pgp_sym_decrypt(pswd, 'mykeystring')",
            write = "pgp_sym_encrypt(?, 'mykeystring')"
    )
    @org.hibernate.annotations.Type( type = "binary" )*/
    private String password;


    @FunctionCreationTimestamp
    private Date functionCreationTimestamp;

    /* TODO: how to implement? */
    /*@Type(type = "org.hibernate.userguide.mapping.basic.MonetaryAmountUserType")
    @Columns(columns = {
            @Column(name = "money"),
            @Column(name = "currency")
    })
    @ColumnTransformer(
            forColumn = "money",
            read = "money / 100",
            write = "? * 100"
    )
    private MonetaryAmount wallet;*/

    private Double credit;

    private Double rate;

    @Formula(value = "credit * rate")
    private Double interest;

    private Phone phone1;

    private Phone phone2;

    @Embedded
    @Target( GPS.class )
    private Coordinates coordinates;

    /* TODO: Replaced ALL to MERGE and problem with detached object cant be persisted solved */
    @OneToMany(cascade = CascadeType.MERGE )
    @JoinColumn(name = "book_id")
    private Set<Book> books = new HashSet<>();

    @OneToMany( mappedBy = "author" )
    @Persister( impl = OneToManyPersister.class )
    public Set<Letter> letters = new HashSet<>();

    public void addLetter(Letter letter) {
        this.letters.add( letter );
        letter.setAuthor( this );
    }

    public Set<Letter> getLetters() {
        return letters;
    }

    public void setLetters(Set<Letter> letters) {
        this.letters = letters;
    }

    @Access( AccessType.FIELD )
    @Version
    private int version;

    public Student(int id,
                   String firstName,
                   String lastName,
                   //BitSet bitSet,
                   String email,
                   Caption caption,
                   Clob bigString,
                   Blob image,
                   Date timestamp,
                   Period span,
                   String number,
                   String password,
                   Double credit,
                   Double rate,
                   Phone phone1,
                   Phone phone2,
                   Coordinates coordinates
                   ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.bitSet = bitSet;
        this.email = email;
        this.caption = caption;
        this.bigString = bigString;
        this.image = image;
        this.timestamp = timestamp;
        this.span = span;
        this.number = number;
        this.password = password;
        this.credit = credit;
        this.rate = rate;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.coordinates = coordinates;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Phone getPhone1() {
        return phone1;
    }

    public void setPhone1(Phone phone1) {
        this.phone1 = phone1;
    }

    public Phone getPhone2() {
        return phone2;
    }

    public void setPhone2(Phone phone2) {
        this.phone2 = phone2;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    /*public MonetaryAmount getWallet() {
        return wallet;
    }

    public void setWallet(MonetaryAmount wallet) {
        this.wallet = wallet;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFunctionCreationTimestamp() {
        return functionCreationTimestamp;
    }

    public void setFunctionCreationTimestamp(Date functionCreationTimestamp) {
        this.functionCreationTimestamp = functionCreationTimestamp;
    }

    public Date getAutoTimestamp() {
        return autoTimestamp;
    }

    public void setAutoTimestamp(Date autoTimestamp) {
        this.autoTimestamp = autoTimestamp;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Period getSpan() {
        return span;
    }

    public void setSpan(Period span) {
        this.span = span;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }
/*private Phone phone;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }*/

    /*public Student(int id, String firstName, String lastName, BitSet bitSet, String email, Phone phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bitSet = bitSet;
        this.email = email;
        this.phone = phone;
    }*/


    /*public BitSet getBitSet() {
        return bitSet;
    }

    public void setBitSet(BitSet bitSet) {
        this.bitSet = bitSet;
    }*/

    public Student() {

    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }
}