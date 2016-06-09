package com.ejb;

import com.model.Address;
import com.model.Cart;
import com.model.Dish;
import com.model.UserAccount;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateful
@Remote(CartEditor.class)
public class CartEditorBean implements CartEditor{

    @EJB
    CartBox cartBox;

    @EJB
    UserBox userBox = new UserBox();

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

    @Override
    public void changeCartStatus(Cart cart, String status) {
        UserAccount user = new UserAccount();

        if(status.equals("FinishedCash")) {
            cart.setStatus("Finished");
            cartBox.editCart(cart);
            user = userBox.getUserByCart(cart.getId());
            if(!cart.getSubscribedType().equals("None"))
                createCartBySubscription(cart, user);


        } else if(status.equals("FinishedSalary")) {

            user = userBox.getUserByCart(cart.getId());
            cart.setStatus("Finished");
            cart.setPaidFromSalary(true);
            cartBox.editCart(cart);
            user.addDebt(cart.getCost());
            userBox.editUser(user);

            if(!cart.getSubscribedType().equals("None")){
                createCartBySubscription(cart, user);
            }


        } else if (status.equals("Cancelled")){
            cart.setStatus(status);
            cartBox.editCart(cart);
            if(!cart.getSubscribedType().equals("None")){
                user = userBox.getUserByCart(cart.getId());
                createCartBySubscription(cart, user);
            }

        } else {
            cart.setStatus(status);
            cartBox.editCart(cart);
        }


    }

    public void createCartBySubscription(Cart cart, UserAccount user) {
        cart.setId(0);
        cart.setStatus("New");
        if(cart.getSubscribedType().equals("Regular"))
            cart.setDateOfReceipt(createNextRegularSubscription(cart.getDateOfReceipt()));
        else if(cart.getSubscribedType().equals("Repeated"))
            cart.setDateOfReceipt(createNextRepeatedSubscription(cart.getSubscribedValue(), cart.getDateOfReceipt()));
        else if(cart.getSubscribedType().equals("OnDays")){
            cart.setDateOfReceipt(createNextOnDaysSubscription(cart.getSubscribedValue(), cart.getDateOfReceipt()));
        }
        Address address = cart.getAddress();
        address.setId(0);
        List<Dish> dishes = new ArrayList<>();
        for(Dish dish : cart.getDishes()) dishes.add(dish);
        cart.setDishes(dishes);
        cart = cartBox.addCart(cart);
        userBox.addCartToUser(user.getId(), cart);
    }

    public Date createNextRegularSubscription(Date date){
        LocalDateTime date1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        date1=date1.plusDays(1);
        return Date.from(date1.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date createNextRepeatedSubscription(String subscriptionValue, Date date) {
        LocalDateTime date1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        date1=date1.plusDays(Integer.parseInt(subscriptionValue));
        return Date.from(date1.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date createNextOnDaysSubscription(String subscriptionValue, Date date) {
        LocalDateTime date1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        date1=date1.plusDays(7);
        return Date.from(date1.atZone(ZoneId.systemDefault()).toInstant());
    }
}
