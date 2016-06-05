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
    public void editUser(UserAccount user) {
        box.editUser(user);
    }

    @Override
    public void addDebt(UserAccount user, double debtToAdd) {
        user.addDebt(debtToAdd);
        editUser(user);
    }

    @Override
    public void addCartToUser(int userId, Cart cart){
        box.addCartToUser(userId, cart);
    }
}
