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


}
