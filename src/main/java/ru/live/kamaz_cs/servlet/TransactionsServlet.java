package ru.live.kamaz_cs.servlet;

import ru.live.kamaz_cs.buildTables.Transaction;
import ru.live.kamaz_cs.controller.TransactionController;
import ru.live.kamaz_cs.tablesImplementation.TransactionImplementation;

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

@WebServlet(urlPatterns = "/transactions")
public class TransactionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAllTransactions(req);
        dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showAllTransactions(req);
        dispatch(req, resp);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/transactions.jsp");
        rd.forward(req, resp);
    }

    private void showAllTransactions(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        TransactionController controller = new TransactionController(new TransactionImplementation());
        List<Transaction> transactionList = controller.getTransactions();
        req.setAttribute("transaction", transactionList);
    }
}
