package com.ejb;

import com.model.Cart;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;
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
    public List<Cart> getCartsByStatus(String status) {
        return cartBox.getCartsByStatus(status);
    }

    @Override
    public List<Cart> getCartsByStatusByUserId(int userId, String status) {
        return cartBox.getCartsByUserId(userId, status);
    }
    @Override
    public List<Cart> getSubscribedCartsByUserId(int userId) {
        return cartBox.getSubscribedCartsByUserId(userId);
    }

//    @Override
//    public List<Cart> getNewCarts() {
//        return cartBox.getNewCarts();
//    }
//
//    @Override
//    public List<Cart> getCancelledCarts() {
//        return cartBox.getCancelledCarts();
//    }
//
//    @Override
//    public List<Cart> getReadyCarts() {
//        return cartBox.getReadyCarts();
//    }
//
//    @Override
//    public List<Cart> getFinishedCarts() {
//        return cartBox.getFinishedCarts();
//    }
//
//    @Override
//    public List<Cart> getInProgressCarts() {
//        return cartBox.getInProgressCarts();
//    }
//
//    @Override
//    public List<Cart> getInDeliveredCarts() {
//        return cartBox.getInDeliveredCarts();
//    }
//
//    @Override
//    public List<Cart> getNewCartsByUserId(int id) {
//        return cartBox.getNewCarts();
//    }
//
//    @Override
//    public List<Cart> getCancelledCartsByUserId(int id) {
//        return cartBox.getCancelledCarts();
//    }
//
//    @Override
//    public List<Cart> getReadyCartsByUserId(int id) {
//        return cartBox.getReadyCarts();
//    }
//
//    @Override
//    public List<Cart> getFinishedCartsByUserId(int id) {
//        return cartBox.getFinishedCarts();
//    }
//
//    @Override
//    public List<Cart> getInProgressCartsByUserId(int id) {
//        return cartBox.getInProgressCarts();
//    }
//
//    @Override
//    public List<Cart> getInDeliveredCartsByUserId(int id) {
//        return cartBox.getInDeliveredCarts();
//    }

    @Override
    public List<Cart> getCartsBetweenDate(Date date1, Date date2) {
        return cartBox.getCartsBetweenDate(date1, date2);
    }
}
