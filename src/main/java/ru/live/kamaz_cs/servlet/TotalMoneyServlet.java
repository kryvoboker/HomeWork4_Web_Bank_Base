package ru.live.kamaz_cs.servlet;

import ru.live.kamaz_cs.buildTables.CurrencyExchange;
import ru.live.kamaz_cs.buildTables.TotalMoney;
import ru.live.kamaz_cs.buildTables.Users;
import ru.live.kamaz_cs.controller.TotalMoneyController;
import ru.live.kamaz_cs.tablesImplementation.TotalMoneyImplementation;

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

@WebServlet(urlPatterns = "/totalMoney")
public class TotalMoneyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getTotalList(req);
            getTableOfCurrency(req);
            despatcher(req, resp);
        } catch (NoResultException | NonUniqueResultException e) {
            showMsg(req, e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sUserId = req.getParameter("userId");
        if (sUserId.isEmpty()) {
            getTableOfCurrency(req);
            getTotalList(req);
            showMsg(req, "Your fields is empty");
            despatcher(req, resp);
        }
        try {
            int userId = Integer.valueOf(sUserId);

            getTableOfCurrency(req);
            createTableOfTotalMoneyInUAH(userId);
            getTotalList(req);
        } catch (NullPointerException | NumberFormatException e) {
            showMsg(req, e.getMessage());
        }
        despatcher(req, resp);

    }

    public void despatcher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/totalMoney.jsp");
        dispatcher.forward(request, response);
    }

    private void getTableOfCurrency(HttpServletRequest request) throws NoResultException, NonUniqueResultException {
        TotalMoneyController controller = new TotalMoneyController(new TotalMoneyImplementation());
        List<CurrencyExchange> exchange = controller.getCurrencyExchange();
        request.setAttribute("currencyExc", exchange);
    }

    private void getTotalList(HttpServletRequest request) throws NoResultException, NonUniqueResultException {
        TotalMoneyController controller = new TotalMoneyController(new TotalMoneyImplementation());
        List<TotalMoney> totalMoney = controller.getTotalMoney();
        request.setAttribute("totalList", totalMoney);
    }

    private void createTableOfTotalMoneyInUAH(int userId) throws NullPointerException, NumberFormatException {
        TotalMoneyImplementation implementation = new TotalMoneyImplementation();
        implementation.createTableOfTotalMoney(userId);
    }

    private void showMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }
}
