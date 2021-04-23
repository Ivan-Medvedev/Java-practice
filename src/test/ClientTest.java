import dao.*;
import logic.*;

import java.math.BigDecimal;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ClientTest {
    private ClientDAO clientDAOtest;
    private ClientTest() {
        clientDAOtest = new ClientDAOImpl();
    }

    @Test
    public void addClient() {
        Client testClient = new Client("a", "bb", "c", "d",
                "e", new BigDecimal("0.00"), new BigDecimal("1.00"), 10);
        clientDAOtest.save(testClient);
        Assert.assertEquals(testClient, clientDAOtest.findById(testClient.getId()));
    }

    @Test
    public void updateClient() {
        Client client = new Client("Person", "Ivan", "DSL", "88005553535",
                "IvanMedvedev900@yandex.ru", new BigDecimal("100.00"), new BigDecimal("199.00"), 1090);
        clientDAOtest.save(client);

        client.setName("Ivan Vitalyevich");
        clientDAOtest.update(client);

        Client loadedClient = clientDAOtest.findById(client.getId());
        Assert.assertEquals(client, loadedClient);
    }

    @Test
    public void deleteClient() {
        Client client = new Client("Person", "Ivan", "DSL", "88005553535",
                "IvanMedvedev900@yandex.ru", new BigDecimal("100.00"), new BigDecimal("199.00"), 1090);
        clientDAOtest.save(client);
        clientDAOtest.delete(client);
        Client loadedClient = clientDAOtest.findById(client.getId());
        Assert.assertEquals(loadedClient, null);
    }
}