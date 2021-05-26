import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.xml.sax.SAXException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;
import com.meterware.httpunit.*;
import com.meterware.httpunit.*;
import junit.framework.*;
import org.junit.Test;

import dao.*;
import logic.*;
import dao.Factory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.lang.Math;
import java.util.Collection;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class SystemTest {
    @Test
    public void mainContentTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://localhost:8080/");
        assertEquals("Main page", resp.getTitle());
        WebLink[] links = resp.getLinks();
        assertEquals(links.length, 2);
        assertEquals(links[0].getText(), "Clients");
        assertEquals(links[1].getText(), "Services");
        assertEquals(links[0].getURLString(), "/clients");
        assertEquals(links[1].getURLString(), "/services");
    }

    @Test
    public void mainLinksTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://localhost:8080/");
        WebLink[] links = resp.getLinks();

        WebResponse clients = links[0].click();
        assertEquals(wc.getResponse("http://localhost:8080/clients").getURL().getPath(), clients.getURL().getPath());
        WebResponse services = links[1].click();
        assertEquals(wc.getResponse("http://localhost:8080/services").getURL().getPath(), services.getURL().getPath());
    }

    @Test
    public void seeAllClientsTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://localhost:8080/clients");

        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        Collection<Client> clients = clientDAO.findAll();

        WebLink links[] = resp.getLinks();

        assertEquals(links.length, clients.size() * 4 + 2);
        assertEquals(links[0].getText(), "To main page");
        assertEquals(links[0].getURLString(), "/");

        String[][] table = resp.getTables()[0].asText();

        for (String[] row : table) {
            if (row[0].compareTo("ID") == 0)
                continue;
            Client client = clientDAO.findById(Integer.parseInt(row[0]));
            assertEquals(Integer.parseInt(row[0]), client.getId());
            assertEquals(row[1], client.getType());
            assertEquals(row[2], client.getName());
            assertEquals(row[3], client.getAddress());
            assertEquals(row[4], client.getPhone());
            assertEquals(row[5], client.getE_mail());
            assertEquals(row[6], client.getBalance().toString());
            assertEquals(row[7], client.getMaxLoan().toString());
            assertEquals(Integer.parseInt(row[8]), client.getMaxLoanTime());
        }
    }

    @Test
    public void clientsLinksTest() throws IOException, SAXException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://localhost:8080/clients");
        WebLink[] links = resp.getLinks();

        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        Collection<Client> clients = clientDAO.findAll();

        assertEquals(links[clients.size() * 4 + 1].getText(), "Add new client");
        assertEquals(links[clients.size() * 4 + 1].getURLString(), "/addClient");
        WebResponse addClient = links[clients.size() * 4 + 1].click();
        assertEquals(wc.getResponse("http://localhost:8080/addClient").getURL().getPath(), addClient.getURL().getPath());
    }

    @Test
    public void allClientTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://localhost:8080/addClient");
        WebForm form = resp.getForms()[0];

        form.setParameter("name", "testName");
        form.setParameter("type", "testType");
        form.setParameter("address", "testAddress");
        form.setParameter("e_mail", "testEmail");
        form.setParameter("phone", "testPhone");
        form.setParameter("balance", "500");
        form.setParameter("maxLoan", "1000");
        form.setParameter("maxLoanTime", "50");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("Add")));

        assertEquals(resp.getURL().getPath(), "/clients");

        String[][] table = resp.getTables()[0].asText();

        ClientDAO clientDAO = Factory.getInstance().getClientDAO();

        WebLink links[] = resp.getLinks();
        WebResponse editClient = resp;
        Integer id = 0, i = 0;
        for (String[] row : table) {
            if (row[2].compareTo("testName") == 0) {
                id = Integer.parseInt(row[0]);
                Client client = clientDAO.findById(id);
                assertEquals(client.getName(), "testName");
                assertEquals(client.getType(), "testType");
                assertEquals(client.getAddress(), "testAddress");
                assertEquals(client.getE_mail(), "testEmail");
                assertEquals(client.getPhone(), "testPhone");
                assertEquals(client.getBalance().toString(), "500");
                assertEquals(client.getMaxLoan().toString(), "1000");
                assertEquals(client.getMaxLoanTime().toString(), "50");

                editClient = links[i * 4 - 1].click();
                form = editClient.getForms()[0];
                form.setParameter("name", "editTestName");
                form.setParameter("type", "editTestType");
                form.setParameter("address", "editTestAddress");
                editClient = wc.getResponse(form.getRequest(form.getSubmitButton("Edit")));
                break;
            }
            i += 1;
        }

        Client client = clientDAO.findById(id);
        assertEquals(client.getName(), "editTestName");
        assertEquals(client.getType(), "editTestType");
        assertEquals(client.getAddress(), "editTestAddress");
        assertEquals(client.getE_mail(), "testEmail");
        assertEquals(client.getPhone(), "testPhone");
        assertEquals(client.getBalance().toString(), "500");
        assertEquals(client.getMaxLoan().toString(), "1000");
        assertEquals(client.getMaxLoanTime().toString(), "50");

        //Adding service
        editClient = links[i * 4 - 2].click();
        WebLink addService = editClient.getLinks()[1];
        editClient = addService.click();
        form = editClient.getForms()[0];
        form.setParameter("id", "1");
        form.setParameter("startDate", "2021-05-26");
        form.setParameter("finishDate", "2022-05-26");
        form.setParameter("price", "159");
        editClient = wc.getResponse(form.getRequest(form.getSubmitButton("Add")));
        editClient = links[i * 4 - 2].click();
        table = editClient.getTables()[0].asText();
        assertEquals(table[1][0], "2021-05-26");
        assertEquals(table[1][1], "2022-05-26");
        assertEquals(table[1][2], "159");
        assertEquals(table[1][3], "Basic");

        //Adding receipt
        editClient = links[i * 4 - 3].click();
        WebLink addReceipt = editClient.getLinks()[1];
        editClient = addReceipt.click();
        form = editClient.getForms()[0];
        form.setParameter("receiptDate", "2021-05-26");
        form.setParameter("amount", "300");
        editClient = wc.getResponse(form.getRequest(form.getSubmitButton("Add")));
        editClient = links[i * 4 - 3].click();
        table = editClient.getTables()[0].asText();
        assertEquals(table[1][0], "2021-05-26");
        assertEquals(table[1][1], "300");

        links[i * 4].click();
        client = clientDAO.findById(id);
        assertTrue(client == null);
    }

    @Test
    public void seeAllServicesTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://localhost:8080/services");

        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
        Collection<Service> services = serviceDAO.findAll();

        WebLink links[] = resp.getLinks();

        assertEquals(links.length, services.size() + 2);
        assertEquals(links[0].getText(), "To main page");
        assertEquals(links[0].getURLString(), "/");

        String[][] table = resp.getTables()[0].asText();

        for (String[] row : table) {
            if (row[0].compareTo("Id") == 0)
                continue;
            Service service = serviceDAO.findById(Integer.parseInt(row[0]));
            assertEquals(Integer.parseInt(row[0]), service.getId());
            assertEquals(row[1], service.getName());
            assertEquals(Integer.parseInt(row[2]), service.getInternetAmount());
            assertEquals(Integer.parseInt(row[3]), service.getCallMinutes());
            assertEquals(Integer.parseInt(row[4]), service.getSmsAmount());
            assertEquals(row[5], service.getMonthPrice().toString());
        }
    }

    @Test
    public void addDeleteServiceTest() throws IOException, SAXException, SQLException {
        WebConversation wc = new WebConversation();
        WebResponse resp = wc.getResponse("http://localhost:8080/services");

        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
        Collection<Service> services = serviceDAO.findAll();
        resp = resp.getLinks()[services.size() + 1].click();

        WebForm form = resp.getForms()[0];

        form.setParameter("name", "testService");
        form.setParameter("InternetAmount", "1000");
        form.setParameter("callMinutes", "1001");
        form.setParameter("smsAmount", "1002");
        form.setParameter("monthPrice", "1003");
        resp = wc.getResponse(form.getRequest(form.getSubmitButton("Add")));

        assertEquals(resp.getURL().getPath(), "/services");

        String[][] table = resp.getTables()[0].asText();

        WebLink links[] = resp.getLinks();
        Integer id = 0, i = 0;
        for (String[] row : table) {
            if (row[1].compareTo("testService") == 0) {
                id = Integer.parseInt(row[0]);
                Service service = serviceDAO.findById(id);
                assertEquals(service.getName(), "testService");
                assertEquals(service.getInternetAmount().toString(), "1000");
                assertEquals(service.getCallMinutes().toString(), "1001");
                assertEquals(service.getSmsAmount().toString(), "1002");
                assertEquals(service.getMonthPrice().toString(), "1003");
                break;
            }
            i += 1;
        }
        links[i].click();
        Service service = serviceDAO.findById(id);
        assertTrue(service == null);
    }
}