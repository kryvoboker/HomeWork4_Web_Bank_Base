package ru.live.kamaz_cs.servlet;

import ru.live.kamaz_cs.buildTables.Users;
import ru.live.kamaz_cs.controller.UsersController;
import ru.live.kamaz_cs.tablesImplementation.MoneyAccountImplementation;
import ru.live.kamaz_cs.tablesImplementation.UsersImplementation;

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

@WebServlet(urlPatterns = "/add_user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            showUsers(req);
        } catch (NoResultException | NonUniqueResultException e) {
            showMsg(req, e.getMessage());
        }
        dispatchAddUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {
        String name = req.getParameter("nameUser");
        if (name.isEmpty()) {
            showUsers(req);
            showMsg(req, "Your field is empty!!!");
            dispatchAddUser(req, resp);
        }
        try {
            addNewUser(name);
            showUsers(req);
        } catch (NullPointerException e) {
            showMsg(req, e.getMessage());
        }
        dispatchAddUser(req, resp);
    }

    private void dispatchAddUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/add_user.jsp");
        rd.forward(req, resp);
    }

    private void addNewUser(String name) throws NullPointerException {
        MoneyAccountImplementation moneyAccountImplementation = new MoneyAccountImplementation();
        UsersImplementation usersImplementation = new UsersImplementation();
        usersImplementation.addNewUser(name);
        moneyAccountImplementation.createMoneyAccount(name);
    }

    private void showUsers(HttpServletRequest req) throws NoResultException, NonUniqueResultException {
        UsersController controller = new UsersController(new UsersImplementation());
        List<Users> users = controller.getAllUsers();
        req.setAttribute("allUsers", users);
    }

    private void showMsg(HttpServletRequest req, String msg) {
        req.setAttribute("error", true);
        req.setAttribute("error_msg", msg);
    }
}
