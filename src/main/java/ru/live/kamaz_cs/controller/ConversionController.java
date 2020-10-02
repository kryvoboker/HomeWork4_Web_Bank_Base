package ru.live.kamaz_cs.controller;

import ru.live.kamaz_cs.buildTables.CurrencyExchange;
//import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.buildTablesDAO.ConversionDAO;

import javax.persistence.NoResultException;
import java.util.List;

public class ConversionController {

    private ConversionDAO conversionDAO;

    public ConversionController(ConversionDAO conversionDAO) {
        this.conversionDAO = conversionDAO;
    }

    public ConversionController() {
    }

    public ConversionDAO getConversionDAO() {
        return conversionDAO;
    }

    public void setConversionDAO(ConversionDAO conversionDAO) {
        this.conversionDAO = conversionDAO;
    }

    public List<CurrencyExchange> getCurrencyExchange() throws NoResultException {
        return conversionDAO.getCurrencyExchange();
    }
}
