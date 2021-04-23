package dao;

import logic.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;
import java.util.List;

public class ServiceDAOImpl implements ServiceDAO {
    public Service findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Service.class, id);
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
        List<Service> services = (List<Service>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Service").list();
        return services;
    }
}
