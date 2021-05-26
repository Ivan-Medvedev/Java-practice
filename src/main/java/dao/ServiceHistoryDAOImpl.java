package dao;

import logic.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class ServiceHistoryDAOImpl implements ServiceHistoryDAO {
    public ServiceHistory findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        ServiceHistory serviceHistory = session.get(ServiceHistory.class, id);
        session.close();
        return serviceHistory;
    }

    public void save(ServiceHistory serviceHistory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(serviceHistory);
        tx1.commit();
        session.close();
    }

    public void update(ServiceHistory serviceHistory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(serviceHistory);
        tx1.commit();
        session.close();
    }

    public void delete(ServiceHistory serviceHistory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(serviceHistory);
        tx1.commit();
        session.close();
    }

    public List<ServiceHistory> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<ServiceHistory> serviceHistoryList = (List<ServiceHistory>)  session.createQuery("From ServiceHistory").list();
        session.close();
        return serviceHistoryList;
    }
}
