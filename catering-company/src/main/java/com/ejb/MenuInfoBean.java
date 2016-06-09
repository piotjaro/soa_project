package com.ejb;


//import com.model.Dish;

import com.google.common.collect.Lists;
import com.model.*;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Stateless
@Remote(MenuInfo.class)
public class  MenuInfoBean implements MenuInfo  {
	@Resource(authenticationType= Resource.AuthenticationType.CONTAINER)
	private SessionContext context;
	@EJB MenuBox box;
	@EJB CartBox cartBox;

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

	@WebMethod(operationName = "getCategories")
	@Override
	public List<Category> getCategories() {
		return box.getCategories();
	}

//	@Override
//	public List<Dish> getDishesFromCategory(int id) {
//		return box.getDishesFromCategory(id);
//	}

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
	@Override
	public Menu getCurrentMenu() {
		String currentUser = context.getCallerPrincipal().getName();
		return box.getCurrentMenu();
	}

	public List<Menu> getArchivedMenu() {
		return box.getArchivedMenu();
	}


	public List<Dish> getTopTenDishes(){
		List<Cart> allCarts = cartBox.getCarts();
		Map<Dish, Integer> numberOfDish = new HashMap<>();
		for(Cart cart : allCarts) {
			for(Dish dish : cart.getDishes()){
				if(numberOfDish.containsKey(dish)) {
					numberOfDish.put(dish, numberOfDish.get(dish) + 1);
				}else
					numberOfDish.put(dish, 0);
			}
		}

		List<Dish> dishes =  numberOfDish.entrySet().stream()
				.sorted(Comparator.comparing((Map.Entry::getValue)))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		Collections.reverse(dishes);
		if(dishes.size()>=10)
			return dishes.stream().limit(10).collect(Collectors.toList());
		else return dishes;

	}
}
