package com.ejb;


//import com.model.Dish;

import com.model.Category;
import com.model.Dish;
import com.model.Ingredient;
import com.model.Menu;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;


@Stateless
@Remote(MenuInfo.class)
public class  MenuInfoBean implements MenuInfo  {
	@EJB MenuBox box;

//	@Override
//	public String printMenuList() {
//		ArrayList<Dish> dishesList= box.getDishesList();
//		StringBuffer sb = new StringBuffer();
//		for (Dish dish: dishesList) {
//			sb.append(dish.toString());
//			sb.append("\n");
//		}
//		return sb.toString();
//
//
//	}

	@Override
	public List<Dish> getDishes() {
		return box.getDishes();
	}

	@Override
	public List<Category> getCategories() {
		return box.getCategories();
	}

	@Override
	public List<Dish> getDishesFromCategory(int id) {
		return box.getDishesFromCategory(id);
	}

	@Override
	public Category getCategory(int id) {
		return box.getCategory(id);
	}

	@Override
	public Dish getDish(int id) {
		return box.getDish(id);
	}

	@Override
	public Ingredient getIngredient(int id) {
		return box.getIngredient(id);
	}

	@Override
	public List<Ingredient> getIngredientFromDish(int id) {
		return box.getIngredientFromDish(id);
	}

	@Override
	public List<Menu> getAllMenu() {
		return box.getAllMenu();
	}

	@Override
	public Menu getMenu(int id) {
		return box.getMenu(id);
	}
}
