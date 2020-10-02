package ru.live.kamaz_cs.controller;

import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTablesDAO.MoneyAccountDAO;

import javax.persistence.NoResultException;
import java.util.List;

public class MoneyAccountController {

    private MoneyAccountDAO moneyAccountDAO;

    public MoneyAccountController(MoneyAccountDAO moneyAccountDAO) {
        this.moneyAccountDAO = moneyAccountDAO;
    }

    public MoneyAccountController() {
    }

    public MoneyAccountDAO getMoneyAccountDAO() {
        return moneyAccountDAO;
    }

    public void setMoneyAccountDAO(MoneyAccountDAO moneyAccountDAO) {
        this.moneyAccountDAO = moneyAccountDAO;
    }

    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException {
        return moneyAccountDAO.getAllMoneyAccounts();
    }

}
