package com.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by piotrek on 18.05.16.
 */

@Entity
public class UserCatering {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String mail;
    private double debt;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Cart> historyCarts;
    @OneToMany(cascade = CascadeType.PERSIST)


    private List<Cart> actualCarts;

    public UserCatering() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public double getDebt() {
        double result = 0.0;
        for(Cart cart : actualCarts) {
            if(!cart.isPaid())
                result += cart.getCost();
        }
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public List<Cart> getHistoryCarts() {
        return historyCarts;
    }

    public void setHistoryCarts(List<Cart> historyCarts) {
        this.historyCarts = historyCarts;
    }

    public List<Cart> getActualCarts() {
        return actualCarts;
    }

    public void setActualCarts(List<Cart> actualCarts) {
        this.actualCarts = actualCarts;
    }
}
