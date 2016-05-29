package com.ejb;

import com.model.Cart;
import com.model.UserAccount;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by piotrek on 20.05.16.
 */

@Stateless
@Remote(UserInfo.class)
public class UserInfoBean implements UserInfo {

    @EJB
    UserBox box;

    @Override
    public List<UserAccount> getUsers() {
        return box.getUsers();
    }

    @Override
    public UserAccount getUsers(int id) {
        return box.getUser(id);
    }

    @Override
    public UserAccount getUserByLogin(String login) {
        return box.getUserByLogin(login);
    }

    @Override
    public Cart getCart(int id) {
        return box.getCart(id);
    }

    @Override
    public List<Cart> getCarts() {
        return box.getCarts();
    }

    @Override
    public List<Cart> getNewCarts() {
        return box.getNewCarts();
    }

    @Override
    public List<Cart> getCancelledCarts() {
        return box.getCancelledCarts();
    }

    @Override
    public List<Cart> getReadyCarts() {
        return box.getReadyCarts();
    }

    @Override
    public List<Cart> getFinishedCarts() {
        return box.getFinishedCarts();
    }

    @Override
    public List<Cart> getInProgressCarts() {
        return box.getInProgressCarts();
    }

}
