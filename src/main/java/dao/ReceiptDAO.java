package dao;

import logic.*;

import java.util.List;

public interface ReceiptDAO {
    public Receipt findById(int id);

    public void save(Receipt receipt);

    public void update(Receipt receipt);

    public void delete(Receipt receipt);

    public List<Receipt> findAll();
}
