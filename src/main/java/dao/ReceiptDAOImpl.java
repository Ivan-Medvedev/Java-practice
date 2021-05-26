package dao;

import logic.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {
    public Receipt findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Receipt receipt = session.get(Receipt.class, id);
        session.close();
        return receipt;
    }

    public void save(Receipt receipt) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(receipt);
        tx1.commit();
        session.close();
    }

    public void update(Receipt receipt) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(receipt);
        tx1.commit();
        session.close();
    }

    public void delete(Receipt receipt) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(receipt);
        tx1.commit();
        session.close();
    }

    public List<Receipt> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Receipt> receipts = (List<Receipt>)  session.createQuery("From Receipt").list();
        session.close();
        return receipts;
    }
}
