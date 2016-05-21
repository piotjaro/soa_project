package com.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by piotrek on 18.05.16.
 */
@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();
    private boolean isPaid;
    private Date dateOfReceipt;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
    private double cost;

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }


    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }
    public void removeDish(int id) {

        for(Dish dish: dishes) {
            if(dish.getId() == id) {
                removeDish(dish);
                return;
            }

        }
    }

    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getCost() {

        double result = 0.0;
        for(Dish dish : dishes){
            result += dish.getPrice();
        }

        return  result;
    }

}
