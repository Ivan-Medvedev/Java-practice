package dao;

import logic.*;

import java.util.List;

public interface ServiceDAO {
    public Service findById(int id);

    public void save(Service service);

    public void update(Service service);

    public void delete(Service service);

    public List<Service> findAll();
}
