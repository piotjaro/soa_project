package com.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 08.05.16.
 */
@Entity
public class Dish implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    @OneToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
    private List<Ingredient> ingredients = new ArrayList<>();
    private String pathToPhoto;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }

    public void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                ", pathToPhoto='" + pathToPhoto + '\'' +
                '}';
    }
}
