package ru.live.kamaz_cs.buildTablesDAO;

import ru.live.kamaz_cs.buildTables.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public interface TransactionDAO {

    public void addTransaction(Transaction transaction) throws NoResultException;
    public List<Transaction> getTransactions() throws NoResultException;

}
