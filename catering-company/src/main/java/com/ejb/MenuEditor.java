package com.ejb;


import com.model.*;
import org.hibernate.id.IncrementGenerator;

import java.util.List;

public interface MenuEditor {
   public String addDish(Dish dish);
   public String addMenu(Menu menu);
   public Ingredient addIngredient(Ingredient ingredient);
   public String editDish(Dish dish);
   public String  editMenu(Menu menu);
   public void editCart(Cart cart );
   public String removeDish(int id);
   public String removeMenu(int id);
   public String removeIngredient(int id);
   public void setCurrentMenu(Menu menu);


   public String addCategory(Category category);
   public String editCategory(Category category);
   public String removeCategory(int id);
   public String editIngredient(Ingredient ingredient);

}