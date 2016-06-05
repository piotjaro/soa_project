package com.ejb;

import com.model.Cart;

import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Date;
import java.util.List;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

/**
 * Created by piotrek on 01.06.16.
 */

@Singleton
@Startup
public class CartBox {
    @PersistenceContext(name="catering-company")
    private EntityManager em;

    UserBox userBox = new UserBox();


    @Lock(WRITE)
    public Cart addCart(Cart cart) {
        em.persist(cart);
        return cart;
    }

    @Lock(READ)
    public List<Cart> getCarts() {
        Query q1 = em.createNamedQuery("Cart.getAll");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getNewCarts() {
        Query q1 = em.createNamedQuery("Cart.getAllByStatus");
        q1.setParameter(1, "New");
        return (List<Cart>)q1.getResultList();
    }
    @Lock(READ)
    public List<Cart> getCancelledCarts() {
        Query q1 = em.createNamedQuery("Cart.getAllByStatus");
        q1.setParameter(1, "Cancelled");
        return (List<Cart>)q1.getResultList();
    }


    @Lock(READ)
    public List<Cart> getInProgressCarts() {
        Query q1 = em.createNamedQuery("Cart.getAllByStatus");
        q1.setParameter(1, "InProgress");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getReadyCarts() {
        Query q1 = em.createNamedQuery("Cart.getAllByStatus");
        q1.setParameter(1, "Ready");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getFinishedCarts() {
        Query q1 = em.createNamedQuery("Cart.getAllByStatus");
        q1.setParameter(1, "Finished");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getReadyToDeliveredCarts() {
        Query q1 = em.createNamedQuery("Cart.getReadyToDelivered");
        return (List<Cart>)q1.getResultList();
    }

    public List<Cart> getCartsBetweenDate(Date date1, Date date2) {
//        Query q1 = em.createNamedQuery("Cart.betweenDate");
//        q1.setParameter(1, date1);
//        q1.setParameter(2, date2);
//        return (List<Cart>)q1.getResultList();
        return null;
    }

    @Lock(READ)
    public List<Cart> getInDeliveredCarts() {
        Query q1 = em.createNamedQuery("Cart.getAllByStatus");
                q1.setParameter(1, "InDelivery");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public Cart getCart(int id) {
        Query q1 = em.createNamedQuery("Cart.getById");
        q1.setParameter(1, id);
        return (Cart)q1.getResultList().get(0);
    }

    @Lock(WRITE)
    public void deleteCart(Cart cart) {
        em.remove(cart);
    }


    @Lock(WRITE)
    public void editCart(Cart cart) {
        em.merge(cart);
    }

//    @Lock(WRITE)
//    public void changeCartStatus(Cart cart, String status) {
//        if(status.equals("FinishedCash")) {
//            if(!cart.getSubscribedType().equals("None"))
//        }
//    }
}
