package managedBean;

import com.model.Cart;
import com.model.Dish;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by piotr on 02.06.16.
 */

@ManagedBean
@ApplicationScoped
public class MoneySystem {
    private Initial initial = new Initial();

    public double paidMoney() {
        double result = 0.0;

        for(Cart cart : initial.getCartInfo().getCartsByStatus("Finished")){
            if(!cart.isPaidFromSalary()) result += cart.getCost();
        }
        return result;
    }


    public double paidFromSalary() {
        double result = 0.0;

        for(Cart cart : initial.getCartInfo().getCartsByStatus("Finished")){
            if(cart.isPaidFromSalary()) result += cart.getCost();
        }
        return result;
    }

    public double payToAnotherRestaurant() {
        double result = 0.0;

        for(Cart cart : initial.getCartInfo().getCartsByStatus("Finished")){
            for(Dish dish : cart.getDishes()){
                if(dish.getDishPriceFromRestaurant() != null) result += dish.getDishPriceFromRestaurant();
            }
        }
        return result;
    }


}
