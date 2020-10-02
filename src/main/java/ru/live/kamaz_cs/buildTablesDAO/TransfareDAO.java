package ru.live.kamaz_cs.buildTablesDAO;

import ru.live.kamaz_cs.buildTables.MoneyAccount;

import javax.persistence.NoResultException;
import java.util.List;

public interface TransfareDAO {

    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException;
    public void moneyTransfer(int idOut, int idIn, String currency, double amount) throws NullPointerException, NumberFormatException;
    public void mergeMoneyAccount(MoneyAccount moneyAccount) throws NoResultException;

}
