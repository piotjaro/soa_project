package com.ejb;

import com.model.Dish;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;


@Singleton
@Startup
public class MenuBox {

	private ArrayList<Dish> dishesList;
//	private static final Logger logger =
//			Logger.getLogger(TheatreBox.class);

	@PostConstruct
	public void setupTheatre(){
		dishesList = new ArrayList<Dish>();
		int id = 0;
		for (int i=0;i<5;i++) {
			Dish dish = new Dish(++id, "Stalls", 40);
			dishesList.add(dish);
		}
//		logger.info("Seat Map constructed.");
	 
	}

	@Lock(READ)  
	public ArrayList<Dish> getDishesList() {

		return dishesList;
	}
	@Lock(READ)  
	public int getDishPrice(int id) {

		return getDishesList().get(id).getPrice();
	}

	@Lock(WRITE)  
	public void addDish(Dish dish ){
		dishesList.add(dish);


	}

}
