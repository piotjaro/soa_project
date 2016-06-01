package com.ejb;

import com.model.Cart;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;


@Stateless
@Remote(CartInfo.class)
public class CartInfoBean implements CartInfo{

    @EJB
    CartBox cartBox;

    @Override
    public Cart getCart(int id) {
        return cartBox.getCart(id);
    }

    @Override
    public List<Cart> getCarts() {
        return cartBox.getCarts();
    }

    @Override
    public List<Cart> getNewCarts() {
        return cartBox.getNewCarts();
    }

    @Override
    public List<Cart> getCancelledCarts() {
        return cartBox.getCancelledCarts();
    }

    @Override
    public List<Cart> getReadyCarts() {
        return cartBox.getReadyCarts();
    }

    @Override
    public List<Cart> getFinishedCarts() {
        return cartBox.getFinishedCarts();
    }

    @Override
    public List<Cart> getInProgressCarts() {
        return cartBox.getInProgressCarts();
    }

    @Override
    public List<Cart> getInDeliveredCarts() {
        return cartBox.getInDeliveredCarts();
    }
}
