package ru.live.kamaz_cs.controller;

import ru.live.kamaz_cs.buildTables.Transaction;
import ru.live.kamaz_cs.buildTablesDAO.TransactionDAO;

import javax.persistence.NoResultException;
import java.util.List;

public class TransactionController {

    private TransactionDAO transactionDAO;

    public TransactionController(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public TransactionController() {
    }

    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public void addTransaction(Transaction transaction) {
    }

    public List<Transaction> getTransactions() {
        return transactionDAO.getTransactions();
    }
}
