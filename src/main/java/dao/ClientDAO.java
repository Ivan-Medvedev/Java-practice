package dao;

import logic.*;

import java.util.List;

public interface ClientDAO {
    public Client findById(int id);

    public void save(Client client);

    public void update(Client client);

    public void delete(Client client);

    public Receipt findReceiptById(int id);

    public List<Client> findAll();
}