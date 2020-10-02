package ru.live.kamaz_cs.servlet;

import ru.live.kamaz_cs.buildTables.CurrencyExchange;
import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.controller.ConversionController;
import ru.live.kamaz_cs.controller.MoneyAccountController;
import ru.live.kamaz_cs.tablesImplementation.ConversionImplementation;
import ru.live.kamaz_cs.tablesImplementation.MoneyAccountImplementation;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/exchanges")
public class ConversionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showMoneyAccounts(req);
            showTableOfCuccrency(req);
        } catch (NoResultException | NonUniqueResultException e) {
            showMsg(req, e.getMessage());
        }
        dispatcher(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NumberFormatException,
            NullPointerException {

        String sUserId = req.getParameter("userId");
        String currencyOut = req.getParameter("currencyOut");
        String currencyIn = req.getParameter("currencyIn");
        String sAmount = req.getParameter("amountExchange");
        if (sUserId.isEmpty() || currencyIn.isEmpty() || currencyOut.isEmpty() || sAmount.isEmpty()) {
            showMoneyAccounts(req);
            showTableOfCuccrency(req);
            showMsg(req, "Your fields are empty or incorrect");
            dispatcher(req, resp);
        }
        try {
            int userId = Integer.valueOf(sUserId);
            double amount = Double.valueOf(sAmount);

            currencyConversion(userId, currencyOut, currencyIn, amount);
            showMoneyAccounts(req);
            showTableOfCuccrency(req);
        } catch (NullPointerException | NumberFormatException e) {
            showMsg(req, e.getMessage());
        }
        dispatcher(req, resp);
    }

    public void dispatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/exchanges.jsp");
        dispatcher.forward(request, response);
    }

    private void showMoneyAccounts(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        MoneyAccountController controller = new MoneyAccountController(new MoneyAccountImplementation());
        List<MoneyAccount> moneyAccouts = controller.getAllMoneyAccounts();
        req.setAttribute("monAcc", moneyAccouts);
    }

    private void showTableOfCuccrency(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        ConversionController controller = new ConversionController(new ConversionImplementation());
        List<CurrencyExchange> currencyExchange = controller.getCurrencyExchange();
        req.setAttribute("currencyExc", currencyExchange);
    }

    private synchronized void currencyConversion(int userId, String currencyOut, String currencyIn, double amount)
            throws NullPointerException, NumberFormatException {
        ConversionImplementation implementation = new ConversionImplementation();
        implementation.currencyConversion(userId, currencyOut, currencyIn, amount);
    }

    private void showMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }

}
