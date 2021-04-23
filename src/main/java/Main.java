import dao.*;
import logic.*;

import java.io.*;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String args[]) {
        System.out.println("======= Clients ===========");
        Collection clients = Factory.getInstance().getClientDAO().findAll();
        Iterator iterator = clients.iterator();
        while (iterator.hasNext()) {
            Client client = (Client) iterator.next();
            System.out.println(client.getName());
        }

        System.out.println("======= Receipts ===========");
        Collection receipts = Factory.getInstance().getReceiptDAO().findAll();
        iterator = receipts.iterator();
        while (iterator.hasNext()) {
            Receipt receipt = (Receipt) iterator.next();
            System.out.println(receipt.getAmount());
        }

        System.out.println("======= Services ===========");
        Collection services = Factory.getInstance().getServiceDAO().findAll();
        iterator = services.iterator();
        while (iterator.hasNext()) {
            Service service = (Service) iterator.next();
            System.out.println(service.getName());
        }

        System.out.println("======= ServiceHistory ===========");
        Collection serviceHistories = Factory.getInstance().getServiceHistoryDAO().findAll();
        iterator = serviceHistories.iterator();
        while (iterator.hasNext()) {
            ServiceHistory serviceHistory = (ServiceHistory) iterator.next();
            System.out.println(serviceHistory.getStartDate());
        }
    }
}