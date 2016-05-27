package com.ejb;

//import com.model.Dish;
import com.model.Cart;
import com.model.Category;
import com.model.Dish;
import com.model.Ingredient;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
@Remote(MenuEditor.class)
public class MenuEditorBean implements MenuEditor {
	private static final Logger logger =
			Logger.getLogger(MenuEditorBean.class);

	@EJB MenuBox box;

	public String addDish(Dish dish) {
		box.addDish(dish);
		return "Success";
	}


	@Override
	public String editDish(Dish dish) {
		box.editDish(dish);
		return "Success";
	}

	@Override
	public String removeDish(int id) {
		box.removeDish(id);
		return "Success";
	}

	@Override
	public String addCategory(Category category) {
		box.addCategory(category);
		return "Success";

	}

	@Override
	public String editCategory(Category category) {
		box.editCategory(category);
		return "Success";
	}

	@Override
	public String removeCategory(int id) {
		box.removeCategory(id);
		return "Success";
	}
	@Override
	public String removeIngredient(int id) {
		box.removeIngredient(id);
		return "Success";
	}

	@Override
	public String editIngredient(Ingredient ingredient) {
		box.editIngredient(ingredient);
		return "Success";
	}

	@Override
	public void editCart(Cart cart) {
		box.editCart(cart);
	}

	@Override
	public Ingredient addIngredient(Ingredient ingredient) {
		return box.addIngredient(ingredient);

	}
}
