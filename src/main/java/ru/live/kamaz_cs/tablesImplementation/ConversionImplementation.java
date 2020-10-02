package ru.live.kamaz_cs.tablesImplementation;

import org.apache.commons.math3.util.Precision;

import ru.live.kamaz_cs.buildTables.CurrencyExchange;
import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTables.Transaction;
import ru.live.kamaz_cs.buildTablesDAO.ConversionDAO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class ConversionImplementation implements ConversionDAO {

    private static final String GET_ALL_MONEYACCOUNTS = "select c from MoneyAccount c";
    private static final String GET_ALL_CURRENCY = "select c from CurrencyExchange c";
    private EntityManagerFactory emf;
    private EntityManager em = null;

    @Override
    public void currencyConversion(int userId, String currencyOut, String currencyIn, double amount) throws NullPointerException,
            NumberFormatException {

        if (currencyIn.isEmpty() || currencyOut.isEmpty()) {
            throw new NullPointerException();
        } else if (userId <= 0 || amount <= 0) {
            throw new NumberFormatException();
        }
        conversionUSDInUAH(userId, currencyOut, currencyIn, amount);
        conversionEURInUAH(userId, currencyOut, currencyIn, amount);
        conversionUAHInUSD(userId, currencyOut, currencyIn, amount);
        conversionUAHInEUR(userId, currencyOut, currencyIn, amount);
        conversionUSDInEUR(userId, currencyOut, currencyIn, amount);
        conversionEURInUSD(userId, currencyOut, currencyIn, amount);

    }

    private void conversionUSDInUAH(int userId, String currencyOut, String currencyIn, double amount) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;
        CurrencyExchange currencyExchange = new CurrencyExchange();
        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == userId) {
                if (f.getCashInEUR() != 0 || f.getCashInUAH() != 0 || f.getCashInUSD() != 0) { //проверка на наличие денег
                    if (currencyOut.equals("USD") && currencyIn.equals("UAH")) { //USD in UAH conversion
                        f.setCashInUAH(f.getCashInUAH() + Precision.round((amount * currencyExchange.getUahForBuyinUSD()), 2));
                        f.setCashInUSD(f.getCashInUSD() - amount);
                        mergeMoneyAccount(f);
                        transaction = new Transaction(f.getNameOfUser(), amount, currencyOut, amount * currencyExchange.getUahForBuyinUSD(), currencyIn, new Date(),
                                amount, currencyOut, currencyIn, Precision.round((amount * currencyExchange.getUahForBuyinUSD()), 2));
                        implementation = new TransactionImplementation();
                        implementation.addTransaction(transaction);
                    }
                }
            }
        }
    }

    private void conversionEURInUAH(int userId, String currencyOut, String currencyIn, double amount) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;
        CurrencyExchange currencyExchange = new CurrencyExchange();
        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == userId) {
                if (f.getCashInEUR() != 0 || f.getCashInUAH() != 0 || f.getCashInUSD() != 0) { //проверка на наличие денег
                    if (currencyOut.equals("EUR") && currencyIn.equals("UAH")) { //EUR in UAH conversion
                        f.setCashInUAH(f.getCashInUAH() + Precision.round((amount * currencyExchange.getUahForBuyEUR()), 2));
                        f.setCashInEUR(f.getCashInEUR() - amount);
                        mergeMoneyAccount(f);
                        transaction = new Transaction(f.getNameOfUser(), amount, currencyOut, amount * currencyExchange.getUahForBuyEUR(), currencyIn, new Date(),
                                amount, currencyOut, currencyIn, Precision.round((amount * currencyExchange.getUahForBuyEUR()), 2));
                        implementation = new TransactionImplementation();
                        implementation.addTransaction(transaction);
                    }
                }
            }
        }
    }

    public void conversionUAHInUSD(int userId, String currencyOut, String currencyIn, double amount) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;
        CurrencyExchange currencyExchange = new CurrencyExchange();
        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == userId) {
                if (f.getCashInEUR() != 0 || f.getCashInUAH() != 0 || f.getCashInUSD() != 0) { //проверка на наличие денег
                    if (currencyOut.equals("UAH") && currencyIn.equals("USD")) { //UAH in USD conversion
                        f.setCashInUSD(f.getCashInUSD() + (Precision.round(amount / currencyExchange.getUsdForSaleinUAH(), 2)));
                        f.setCashInUAH(f.getCashInUAH() - amount);
                        mergeMoneyAccount(f);
                        transaction = new Transaction(f.getNameOfUser(), amount, currencyOut, Precision.round(amount / currencyExchange.getUsdForSaleinUAH(), 2), currencyIn, new Date(),
                                amount, currencyOut, currencyIn, Precision.round(amount / currencyExchange.getUsdForSaleinUAH(), 2));
                        implementation = new TransactionImplementation();
                        implementation.addTransaction(transaction);
                    }
                }
            }
        }
    }

    private void conversionUAHInEUR(int userId, String currencyOut, String currencyIn, double amount) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;
        CurrencyExchange currencyExchange = new CurrencyExchange();
        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == userId) {
                if (f.getCashInEUR() != 0 || f.getCashInUAH() != 0 || f.getCashInUSD() != 0) { //проверка на наличие денег
                    if (currencyOut.equals("UAH") && currencyIn.equals("EUR")) { //UAH in EUR conversion
                        f.setCashInEUR(f.getCashInEUR() + (Precision.round(amount / currencyExchange.getEurForSaleinUAH(), 2)));
                        f.setCashInUAH(f.getCashInUAH() - amount);
                        mergeMoneyAccount(f);
                        transaction = new Transaction(f.getNameOfUser(), amount, currencyOut, Precision.round(amount / currencyExchange.getEurForSaleinUAH(), 2), currencyIn, new Date(),
                                amount, currencyOut, currencyIn, Precision.round(amount / currencyExchange.getEurForSaleinUAH(), 2));
                        implementation = new TransactionImplementation();
                        implementation.addTransaction(transaction);
                    }
                }
            }
        }
    }

    private void conversionUSDInEUR(int userId, String currencyOut, String currencyIn, double amount) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;
        CurrencyExchange currencyExchange = new CurrencyExchange();
        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == userId) {
                if (f.getCashInEUR() != 0 || f.getCashInUAH() != 0 || f.getCashInUSD() != 0) { //проверка на наличие денег
                    if (currencyOut.equals("USD") && currencyIn.equals("EUR")) { //USD in EUR conversion
                        f.setCashInEUR(f.getCashInEUR() + (Precision.round(amount * currencyExchange.getUsdForBuyinEUR(), 2)));
                        f.setCashInUSD(f.getCashInUSD() - amount);
                        mergeMoneyAccount(f);
                        transaction = new Transaction(f.getNameOfUser(), amount, currencyOut, Precision.round(amount / currencyExchange.getUsdForBuyinEUR(), 2), currencyIn, new Date(),
                                amount, currencyOut, currencyIn, Precision.round(amount / currencyExchange.getUsdForBuyinEUR(), 2));
                        implementation = new TransactionImplementation();
                        implementation.addTransaction(transaction);
                    }
                }
            }
        }
    }

    private void conversionEURInUSD(int userId, String currencyOut, String currencyIn, double amount) {
        Transaction transaction = null;
        TransactionImplementation implementation = null;
        CurrencyExchange currencyExchange = new CurrencyExchange();
        List<MoneyAccount> m = getAllMoneyAccounts();

        for (MoneyAccount f : m) {
            if (f.getId() == userId) {
                if (f.getCashInEUR() != 0 || f.getCashInUAH() != 0 || f.getCashInUSD() != 0) { //проверка на наличие денег
                    if (currencyOut.equals("EUR") && currencyIn.equals("USD")) { //EUR in USD conversion
                        f.setCashInUSD(f.getCashInUSD() + (Precision.round(amount * currencyExchange.getEurForBuyinUSD(), 2)));
                        f.setCashInEUR(f.getCashInEUR() - amount);
                        mergeMoneyAccount(f);
                        transaction = new Transaction(f.getNameOfUser(), amount, currencyOut, Precision.round(amount / currencyExchange.getEurForBuyinUSD(), 2), currencyIn, new Date(),
                                amount, currencyOut, currencyIn, Precision.round(amount / currencyExchange.getEurForBuyinUSD(), 2));
                        implementation = new TransactionImplementation();
                        implementation.addTransaction(transaction);
                    }
                }
            }
        }
    }

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }

    @Override
    public List<CurrencyExchange> getCurrencyExchange() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_CURRENCY, CurrencyExchange.class);
        return query.getResultList();
    }

    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_MONEYACCOUNTS, MoneyAccount.class);
        return query.getResultList();
    }

    @Override
    public void mergeMoneyAccount(MoneyAccount moneyAccount) throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_MONEYACCOUNTS, MoneyAccount.class);
        List<MoneyAccount> moneyAccounts = query.getResultList();
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

}
