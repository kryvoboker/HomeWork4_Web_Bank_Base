package ru.live.kamaz_cs.servlet;

import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.controller.MoneyAccountController;
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

@WebServlet(urlPatterns = "/top_up")
public class MoneyAccoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showMoneyAccounts(req);
            dispatch(req, resp);
        } catch (NoResultException | NonUniqueResultException e) {
            showMsg(req, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sUserId = req.getParameter("userId");
        String currency = req.getParameter("currency");
        String sAmount = req.getParameter("amount");
        if (sUserId.isEmpty() || currency.isEmpty() || sAmount.isEmpty()) {
            showMoneyAccounts(req);
            showMsg(req, "Your fields are empty or incorrect");
            dispatch(req, resp);
        }
        try {
            int userId = Integer.valueOf(sUserId);
            double amount = Double.valueOf(sAmount);

            topUpAccount(userId, currency, amount);
            showMoneyAccounts(req);
        } catch (NullPointerException | NumberFormatException e) {
            showMsg(req, e.getMessage());
        }
        dispatch(req, resp);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/top_up.jsp");
        rd.forward(req, resp);
    }

    private void showMoneyAccounts(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        MoneyAccountController controller = new MoneyAccountController(new MoneyAccountImplementation());
        List<MoneyAccount> moneyAccouts = controller.getAllMoneyAccounts();
        req.setAttribute("allMonAcc", moneyAccouts);
    }

    private synchronized void topUpAccount(int id, String currency, double amount) throws NumberFormatException, NullPointerException {
        MoneyAccountImplementation implementation = new MoneyAccountImplementation();
        implementation.topUpAccount(id, currency, amount);
    }

    private void showMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }
}
