package com.ejb;


import com.model.Category;
import com.model.Dish;

import java.util.List;

public interface MenuInfo {
	public List<Dish> printDishes();
	public List<Category> printCategories();

}
