package localhost.hibernate.entity;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;

@Entity(name = "AccountSummary")
@Subselect(
        "select " +
                "	a.id as id, " +
                "	concat(concat(c.first_name, ' '), c.last_name) as client_name, " +
                "	sum(atr.cents) as balance " +
                "from account a " +
                "join student c on c.id = a.client_id " +
                "join account_transaction atr on a.id = atr.account_id " +
                "group by a.id, concat(concat(c.first_name, ' '), c.last_name)"
)
@Synchronize( {"student", "account", "account_transaction"} )
public class AccountSummary {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;

    private int balance;

    public AccountSummary() {
    }

    public AccountSummary(Long id, String clientName, int balance) {
        this.id = id;
        this.clientName = clientName;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}