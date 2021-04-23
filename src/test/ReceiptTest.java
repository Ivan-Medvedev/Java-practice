import dao.*;
import logic.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ReceiptTest {
    private ReceiptDAO receiptDAOtest;
    private ClientDAO clientDAOtest;
    private ReceiptTest() {
        receiptDAOtest = new ReceiptDAOImpl();
        clientDAOtest = new ClientDAOImpl();
    }

    @Test
    public void addReceipt() {
        Client testClient = new Client("a", "bb", "c", "d",
                "e", new BigDecimal("0.00"), new BigDecimal("1.00"), 10);
        clientDAOtest.save(testClient);

        Receipt testReceipt = new Receipt(new Date(2000, 6, 1), new BigDecimal("1.60"), testClient);
        receiptDAOtest.save(testReceipt);
        Assert.assertEquals(testReceipt,
                clientDAOtest.findReceiptById(receiptDAOtest.findById(testReceipt.getId()).getId()));
    }

    @Test
    public void updateClient() {
        Client client = new Client("Person", "Ivan", "DSL", "88005553535",
                "IvanMedvedev900@yandex.ru", new BigDecimal("100.00"), new BigDecimal("199.00"), 1090);
        clientDAOtest.save(client);

        Receipt testReceipt = new Receipt(new Date(2000, 6, 1), new BigDecimal("1.60"), client);
        receiptDAOtest.save(testReceipt);

        testReceipt.setAmount(new BigDecimal("1.22"));
        receiptDAOtest.update(testReceipt);

        Assert.assertEquals(testReceipt, receiptDAOtest.findById(testReceipt.getId()));
    }

    @Test
    public void deleteClient() {
        Client client = new Client("Person", "Ivan", "DSL", "88005553535",
                "IvanMedvedev900@yandex.ru", new BigDecimal("100.00"), new BigDecimal("199.00"), 1090);
        clientDAOtest.save(client);

        Receipt testReceipt = new Receipt(new Date(2000, 6, 1), new BigDecimal("1.60"), client);
        receiptDAOtest.save(testReceipt);

        receiptDAOtest.delete(testReceipt);

        Assert.assertEquals(receiptDAOtest.findById(testReceipt.getId()), null);
    }
}