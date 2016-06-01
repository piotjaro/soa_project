package com.ejb;

import com.model.Cart;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(CartEditor.class)
public class CartEditorBean implements CartEditor{

    @EJB
    CartBox cartBox;

    @Override
    public void deleteCart(Cart cart) {
        cartBox.deleteCart(cart);
    }


    @Override
    public void editCart(Cart cart) {
        cartBox.editCart(cart);
    }

    @Override
    public Cart addCart(Cart cart) {
        return cartBox.addCart(cart);
    }
}
