package com.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    @OneToMany(fetch=FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();
    private boolean paidFromSalary;
    private Date createDate;
    private Date dateOfReceipt;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address = new Address();
    private double cost;
    private String status;
    private String subscribedType;  //regular, repeated, onDays
    private String subscribedValue;
    private String addictionalInformation;


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

    public boolean isPaidFromSalary() {
        return paidFromSalary;
    }

    public void setPaidFromSalary(boolean paidFromSalary) {
        this.paidFromSalary = paidFromSalary;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSubscribedType() {
        return subscribedType;
    }

    public void setSubscribedType(String subscribedType) {
        this.subscribedType = subscribedType;
    }

    public String getSubscribedValue() {
        return subscribedValue;
    }

    public void setSubscribedValue(String subscribedValue) {
        this.subscribedValue = subscribedValue;
    }

    public String getAddictionalInformation() {
        return addictionalInformation;
    }

    public void setAddictionalInformation(String addictionalInformation) {
        this.addictionalInformation = addictionalInformation;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", dishes=" + dishes +
                ", paidFromSalary=" + paidFromSalary +
                ", dateOfReceipt=" + dateOfReceipt +
                ", address=" + address +
                ", cost=" + cost +
                ", status=" + status +
                '}';
    }
}
