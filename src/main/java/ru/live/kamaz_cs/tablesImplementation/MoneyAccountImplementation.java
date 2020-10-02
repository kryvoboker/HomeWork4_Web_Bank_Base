package ru.live.kamaz_cs.tablesImplementation;

import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTables.Transaction;
import ru.live.kamaz_cs.buildTablesDAO.MoneyAccountDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class MoneyAccountImplementation implements MoneyAccountDAO {

    private static final String GET_ALL_MONEYACCOUNTS = "select c from MoneyAccount c";
    private EntityManagerFactory emf;
    private EntityManager em = null;

    @Override
    public void createMoneyAccount(String name) throws NullPointerException {
        if (name.isEmpty()) throw new NullPointerException();
        createEntityManager();
        em.getTransaction().begin();
        try {
            MoneyAccount moneyAccount = new MoneyAccount();
            moneyAccount.setNameOfUser(name);
            em.persist(moneyAccount);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void topUpAccount(int id, String currency, double amount) throws NullPointerException, NumberFormatException {
        if (id <= 0 || amount <= 0) throw new NumberFormatException();
        else if (currency.isEmpty()) throw new NullPointerException();

        Transaction transaction = null;
        TransactionImplementation implementation = null;
        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == id) {
                if (currency.equals("USD")) {
                    f.setCashInUSD(f.getCashInUSD() + amount);
                    mergeMoneyAccount(f);                                    //постоянно обновляю значения в таблице moneyaccounts
                } else if (currency.equals("EUR")) {
                    f.setCashInEUR(f.getCashInEUR() + amount);
                    mergeMoneyAccount(f);
                } else if (currency.equals("UAH")) {
                    f.setCashInUAH(f.getCashInUAH() + amount);
                    mergeMoneyAccount(f);
                }
                transaction = new Transaction(f.getNameOfUser(), 0, "none", amount, currency, new Date(), 0, "none",
                        "none", 0);
                implementation = new TransactionImplementation();
                implementation.addTransaction(transaction);
            }
        }
    }

    @Override
    public void mergeMoneyAccount(MoneyAccount moneyAccount) throws NoResultException {
        createEntityManager();
        List<MoneyAccount> moneyAccounts = getAllMoneyAccounts();
        em.getTransaction().begin();
        try {
            for (MoneyAccount f : moneyAccounts) {
                em.merge(f);
            }
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
    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_MONEYACCOUNTS, MoneyAccount.class);
        return query.getResultList();
    }
}
