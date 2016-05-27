package com.ejb;


import com.model.Cart;
import com.model.Category;
import com.model.Dish;
import com.model.Ingredient;
import org.hibernate.id.IncrementGenerator;

import java.util.List;

public interface MenuEditor {
   public String addDish(Dish dish);
   public Ingredient addIngredient(Ingredient ingredient);
   public String editDish(Dish dish);
   public void editCart(Cart cart );
   public String removeDish(int id);
   public String removeIngredient(int id);


   public String addCategory(Category category);
   public String editCategory(Category category);
   public String removeCategory(int id);
   public String editIngredient(Ingredient ingredient);

}