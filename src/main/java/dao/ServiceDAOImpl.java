package dao;

import logic.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    public Service findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Service service = session.get(Service.class, id);
        session.close();
        return service;
    }

    public void save(Service service) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(service);
        tx1.commit();
        session.close();
    }

    public void update(Service service) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(service);
        tx1.commit();
        session.close();
    }

    public void delete(Service service) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(service);
        tx1.commit();
        session.close();
    }

    public List<Service> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        List<Service> services = (List<Service>)  session.createQuery("From Service").list();
        session.close();
        return services;
    }
}
