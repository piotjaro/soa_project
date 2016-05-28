package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 12.05.16.
 */
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return !(name != null ? !name.equals(category.name) : category.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}