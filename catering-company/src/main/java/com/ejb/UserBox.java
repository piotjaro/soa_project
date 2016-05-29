package com.ejb;

import com.model.Cart;
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
    public Cart addCart(Cart cart) {
        em.persist(cart);
        return cart;
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
    public List<Cart> getCarts() {
        Query q1 = em.createQuery("Select c from Cart c ORDER BY c.dateOfReceipt");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getNewCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='New' ORDER BY c.dateOfReceipt");
        return (List<Cart>)q1.getResultList();
    }
    @Lock(READ)
    public List<Cart> getCancelledCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='Cancelled' ORDER BY c.dateOfReceipt");
        return (List<Cart>)q1.getResultList();
    }


    @Lock(READ)
    public List<Cart> getInProgressCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='InProgress' ORDER BY c.dateOfReceipt");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getReadyCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='Ready' ORDER BY c.dateOfReceipt");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getFinishedCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='Finished' ORDER BY c.dateOfReceipt");
        return (List<Cart>)q1.getResultList();
    }


    @Lock(READ)
    public UserAccount getUser(int id) {

        return em.find(UserAccount.class, id);
    }
    @Lock(READ)
    public Cart getCart(int id) {
        Query q1 = em.createQuery("Select c from Cart c where c.id= '"+ id +"'");
        return (Cart)q1.getResultList().get(0);
    }

    @Lock(WRITE)
    public void editUser(UserAccount user) {

        for(Cart cart: user.getActualCarts()){
            if(cart.getId()==0)
                em.persist(cart);
            else
                em.merge(cart);
        }
        em.merge(user);
    }

    @Lock(WRITE)
    public void editCart(Cart cart) {
        em.merge(cart);
    }

    @Lock(READ)
    public UserAccount getUserByLogin(String login) {
        Query q1 = em.createQuery("Select u from UserAccount u where u.login= '"+ login +"'");
        return (UserAccount)q1.getResultList().get(0);
    }

    @Lock(WRITE)
    public void deleteCart(Cart cart) {
        em.remove(cart);
    }




}
