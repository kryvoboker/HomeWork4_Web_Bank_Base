package ru.live.kamaz_cs.tablesImplementation;

import ru.live.kamaz_cs.buildTables.Users;
import ru.live.kamaz_cs.buildTablesDAO.UsersDAO;

import javax.persistence.*;
import java.util.List;

public class UsersImplementation implements UsersDAO {

    private static final String GET_ALL_USERS = "select c from Users c";
    private EntityManagerFactory emf;
    private EntityManager em = null;

    @Override
    public void createUsers() throws NullPointerException {
        Users usersOne = new Users("Ivan");
        Users usersTwo = new Users("Nastia");

        createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(usersOne);
            em.persist(usersTwo);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void addNewUser(String name) throws NullPointerException {
        if (name.isEmpty()) {
            throw new NullPointerException();
        }
        Users users = new Users(name);
        createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(users);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Users> getAllUsers() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_USERS, Users.class);
        return query.getResultList();
    }

    @Override
    public Users getUserById(long user_id) throws NoResultException, NonUniqueResultException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Study");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        return em.find(Users.class, user_id);
    }

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }
}
