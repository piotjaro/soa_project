package com.ejb;


import com.model.Category;
import com.model.Dish;
import com.model.Ingredient;

import java.util.List;

public interface MenuEditor {
   public String addDish(Dish dish);
   public String editDish(int Id, Dish dish);
   public String removeDish(int Id);


   public String addCategory(Category category);
   public String editCategory(int id, String category);
   public String removeCategory(int id);

}