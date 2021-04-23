package logic;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date receiptDate;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    public Receipt() {
    }

    public Receipt(Date receiptDate, BigDecimal amount, Client client) {
        this.receiptDate = receiptDate;
        this.amount = amount;
        this.client = client;
    }

    public void setReceiptDate(Date name) {
        this.receiptDate = receiptDate;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public int getId() { return id; }
    public Date getReceiptDate() {
        return receiptDate;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Client getClient() {
        return client;
    }

    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }

        if (oth == null || getClass() != oth.getClass()) {
            return false;
        }

        Receipt other = (Receipt) oth;

        if (id != other.getId()) {
            return false;
        }

        if (!receiptDate.equals(other.getReceiptDate())) {
            return false;
        }

        if (!amount.equals(other.getAmount())) {
            return false;
        }

        if (!client.equals(other.getClient())) {
            return false;
        }

        return true;
    }
}
