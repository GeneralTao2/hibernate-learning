package localhost.hibernate.entity;

import javax.persistence.*;

@Entity(name = "AccountTransaction")
public class AccountTransaction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Account account;

    private Integer cents;

    private String description;

    public AccountTransaction() {
    }

    public AccountTransaction(Account account, Integer cents, String description) {
        this.account = account;
        this.cents = cents;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getCents() {
        return cents;
    }

    public void setCents(Integer cents) {
        this.cents = cents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}