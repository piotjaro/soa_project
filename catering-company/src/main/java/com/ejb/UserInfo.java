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

}
