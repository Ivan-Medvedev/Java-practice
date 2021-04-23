package logic;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String name;
    private String address;
    private String phone;
    private String e_mail;
    private BigDecimal balance;
    private BigDecimal maxLoan;
    private Integer maxLoanTime;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receipt> receiptList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceHistory> serviceHistoryList;

    public Client() {
    }

    public Client(String type,
                  String name,
                  String address,
                  String phone,
                  String e_mail,
                  BigDecimal balance,
                  BigDecimal maxLoan,
                  Integer maxLoanTime)
    {
        this.type = type;
        this.name = name;
        this.maxLoanTime = maxLoanTime;
        this.maxLoan = maxLoan;
        this.balance = balance;
        this.e_mail = e_mail;
        this.address = address;
        this.phone = phone;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setMaxLoan(BigDecimal maxLoan) {
        this.maxLoan = maxLoan;
    }
    public void setMaxLoanTime(Integer maxLoanTime) {
        this.maxLoanTime = maxLoanTime;
    }

    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getE_mail() {
        return e_mail;
    }
    public BigDecimal getBalance() {
        return balance;
    }
    public BigDecimal getMaxLoan() {
        return maxLoan;
    }
    public Integer getMaxLoanTime() {
        return maxLoanTime;
    }

    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }

        if (oth == null || getClass() != oth.getClass()) {
            return false;
        }

        Client other = (Client) oth;

        if (id != other.getId()) {
            return false;
        }

        if (!type.equals(other.getType())) {
            return false;
        }

        if (!name.equals(other.getName())) {
            return false;
        }

        if (!address.equals(other.getAddress())) {
            return false;
        }

        if (!phone.equals(other.getPhone())) {
            return false;
        }

        if (!e_mail.equals(other.getE_mail())) {
            return false;
        }

        if (!balance.equals(other.getBalance())) {
            return false;
        }

        if (!maxLoan.equals(other.getMaxLoan())) {
            return false;
        }

        if (!maxLoanTime.equals(other.getMaxLoanTime())) {
            return false;
        }

        return true;
    }
}