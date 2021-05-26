package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.*;
import logic.*;
import dao.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.sql.Date;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ModelAndView clients() {
        List<Client> clients = Factory.getInstance().getClientDAO().findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("clients");
        modelAndView.addObject("clientsList", clients);
        return modelAndView;
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public ModelAndView services() {
        List<Service> services = Factory.getInstance().getServiceDAO().findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("services");
        modelAndView.addObject("servicesList", services);
        return modelAndView;
    }

    @RequestMapping(value = "/receipts", method = RequestMethod.GET)
    public ModelAndView receipts() {
        List<Receipt> receipts = Factory.getInstance().getReceiptDAO().findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("receipts");
        modelAndView.addObject("receiptsList", receipts);
        return modelAndView;
    }

    @RequestMapping(value = "/receipts/{id}", method = RequestMethod.GET)
    public ModelAndView receipts(@PathVariable("id") int id) {
        List<Receipt> receipts = Factory.getInstance().getReceiptDAO().findAll();
        receipts.removeIf(r -> r.getClient().getId() != id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("receipts");
        modelAndView.addObject("receiptsList", receipts);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping(value = "/addReceipt/{id}", method = RequestMethod.GET)
    public ModelAndView addReceipt(@PathVariable("id") int id) {
        Receipt receipt = new Receipt();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addReceipt");
        modelAndView.addObject("receipt", receipt);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping(value = "/addReceipt/{id}", method = RequestMethod.POST)
    public ModelAndView addReceipt(@PathVariable("id") int id,
                                   @RequestParam Date receiptDate,
                                   @RequestParam BigDecimal amount) {
        ModelAndView modelAndView = new ModelAndView();
        ReceiptDAO receiptDAO = Factory.getInstance().getReceiptDAO();
        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        Receipt receipt = new Receipt(receiptDate, amount, clientDAO.findById(id));
        Client client = clientDAO.findById(id);
        client.setBalance(client.getBalance().add(amount));
        clientDAO.update(client);
        receiptDAO.save(receipt);
        modelAndView.setViewName("redirect:/clients");
        return modelAndView;
    }

    @RequestMapping(value = "/editClient/{id}", method = RequestMethod.GET)
    public ModelAndView editClient(@PathVariable("id") int id) {
        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        Client client = clientDAO.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editClient");
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @RequestMapping(value = "/editClient", method = RequestMethod.POST)
    public ModelAndView editClient(@RequestParam(required = false) Integer id,
                                   @RequestParam String name,
                                   @RequestParam String type,
                                   @RequestParam String address,
                                   @RequestParam String e_mail,
                                   @RequestParam String phone,
                                   @RequestParam BigDecimal balance,
                                   @RequestParam BigDecimal maxLoan,
                                   @RequestParam Integer maxLoanTime) {
        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        Client client = clientDAO.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/clients");
        client.setName(name);
        client.setType(type);
        client.setAddress(address);
        client.setE_mail(e_mail);
        client.setPhone(phone);
        client.setBalance(balance);
        client.setMaxLoan(maxLoan);
        client.setMaxLoanTime(maxLoanTime);
        clientDAO.update(client);
        return modelAndView;
    }

    @RequestMapping(value = "/serviceHistory/{id}", method = RequestMethod.GET)
    public ModelAndView editServices(@PathVariable("id") int id) {
        ServiceHistoryDAO serviceHistoryDAO = Factory.getInstance().getServiceHistoryDAO();
        List<ServiceHistory> serviceHistoryList = serviceHistoryDAO.findAll();
        serviceHistoryList.removeIf(r -> r.getClient().getId() != id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("serviceHistory");
        modelAndView.addObject("serviceHistoryList", serviceHistoryList);
        return modelAndView;
    }

    @RequestMapping(value = "/addContract/{id}", method = RequestMethod.GET)
    public ModelAndView addContract(@PathVariable("id") int id) {
        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
        List<Service> services = serviceDAO.findAll();
        ModelAndView modelAndView = new ModelAndView();
        ServiceHistory contract = new ServiceHistory();
        modelAndView.addObject("contract", contract);
        modelAndView.addObject("services", services);
        modelAndView.addObject("clientId", id);
        modelAndView.setViewName("addContract");
        return modelAndView;
    }

    @RequestMapping(value = "/addContract/{clientId}", method = RequestMethod.POST)
    public ModelAndView addContract(@PathVariable("clientId") int clientId,
                                    @RequestParam int id,
                                    @RequestParam Date startDate,
                                    @RequestParam Date finishDate,
                                    @RequestParam BigDecimal price) {
        ModelAndView modelAndView = new ModelAndView();
        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        ServiceHistoryDAO serviceHistoryDAO = Factory.getInstance().getServiceHistoryDAO();
        ServiceHistory serviceHistory = new ServiceHistory(startDate, finishDate, price,
                clientDAO.findById(clientId), serviceDAO.findById(id));
        serviceHistoryDAO.save(serviceHistory);
        modelAndView.setViewName("redirect:/clients");
        return modelAndView;
    }

    @RequestMapping(value = "/addClient", method = RequestMethod.GET)
    public ModelAndView addClient() {
        Client client = new Client();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addClient");
        modelAndView.addObject("client", client);
        return modelAndView;
    }

    @RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public ModelAndView addClient(@RequestParam String name,
                                  @RequestParam String type,
                                  @RequestParam String address,
                                  @RequestParam String e_mail,
                                  @RequestParam String phone,
                                  @RequestParam BigDecimal balance,
                                  @RequestParam BigDecimal maxLoan,
                                  @RequestParam Integer maxLoanTime) {
        ModelAndView modelAndView = new ModelAndView();
        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        Client client = new Client(type, name, address, phone, e_mail, balance, maxLoan, maxLoanTime);
        clientDAO.save(client);
        modelAndView.setViewName("redirect:/clients");
        return modelAndView;
    }

    @RequestMapping(value="/deleteClient/{id}", method = RequestMethod.GET)
    public ModelAndView deleteClient(@PathVariable("id") int id) {
        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/clients");
        Client client = clientDAO.findById(id);
        clientDAO.delete(client);
        return modelAndView;
    }

    @RequestMapping(value = "/addService", method = RequestMethod.GET)
    public ModelAndView addService() {
        Service service = new Service();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addService");
        modelAndView.addObject("service", service);
        return modelAndView;
    }

    @RequestMapping(value = "/addService", method = RequestMethod.POST)
    public ModelAndView addService(@RequestParam String name,
                                  @RequestParam Integer InternetAmount,
                                  @RequestParam Integer callMinutes,
                                  @RequestParam Integer smsAmount,
                                  @RequestParam BigDecimal monthPrice) {
        ModelAndView modelAndView = new ModelAndView();
        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
        Service service = new Service(name, InternetAmount, callMinutes, smsAmount, monthPrice);
        serviceDAO.save(service);
        modelAndView.setViewName("redirect:/services");
        return modelAndView;
    }

    @RequestMapping(value="/deleteService/{id}", method = RequestMethod.GET)
    public ModelAndView deleteService(@PathVariable("id") int id) {
        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/services");
        Service service = serviceDAO.findById(id);
        serviceDAO.delete(service);
        return modelAndView;
    }
/*    @RequestMapping(value = "/?clientId", method = RequestMethod.GET)
    public ModelAndView clientPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("services");
        return modelAndView;
    }*/
}
