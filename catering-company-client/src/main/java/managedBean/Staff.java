package managedBean;

import com.model.Cart;
import com.model.Dish;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@ManagedBean
@SessionScoped
public class Staff {
    private Initial initial = new Initial();

    public String changeCartStatus(Cart cart, String status) {
        cart.setStatus(status);
        initial.getUserEditor().editCart(cart);
        return "success";
    }

    public List<Cart> getAllCarts() {
        return initial.getUserInfo().getCarts();
    }

    public List<Cart> getNewCarts() {
        return initial.getUserInfo().getNewCarts();
    }


    public List<Cart> getCancelledCarts() {
        return initial.getUserInfo().getCancelledCarts();
    }


    public List<Cart> getReadyCarts() {
        return initial.getUserInfo().getReadyCarts();
    }


    public List<Cart> getFinishedCarts() {
        return initial.getUserInfo().getFinishedCarts();
    }


    public List<Cart> getInProgressCarts() {
        return initial.getUserInfo().getInProgressCarts();
    }

    public String changeStatus(Cart cart, String status) {
        cart.setStatus(status);
        initial.getUserEditor().editCart(cart);
        return "success";
    }

}
