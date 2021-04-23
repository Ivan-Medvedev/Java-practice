import dao.*;
import logic.*;

import java.math.BigDecimal;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ServiceTest {
    private ServiceDAO serviceDAOtest;
    private ServiceTest() {
        serviceDAOtest = new ServiceDAOImpl();
    }

    @Test
    public void addService() {
        Service testService = new Service("serviceTEST0", 100, 333, 2, new BigDecimal("0.01"));
        serviceDAOtest.save(testService);
        Assert.assertEquals(testService, serviceDAOtest.findById(testService.getId()));
    }

    @Test
    public void updateService() {
        Service service = new Service("serviceTEST1", 100, 333, 2, new BigDecimal("0.01"));
        serviceDAOtest.save(service);

        service.setInternetAmount(10002);
        serviceDAOtest.update(service);

        Service loadedService = serviceDAOtest.findById(service.getId());
        Assert.assertEquals(service, loadedService);
    }

    @Test
    public void deleteService() {
        Service service = new Service("serviceTEST2", 100, 333, 2, new BigDecimal("0.01"));
        serviceDAOtest.save(service);
        serviceDAOtest.delete(service);
        Service loadedService = serviceDAOtest.findById(service.getId());
        Assert.assertEquals(loadedService, null);
    }
}