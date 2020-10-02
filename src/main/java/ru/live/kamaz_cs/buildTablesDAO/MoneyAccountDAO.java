package ru.live.kamaz_cs.buildTablesDAO;

import ru.live.kamaz_cs.buildTables.MoneyAccount;

import javax.persistence.NoResultException;
import java.util.List;

public interface MoneyAccountDAO {

    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException;
    public void createMoneyAccount(String name) throws NullPointerException;
    public void topUpAccount(int id, String currency, double amount) throws NullPointerException, NumberFormatException;
    public void mergeMoneyAccount(MoneyAccount moneyAccount) throws NoResultException;

}
