package ru.live.kamaz_cs.tablesImplementation;

import ru.live.kamaz_cs.buildTables.Transaction;
import ru.live.kamaz_cs.buildTablesDAO.TransactionDAO;

import javax.persistence.*;
import java.util.List;

public class TransactionImplementation implements TransactionDAO {

    private static final String GET_TRANSACTIONS = "select c from Transaction c";
    private EntityManagerFactory emf;
    private EntityManager em = null;

    @Override
    public void addTransaction(Transaction transaction) throws NoResultException {
        createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }

    @Override
    public List<Transaction> getTransactions() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_TRANSACTIONS, Transaction.class);
        return query.getResultList();
    }

}
