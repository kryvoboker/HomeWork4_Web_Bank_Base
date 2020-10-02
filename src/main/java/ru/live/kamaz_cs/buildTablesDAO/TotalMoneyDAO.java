package ru.live.kamaz_cs.buildTablesDAO;

import ru.live.kamaz_cs.buildTables.CurrencyExchange;
import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTables.TotalMoney;
import ru.live.kamaz_cs.buildTables.Users;

import javax.persistence.NoResultException;
import java.util.List;

public interface TotalMoneyDAO {

    public List<CurrencyExchange> getCurrencyExchange() throws NoResultException;
    public List<MoneyAccount> getAllMoneyAccounts() throws NoResultException;
    public void createTableOfTotalMoney(int userId) throws NoResultException, NullPointerException, NumberFormatException;
    public List<TotalMoney> getTotalMoney() throws NoResultException;

}
