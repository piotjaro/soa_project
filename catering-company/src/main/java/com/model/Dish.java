package com.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotrek on 08.05.16.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Dish.getAll", query = "Select d from Dish d")

})
public class Dish implements Serializable, Comparable<Dish> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double price;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients = new ArrayList<>();
    private String pathToPhoto;
    private Double dishPriceFromRestaurant;

    public Double getDishPriceFromRestaurant() {
        return dishPriceFromRestaurant;
    }

    public void setDishPriceFromRestaurant(Double dishPriceFromRestaurant) {
        this.dishPriceFromRestaurant = dishPriceFromRestaurant;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (Double.compare(dish.price, price) != 0) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        if (ingredients != null ? !ingredients.equals(dish.ingredients) : dish.ingredients != null) return false;
        if (pathToPhoto != null ? !pathToPhoto.equals(dish.pathToPhoto) : dish.pathToPhoto != null) return false;
        return !(dishPriceFromRestaurant != null ? !dishPriceFromRestaurant.equals(dish.dishPriceFromRestaurant) : dish.dishPriceFromRestaurant != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ingredients != null ? ingredients.hashCode() : 0);
        result = 31 * result + (pathToPhoto != null ? pathToPhoto.hashCode() : 0);
        result = 31 * result + (dishPriceFromRestaurant != null ? dishPriceFromRestaurant.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Dish dish) {
        return 1;
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