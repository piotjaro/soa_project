package com.ejb;

import com.model.UserAccount;

/**
 * Created by piotrek on 20.05.16.
 */
public interface UserEditor {

    public void addUser(UserAccount user);
    public void deleteUser(UserAccount user);
    public void editUser(UserAccount user);
}
