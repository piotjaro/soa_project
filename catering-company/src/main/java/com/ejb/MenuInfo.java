package com.ejb;


import com.model.Category;
import com.model.Dish;

import java.util.List;

public interface MenuInfo {
	public List<Dish> getDishes();
	public List<Category> getCategories();
	public List<Dish> getDishesFromCategory(int id);

}
