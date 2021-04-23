package logic;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Service_history")
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDate;
    private Date finishDate;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "serviceId")
    private Service service;

    public ServiceHistory() {
    }

    public ServiceHistory(Date startDate, Date finishDate, BigDecimal price, Client client, Service service) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.price = price;
        this.client = client;
        this.service = service;
    }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public void setFinishDate(Date finishDate) { this.finishDate = finishDate; }

    public void setPrice(BigDecimal price) { this.price = price; }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getId() { return id; }
    public Date getStartDate() {
        return startDate;
    }
    public Date getFinishDate() {
        return finishDate;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Client getClient() {
        return client;
    }
    public Service getService() {
        return service;
    }

    @Override
    public boolean equals(Object oth) {
        if (this == oth) {
            return true;
        }

        if (oth == null || getClass() != oth.getClass()) {
            return false;
        }

        ServiceHistory other = (ServiceHistory) oth;

        if (id != other.getId()) {
            return false;
        }

        if (!startDate.equals(other.getStartDate())) {
            return false;
        }

        if (!finishDate.equals(other.getFinishDate())) {
            return false;
        }

        if (!price.equals(other.getPrice())) {
            return false;
        }

        if (!client.equals(other.getClient())) {
            return false;
        }

        if (!service.equals(other.getService())) {
            return false;
        }

        return true;
    }
}
