import dao.*;
import logic.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ServiceHistoryTest {
    private ServiceHistoryDAO serviceHistoryDAO;
    private ClientDAO clientDAO;
    private ServiceDAO serviceDAO;
    private ServiceHistoryTest() {
        serviceDAO = new ServiceDAOImpl();
        clientDAO = new ClientDAOImpl();
        serviceHistoryDAO = new ServiceHistoryDAOImpl();
    }

    @Test
    public void addServiceHistory() {
        Client client = new Client("Person", "Ivan", "DSL", "88005553535",
                "IvanMedvedev900@yandex.ru", new BigDecimal("100.00"), new BigDecimal("199.00"), 1090);
        clientDAO.save(client);

        Service service = new Service("serviceHistoryTEST0", 100, 333, 2, new BigDecimal("0.01"));
        serviceDAO.save(service);

        ServiceHistory serviceHistory = new ServiceHistory(new Date(2000, 6, 1),
                new Date(2021, 6, 1),
                new BigDecimal("10.00"),
                client,
                service);
        serviceHistoryDAO.save(serviceHistory);
        Assert.assertEquals(serviceHistory, serviceHistoryDAO.findById(serviceHistory.getId()));
    }

    @Test
    public void updateServiceHistory() {
        Client client = new Client("Person", "IvanM", "DSL", "88005553535",
                "IvanMedvedev900@yandex.ru", new BigDecimal("100.00"), new BigDecimal("199.00"), 1090);
        clientDAO.save(client);

        Service service = new Service("serviceHistoryTEST1", 100, 333, 2, new BigDecimal("0.01"));
        serviceDAO.save(service);

        ServiceHistory serviceHistory = new ServiceHistory(new Date(2000, 6, 1),
                new Date(2021, 6, 1),
                new BigDecimal("10.00"),
                client,
                service);
        serviceHistoryDAO.save(serviceHistory);


        serviceHistory.setPrice(new BigDecimal("1.22"));
        serviceHistoryDAO.update(serviceHistory);

        Assert.assertEquals(serviceHistory, serviceHistoryDAO.findById(serviceHistory.getId()));
    }

    @Test
    public void deleteClient() {
        Client client = new Client("Person", "IvanMedvedev", "DSL", "88005553535",
                "IvanMedvedev900@yandex.ru", new BigDecimal("100.00"), new BigDecimal("199.00"), 1090);
        clientDAO.save(client);

        Service service = new Service("serviceHistoryTEST2", 100, 333, 2, new BigDecimal("0.01"));
        serviceDAO.save(service);

        ServiceHistory serviceHistory = new ServiceHistory(new Date(2000, 6, 1),
                new Date(2021, 6, 1),
                new BigDecimal("11234.00"),
                client,
                service);
        serviceHistoryDAO.save(serviceHistory);

        serviceHistoryDAO.delete(serviceHistory);

        Assert.assertEquals(serviceHistoryDAO.findById(serviceHistory.getId()), null);
    }
}