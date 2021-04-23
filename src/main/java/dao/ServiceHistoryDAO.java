package dao;

import logic.*;

import java.util.List;

public interface ServiceHistoryDAO {
    public ServiceHistory findById(int id);

    public void save(ServiceHistory serviceHistory);

    public void update(ServiceHistory serviceHistory);

    public void delete(ServiceHistory serviceHistory);

    public List<ServiceHistory> findAll();
}
