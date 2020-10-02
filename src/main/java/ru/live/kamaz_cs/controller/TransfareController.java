package ru.live.kamaz_cs.controller;

import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTablesDAO.TransfareDAO;

import javax.persistence.NoResultException;
import java.util.List;

public class TransfareController {

    private TransfareDAO transfareDao;

    public TransfareController(TransfareDAO transfareDao) {
        this.transfareDao = transfareDao;
    }

    public TransfareController() {
    }

    public TransfareDAO getTransfareDao() {
        return transfareDao;
    }

    public void setTransfareDao(TransfareDAO transfareDao) {
        this.transfareDao = transfareDao;
    }

    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException {
        return transfareDao.getAllMoneyAccounts();
    }

}
