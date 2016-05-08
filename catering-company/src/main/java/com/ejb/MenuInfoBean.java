package com.ejb;


import com.model.Dish;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;


@Stateless
@Remote(MenuInfo.class)
public class  MenuInfoBean implements MenuInfo  {
	@EJB MenuBox box;

	@Override
	public String printMenuList() {
		ArrayList<Dish> dishesList= box.getDishesList();
		StringBuffer sb = new StringBuffer();
		for (Dish dish: dishesList) {
			sb.append(dish.toString());
			sb.append("\n");
		}
		return sb.toString();


	}


}
