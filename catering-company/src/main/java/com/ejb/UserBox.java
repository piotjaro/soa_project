package com.ejb;

import com.model.UserAccount;
import org.apache.log4j.Logger;

import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

/**
 * Created by piotrek on 18.05.16.
 */
@Singleton
@Startup
public class UserBox {
    private static final Logger logger = Logger.getLogger(MenuBox.class);
    @PersistenceContext(name="catering-company")
    private EntityManager em;

    @Lock(WRITE)
    public void addUser(UserAccount user) {
        em.persist(user);
    }

    @Lock(WRITE)
    public void deleteUser(UserAccount user) {
        em.remove(user);
    }

    @Lock(READ)
    public List<UserAccount> getUsers() {
        Query q1 = em.createQuery("Select u from UserAccount u");
        return (List<UserAccount>)q1.getResultList();
    }

    @Lock(READ)
    public UserAccount getUser(int id) {

        return em.find(UserAccount.class, id);
    }

    @Lock(WRITE)
    public void editUser(UserAccount user) {
        em.merge(user);
    }

    @Lock(READ)
    public UserAccount getUserByLogin(String login) {
        Query q1 = em.createQuery("Select u from UserAccount u where u.login= '"+ login +"'");
        return (UserAccount)q1.getResultList().get(0);
    }
}
