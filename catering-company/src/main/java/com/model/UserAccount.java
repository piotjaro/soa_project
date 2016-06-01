package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 18.05.16.
 */

@Entity
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String login;
    private String password;
    private String role;
    private String name;
    private String surname;
    private String mail;
    private double debt;
    @OneToMany(fetch=FetchType.EAGER)
    private List<Cart> actualCarts = new ArrayList<>();

    public UserAccount() {
    }

    public void addCart(Cart cart) {
        actualCarts.add(cart);
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }


    public List<Cart> getActualCarts() {
        return actualCarts;
    }

    public void setActualCarts(List<Cart> actualCarts) {
        this.actualCarts = actualCarts;
    }

    public void addDebt(double debtFromCart) {
        debt += debtFromCart;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                ", debt=" + debt +
                ", actualCarts=" + actualCarts +
                '}';
    }
}
