package ru.live.kamaz_cs.buildTablesDAO;

import ru.live.kamaz_cs.buildTables.Users;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface UsersDAO {

    public void createUsers() throws NullPointerException;
    public void addNewUser(String name) throws NullPointerException;
    public List<Users> getAllUsers() throws NoResultException;
    public Users getUserById(long user_id) throws NoResultException, NonUniqueResultException;
}
