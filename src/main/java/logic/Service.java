package logic;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer InternetAmount;
    private Integer callMinutes;
    private Integer smsAmount;
    private BigDecimal monthPrice;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceHistory> serviceHistoryList;

    public Service() {
    }

    public Service(String name, Integer InternetAmount, Integer callMinutes, Integer smsAmount, BigDecimal monthPrice) {
        this.name = name;
        this.InternetAmount = InternetAmount;
        this.callMinutes = callMinutes;
        this.smsAmount = smsAmount;
        this.monthPrice = monthPrice;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setInternetAmount(Integer InternetAmount) {
        this.InternetAmount = InternetAmount;
    }
    public void setCallMinutes(Integer callMinutes) {
        this.callMinutes = callMinutes;
    }
    public void setSmsAmount(Integer smsAmount) {
        this.smsAmount = smsAmount;
    }
    public void setMonthPrice(BigDecimal monthPrice) {
        this.monthPrice = monthPrice;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getInternetAmount() {
        return InternetAmount;
    }
    public Integer getCallMinutes() {
        return callMinutes;
    }
    public Integer getSmsAmount() {
        return smsAmount;
    }
    public BigDecimal getMonthPrice() {
        return monthPrice;
    }

    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }

        if (oth == null || getClass() != oth.getClass()) {
            return false;
        }

        Service other = (Service) oth;

        if (id != other.getId()) {
            return false;
        }

        if (!name.equals(other.getName())) {
            return false;
        }

        if (!InternetAmount.equals(other.getInternetAmount())) {
            return false;
        }

        if (!callMinutes.equals(other.getCallMinutes())) {
            return false;
        }

        if (!smsAmount.equals(other.getSmsAmount())) {
            return false;
        }

        if (!monthPrice.equals(other.getMonthPrice())) {
            return false;
        }

        return true;
    }
}