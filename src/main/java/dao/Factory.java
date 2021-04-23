package dao;

import dao.*;
import logic.Service;
import logic.ServiceHistory;

public class Factory {

    private static ClientDAO clientDAO = null;
    private static ReceiptDAO receiptDAO = null;
    private static ServiceDAO serviceDAO = null;
    private static ServiceHistoryDAO serviceHistoryDAO = null;

    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public ClientDAO getClientDAO() {
        if (clientDAO == null) {
            clientDAO = new ClientDAOImpl();
        }
        return clientDAO;
    }

    public ReceiptDAO getReceiptDAO() {
        if (receiptDAO == null) {
            receiptDAO = new ReceiptDAOImpl();
        }
        return receiptDAO;
    }

    public ServiceDAO getServiceDAO() {
        if (serviceDAO == null) {
            serviceDAO = new ServiceDAOImpl();
        }
        return serviceDAO;
    }

    public ServiceHistoryDAO getServiceHistoryDAO() {
        if (serviceHistoryDAO == null) {
            serviceHistoryDAO = new ServiceHistoryDAOImpl();
        }
        return serviceHistoryDAO;
    }
}