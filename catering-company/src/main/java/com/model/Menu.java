package com.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 27.05.16.
 */
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private boolean isCurrent;
    @OneToOne
    private Dish dayDish = new Dish();
    @OneToMany(fetch=FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public Dish getDayDish() {
        return dayDish;
    }

    public void setDayDish(Dish dayDish) {
        this.dayDish = dayDish;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", isCurrent=" + isCurrent +
                ", dayDish=" + dayDish +
                ", dishes=" + dishes +
                '}';
    }
}
