package com.ejb;

import com.model.Cart;

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
 * Created by piotrek on 01.06.16.
 */

@Singleton
@Startup
public class CartBox {
    @PersistenceContext(name="catering-company")
    private EntityManager em;


    @Lock(WRITE)
    public Cart addCart(Cart cart) {
        em.persist(cart);
        return cart;
    }

    @Lock(READ)
    public List<Cart> getCarts() {
        Query q1 = em.createQuery("Select c from Cart c ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getNewCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='New' ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }
    @Lock(READ)
    public List<Cart> getCancelledCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='Cancelled' ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }


    @Lock(READ)
    public List<Cart> getInProgressCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='InProgress' ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getReadyCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='Ready' ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getFinishedCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='Finished' ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getReadyToDeliveredCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='Ready' and c.address.city <> '' ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public List<Cart> getInDeliveredCarts() {
        Query q1 = em.createQuery("Select c from Cart c where c.status='InDelivery' ORDER BY c.dateOfReceipt DESC");
        return (List<Cart>)q1.getResultList();
    }

    @Lock(READ)
    public Cart getCart(int id) {
        Query q1 = em.createQuery("Select c from Cart c where c.id= '"+ id +"'");
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
}
