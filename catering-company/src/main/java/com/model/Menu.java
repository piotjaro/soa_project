package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 27.05.16.
 */
@Entity
public class Menu implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private boolean isCurrent;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Dish dayDish = new Dish();
    @OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", isCurrent=" + isCurrent +
                ", dayDish=" + dayDish +
                ", categories=" + categories +
                '}';
    }
}
