package localhost.hibernate.entity;

import javax.persistence.*;

@Embeddable
public class Phone {
    //
    @Column(name = "phoneNumber")
    private String number;

    @Column(name = "phoneType")
    @Enumerated(EnumType.STRING) //or ordinal
    private PhoneType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Phone(String number, PhoneType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public Phone() {
    }
}

