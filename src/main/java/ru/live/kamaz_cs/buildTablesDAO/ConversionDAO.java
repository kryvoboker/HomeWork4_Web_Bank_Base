package ru.live.kamaz_cs.buildTablesDAO;

import ru.live.kamaz_cs.buildTables.CurrencyExchange;
import ru.live.kamaz_cs.buildTables.MoneyAccount;

import javax.persistence.NoResultException;
import java.util.List;

public interface ConversionDAO {

    public void currencyConversion(int userId, String currencyOut, String currencyIn, double amount) throws NullPointerException, NumberFormatException;
    public List<CurrencyExchange> getCurrencyExchange() throws NoResultException;
    public void mergeMoneyAccount(MoneyAccount moneyAccount) throws NoResultException;

}
