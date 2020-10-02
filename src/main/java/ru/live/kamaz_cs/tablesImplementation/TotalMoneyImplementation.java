package ru.live.kamaz_cs.tablesImplementation;

import org.apache.commons.math3.util.Precision;
import ru.live.kamaz_cs.buildTables.CurrencyExchange;
import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTables.TotalMoney;
import ru.live.kamaz_cs.buildTables.Users;
import ru.live.kamaz_cs.buildTablesDAO.TotalMoneyDAO;

import javax.persistence.*;
import java.util.*;

public class TotalMoneyImplementation implements TotalMoneyDAO {

    private static final String GET_ALL_MONEYACCOUNTS = "select c from MoneyAccount c";
    private static final String GET_ALL_CURRENCY = "select c from CurrencyExchange c";
    private static final String GET_COLUMN_TOTALMONEY = "select c from TotalMoney c";
    private EntityManagerFactory emf;
    private EntityManager em = null;

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }

    @Override
    public void createTableOfTotalMoney(int userId) throws NoResultException, NullPointerException, NumberFormatException {
        if (userId <= 0) throw new NumberFormatException();
        double moneyInUAH = 0;
        CurrencyExchange currencyExchange = new CurrencyExchange();
        TotalMoney totalMoney = null;
        createEntityManager();
        em.getTransaction().begin();
        try {
            List<MoneyAccount> m = getAllMoneyAccounts();

            for (MoneyAccount f : m) {
                if (f.getId() == userId) {
                    moneyInUAH = Precision.round((f.getCashInUAH() + (f.getCashInUSD() * currencyExchange.getUsdForSaleinUAH()) + (f.getCashInEUR() * currencyExchange.getEurForSaleinUAH())), 2);
                    totalMoney = new TotalMoney(f.getId(), f.getNameOfUser(), moneyInUAH);
                    em.persist(totalMoney);
                }
            }
            em.getTransaction().commit();
        } catch (NullPointerException | NumberFormatException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<TotalMoney> getTotalMoney() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_COLUMN_TOTALMONEY, TotalMoney.class);
        return query.getResultList();
    }

    @Override
    public List<CurrencyExchange> getCurrencyExchange() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_CURRENCY, CurrencyExchange.class);
        return query.getResultList();
    }

    @Override
    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_MONEYACCOUNTS, MoneyAccount.class);
        return query.getResultList();
    }

}
