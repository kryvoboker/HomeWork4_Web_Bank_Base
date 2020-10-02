package ru.live.kamaz_cs.servlet;

import ru.live.kamaz_cs.buildTables.MoneyAccount;
import ru.live.kamaz_cs.controller.TransfareController;
import ru.live.kamaz_cs.other_exceprions.DuplicateException;
import ru.live.kamaz_cs.tablesImplementation.TransfareImplementation;

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

@WebServlet(urlPatterns = "/transfare")
public class TransfareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showMoneyAccounts(req);
        } catch (NoResultException | NonUniqueResultException e) {
            showMsg(req, e.getMessage());
        }
        dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sIdOut = req.getParameter("userIdOut");
        String sIdIn = req.getParameter("userIdIn");
        String currency = req.getParameter("currencyTransfare");
        String sAmount = req.getParameter("amountTransfare");
        try {
            if (sIdIn.isEmpty() || sIdOut.isEmpty() || currency.isEmpty() || sAmount.isEmpty()) {
                showMoneyAccounts(req);
                showMsg(req, "Your fields are empty or incorrect");
                dispatch(req, resp);
            }
            int idOut = Integer.valueOf(sIdOut);
            int idIn = Integer.valueOf(sIdIn);
            double amount = Double.valueOf(sAmount);
            if (idIn <= 0 || idOut <= 0) {
                showMoneyAccounts(req);
                showMsg(req, "Id does not exist");
                dispatch(req, resp);
            } else if (amount <= 0) {
                showMoneyAccounts(req);
                showMsg(req, "You don't have enough money");
                dispatch(req, resp);
            } else if (idOut == idIn) {
                try {
                    showMoneyAccounts(req);
                    throw new DuplicateException();
                } catch (DuplicateException e) {
                    showMsg(req, e.messageUserId());
                    dispatch(req, resp);
                }
            } else {
                transfare(idOut, idIn, currency, amount);
                showMoneyAccounts(req);
                dispatch(req, resp);
            }
        } catch (NullPointerException | NumberFormatException e) {
            showMsg(req, e.getMessage());
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/transfare.jsp");
        rd.forward(req, resp);
    }

    private void showMoneyAccounts(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        TransfareController transfareController = new TransfareController(new TransfareImplementation());
        List<MoneyAccount> moneyAccouts = transfareController.getAllMoneyAccounts();
        req.setAttribute("allMonAcc", moneyAccouts);
    }

    private synchronized void transfare(int idOut, int idIn, String currency, double amount) throws NumberFormatException, NullPointerException {
        TransfareImplementation implementation = new TransfareImplementation();
        implementation.moneyTransfer(idOut, idIn, currency, amount);
    }

    private void showMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }
}
