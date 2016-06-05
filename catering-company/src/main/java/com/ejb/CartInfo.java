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
    public List<Cart> getNewCarts();
    public List<Cart> getCancelledCarts();
    public List<Cart> getReadyCarts();
    public List<Cart> getFinishedCarts();
    public List<Cart> getInProgressCarts();
    public List<Cart> getInDeliveredCarts();
    public List<Cart> getCartsBetweenDate(Date date1, Date date2);
}
