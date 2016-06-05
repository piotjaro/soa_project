package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 27.05.16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Menu.getAll", query = "Select m from Menu m"),
        @NamedQuery(name = "Menu.getById", query = "Select m from Menu m where m.id= ?1"),
        @NamedQuery(name = "Menu.getCurrentMenu", query = "Select m from Menu m where m.isCurrent=true"),
        @NamedQuery(name = "Menu.getArchivedMenu", query = "Select m from Menu m where m.isCurrent=false")

})
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
