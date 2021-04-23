package dao;

import logic.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    public Client findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public void save(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(client);
        tx1.commit();
        session.close();
    }

    public void update(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(client);
        tx1.commit();
        session.close();
    }

    public void delete(Client client) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(client);
        tx1.commit();
        session.close();
    }

    public Receipt findReceiptById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Receipt.class, id);
    }

    public List<Client> findAll() {
        List<Client> clients = (List<Client>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Client").list();
        return clients;
    }
}