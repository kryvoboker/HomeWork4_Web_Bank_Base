package ru.live.kamaz_cs.tablesImplementation;

import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTables.Transaction;
import ru.live.kamaz_cs.buildTablesDAO.TransfareDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class TransfareImplementation implements TransfareDAO {

    private static final String GET_ALL_MONEYACCOUNTS = "select c from MoneyAccount c";
    private EntityManagerFactory emf;
    private EntityManager em = null;

    @Override
    public void moneyTransfer(int idOut, int idIn, String currency, double amount) throws NullPointerException, NumberFormatException {

        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == idOut) {
                if (amount > 0 && (f.getCashInEUR() >= amount || f.getCashInUAH() >= amount || f.getCashInUSD() >= amount)) { //проверка на наличие денег и чтобы пользователь сам себе не пыталься переводить деньги
                    transferOut(idOut, amount, currency);
                    transferIn(idIn, amount, currency);
                }
            }
        }
    }

    private void transferOut(int idOut, double amount, String currency) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;

        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == idOut) {
                if (currency.equals("USD")) {
                    f.setCashInUSD(f.getCashInUSD() - amount);
                    mergeMoneyAccount(f);
                } else if (currency.equals("EUR")) {
                    f.setCashInEUR(f.getCashInEUR() - amount);
                    mergeMoneyAccount(f);
                } else if (currency.equals("UAH")) {
                    f.setCashInUAH(f.getCashInUAH() - amount);
                    mergeMoneyAccount(f);

                }
                transaction = new Transaction(f.getNameOfUser(), amount, currency, 0, "none", new Date(),
                        0, "none", "none", 0);
                implementation = new TransactionImplementation();
                implementation.addTransaction(transaction);
            }
        }
    }

    private void transferIn(int idIn, double amount, String currency) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;

        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == idIn) {
                if (currency.equals("USD")) {
                    f.setCashInUSD(f.getCashInUSD() + amount);
                    mergeMoneyAccount(f);
                } else if (currency.equals("EUR")) {
                    f.setCashInEUR(f.getCashInEUR() + amount);
                    mergeMoneyAccount(f);
                } else if (currency.equals("UAH")) {
                    f.setCashInUAH(f.getCashInUAH() + amount);
                    mergeMoneyAccount(f);

                }
                transaction = new Transaction(f.getNameOfUser(), 0, "none", amount, currency, new Date(),
                        0, "none", "none", 0);
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

    @Override
    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_MONEYACCOUNTS, MoneyAccount.class);
        return query.getResultList();
    }

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }
}
