package com.ejb;

import com.model.Cart;
import com.model.UserAccount;

import java.util.List;

/**
 * Created by piotrek on 20.05.16.
 */
public interface UserInfo {
    public List<UserAccount> getUsers();
    public UserAccount getUsers(int id);
    public UserAccount getUserByLogin(String login);
    public Cart getCart(int id);
    public List<Cart> getCarts();
    public List<Cart> getNewCarts();
    public List<Cart> getCancelledCarts();
    public List<Cart> getReadyCarts();
    public List<Cart> getFinishedCarts();
    public List<Cart> getInProgressCarts();

}
