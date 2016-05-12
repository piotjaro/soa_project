package com.ejb;

//import com.model.Dish;

import com.model.Category;
import com.model.Dish;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;


@Singleton
@Startup
public class MenuBox {

    private List<Dish> dishes = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
    private static final Logger logger =
            Logger.getLogger(MenuBox.class);
//
//	@PostConstruct
//	public void setupMenu(){
//		dishesList = new ArrayList<Dish>();
//		int id = 0;
////		for (int i=0;i<5;i++) {
////			Dish dish = new Dish(++id, "Stalls", 40);
////			dishesList.add(dish);
////		}
//		logger.info("Menu constructed.");
//
//	}

    @Lock(READ)
    public List<Dish> getDishes() {
        return dishes;
    }

    @Lock(READ)
    public List<Category> getCategories() {
        return categories;
    }

    @Lock(READ)
    public double getDishPrice(int id) {

        return getDishes().get(id).getPrice();
    }

    @Lock(WRITE)
    public void addDish(Dish dish) {
        dishes.add(dish);
        Category category = dish.getCategory();
        if (!categories.contains(category))
            addCategory(dish.getCategory());
    }

    @Lock(WRITE)
    public void editDish(int id, Dish dish) {
        getDishes().add(id, dish);
        Category category = dish.getCategory();
        if (!categories.contains(category))
            addCategory(dish.getCategory());
    }

    @Lock(WRITE)
    public void removeDish(int id) {
        getDishes().remove(id);
    }

    @Lock(WRITE)
    public void addCategory(Category category) {
        logger.warn("category: " + category.toString());
        categories.add(category);
    }

    @Lock(WRITE)
    public String editCategory(int id, String category) {
        boolean flag=false;

        for (int i=0; i<categories.size(); i++) {
            if(categories.get(i).getId() == id) {
                categories.add(i, new Category(id, category));
                flag=true;
                break;
            }
        }
        for (int i=0; i<dishes.size(); i++) {
            if(dishes.get(i).getCategory().getId() == id) {
                dishes.get(i).setCategory(new Category(id, category));
            }
        }
        return flag ? "Success" : "Error";

    }

    @Lock(WRITE)
    public String removeCategory(int id) {
        boolean flag=false;

        for (int i=0; i<categories.size(); i++) {
            if(categories.get(i).getId() == id) {
                categories.remove(id);
                flag=true;
                break;
            }
        }
        for (int i=0; i<dishes.size(); i++) {
            if(dishes.get(i).getCategory().getId() == id) {
                dishes.add(i, null);
            }
        }

        return flag ? "Success" : "Error";

    }



}
