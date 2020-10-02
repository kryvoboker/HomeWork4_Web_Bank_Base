package ru.live.kamaz_cs.controller;

import ru.live.kamaz_cs.buildTables.CurrencyExchange;
import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTables.TotalMoney;
import ru.live.kamaz_cs.buildTables.Users;
import ru.live.kamaz_cs.buildTablesDAO.TotalMoneyDAO;

import javax.persistence.NoResultException;
import java.util.List;

public class TotalMoneyController {

    private TotalMoneyDAO totalMoneyDAO;

    public TotalMoneyController(TotalMoneyDAO totalMoneyDAO) {
        this.totalMoneyDAO = totalMoneyDAO;
    }

    public TotalMoneyController() {
    }

    public TotalMoneyDAO getTotalMoneyDAO() {
        return totalMoneyDAO;
    }

    public void setTotalMoneyDAO(TotalMoneyDAO totalMoneyDAO) {
        this.totalMoneyDAO = totalMoneyDAO;
    }

    public List<CurrencyExchange> getCurrencyExchange() throws NoResultException {
        return totalMoneyDAO.getCurrencyExchange();
    }
    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException {
        return totalMoneyDAO.getAllMoneyAccounts();
    }

    public List<TotalMoney> getTotalMoney() throws NoResultException {
        return totalMoneyDAO.getTotalMoney();
    }
}
