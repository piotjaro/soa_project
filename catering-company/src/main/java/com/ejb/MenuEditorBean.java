package com.ejb;

import com.model.Dish;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
@Remote(MenuEditor.class)
public class MenuEditorBean implements MenuEditor {
//	private static final Logger logger =
//			Logger.getLogger(TheatreBookerBean.class);

	int money;
	@EJB MenuBox box;

	@PostConstruct
	public void createCustomer() {
		this.money=100;
	}


	@Override
	public void addDish(Dish dish) {
		box.addDish(dish);
	}
}
