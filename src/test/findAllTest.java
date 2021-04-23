import dao.*;
import logic.*;

import java.util.Collection;
import java.util.Iterator;

import org.testng.annotations.Test;
import org.testng.Assert;

public class findAllTest {
    @Test
    public void clientFindAll() {
        System.out.println("======= Clients ===========");
        Collection clients = Factory.getInstance().getClientDAO().findAll();
        Iterator iterator = clients.iterator();
        while (iterator.hasNext()) {
            Client client = (Client) iterator.next();
            System.out.println(client.getName());
        }
    }

    @Test
    public void receiptsFindAll() {
        System.out.println("======= Receipts ===========");
        Collection receipts = Factory.getInstance().getReceiptDAO().findAll();
        Iterator iterator = receipts.iterator();
        while (iterator.hasNext()) {
            Receipt receipt = (Receipt) iterator.next();
            System.out.println(receipt.getAmount());
        }
    }

    @Test
    public void servicesFindAll() {
        System.out.println("======= Services ===========");
        Collection services = Factory.getInstance().getServiceDAO().findAll();
        Iterator iterator = services.iterator();
        while (iterator.hasNext()) {
            Service service = (Service) iterator.next();
            System.out.println(service.getName());
        }
    }

    @Test
    public void serviceHistoryFindAll() {
        System.out.println("======= ServiceHistory ===========");
        Collection serviceHistories = Factory.getInstance().getServiceHistoryDAO().findAll();
        Iterator iterator = serviceHistories.iterator();
        while (iterator.hasNext()) {
            ServiceHistory serviceHistory = (ServiceHistory) iterator.next();
            System.out.println(serviceHistory.getStartDate());
        }
    }
}