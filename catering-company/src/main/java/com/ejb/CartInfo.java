package com.ejb;

import com.model.Cart;

import java.util.Date;
import java.util.List;

/**
 * Created by piotrek on 01.06.16.
 */
public interface CartInfo {
    public Cart getCart(int id);
    public List<Cart> getCarts();
//    public List<Cart> getNewCarts();
//    public List<Cart> getCancelledCarts();
//    public List<Cart> getReadyCarts();
//    public List<Cart> getFinishedCarts();
//    public List<Cart> getInProgressCarts();
//    public List<Cart> getInDeliveredCarts();
//    public List<Cart> getNewCartsByUserId(int id);
//    public List<Cart> getCancelledCartsByUserId(int id);
//    public List<Cart> getReadyCartsByUserId(int id);
//    public List<Cart> getFinishedCartsByUserId(int id);
//    public List<Cart> getInProgressCartsByUserId(int id);
//    public List<Cart> getInDeliveredCartsByUserId(int id);
    public List<Cart> getCartsByStatus(String status);
    public List<Cart> getCartsByStatusByUserId(int id, String status);
    public List<Cart> getSubscribedCartsByUserId(int id);
    public List<Cart> getCartsBetweenDate(Date date1, Date date2);

}
