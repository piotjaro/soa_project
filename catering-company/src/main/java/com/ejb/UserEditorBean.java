package com.ejb;

import com.model.Cart;
import com.model.UserAccount;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;


@Stateful
@Remote(UserEditor.class)
public class UserEditorBean implements UserEditor {

    private static final Logger logger =
            Logger.getLogger(MenuEditorBean.class);

    @EJB
    UserBox box;

    @Override
    public void addUser(UserAccount user) {
        box.addUser(user);
    }

    @Override
    public void deleteUser(UserAccount user) {
        box.deleteUser(user);
    }

    @Override
    public void deleteCart(Cart cart) {
        box.deleteCart(cart);
    }

    @Override
    public void editUser(UserAccount user) {
        box.editUser(user);
    }

    @Override
    public void editCart(Cart cart) {
        box.editCart(cart);
    }

    public Cart addCart(Cart cart) {
       return box.addCart(cart);
    }
}
