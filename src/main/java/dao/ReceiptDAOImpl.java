package dao;

import logic.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class ReceiptDAOImpl implements ReceiptDAO {
    public Receipt findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Receipt.class, id);
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
        List<Receipt> receipts = (List<Receipt>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Receipt").list();
        return receipts;
    }
}
