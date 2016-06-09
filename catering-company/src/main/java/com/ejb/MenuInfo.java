package com.ejb;


import com.model.Category;
import com.model.Dish;
import com.model.Ingredient;
import com.model.Menu;

import java.util.List;
import java.util.Set;

public interface MenuInfo {
	public List<Dish> getDishes();
	public List<Category> getCategories();
//	public List<Dish> getDishesFromCategory(int id);
	public Category getCategory(int id);
	public Dish getDish(int id);
	public Ingredient getIngredient(int id);
	public List<Ingredient> getIngredientFromDish(int id);
	public List<Menu> getAllMenu();
	public Menu getMenu(int id);
	public Menu getCurrentMenu();
	public List<Menu> getArchivedMenu();
	public List<Dish> getTopTenDishes();

}
