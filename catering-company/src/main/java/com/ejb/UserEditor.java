package com.ejb;

import com.model.Cart;
import com.model.UserAccount;

/**
 * Created by piotrek on 20.05.16.
 */
public interface UserEditor {

    public void addUser(UserAccount user);
    public void deleteUser(UserAccount user);
    public void deleteCart(Cart cart);
    public void editUser(UserAccount user);
    public void editCart(Cart cart);
    public Cart addCart(Cart cart);
}
