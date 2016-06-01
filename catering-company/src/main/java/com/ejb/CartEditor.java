package com.ejb;

import com.model.Cart;

/**
 * Created by piotrek on 01.06.16.
 */
public interface CartEditor {
    public Cart addCart(Cart cart);
    public void editCart(Cart cart);
    public void deleteCart(Cart cart);
}
