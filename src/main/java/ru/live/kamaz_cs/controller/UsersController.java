package ru.live.kamaz_cs.controller;

import ru.live.kamaz_cs.buildTables.Users;
import ru.live.kamaz_cs.buildTablesDAO.UsersDAO;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public class UsersController {

    private UsersDAO usersDAO;

    public UsersController(UsersDAO userDAO) {
        super();
        this.usersDAO = userDAO;
    }

    public UsersController() {
        super();
    }

    public UsersDAO getUserDAO() {
        return usersDAO;
    }

    public void setUserDAO(UsersDAO userDAO) {
        this.usersDAO = userDAO;
    }

    public List<Users> getAllUsers() throws NoResultException {
        return usersDAO.getAllUsers();
    }

    public Users getUserById(long user_id) throws NoResultException, NonUniqueResultException {
        return usersDAO.getUserById(user_id);
    }

}
