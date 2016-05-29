package managedBean;

import com.model.Cart;
import com.model.Dish;
import com.model.UserAccount;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;

/**
 * Created by piotrek on 28.05.16.
 */

@ManagedBean
@SessionScoped
public class Client {

    private UserAccount user;
    private Initial initial = new Initial();
    private Cart cart = new Cart();

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String goToUserPanel() {
        user =  initial.getUserInfo().getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return "userPanel";
    }

    public String removeDishFromCart(int dishId, int cartId) {
        Cart cartTmp = initial.getUserInfo().getCart(cartId);
        cartTmp.removeDish(dishId);
        initial.getUserEditor().editCart(cartTmp);
        return "success";
    }

    public String payCash(int userId, int cartId) {
        UserAccount userAccount = initial.getUserInfo().getUserByLogin(initial.getUserInfo().getUsers(userId).getLogin());
        List<Cart> carts = userAccount.getActualCarts();
        for(int i = 0; i<carts.size(); i++) {
            if(carts.get(i).getId() == cartId) {
                Cart cartTmp = carts.get(i);
                cartTmp.setPaidFromSalary(false);
                initial.getMenu().editCart(cartTmp);
                break;
            }
        }
        return "success";
    }

    public String addToCart(Dish dish) {
        cart.addDish(dish);
        return "success";
    }

    public String addToModyfiedCart(Dish dish) {
        cart.addDish(dish);
        return "Success";
    }

    public String addCartToUser() {
        cart.setCreateDate(new Date());
        if(cart.getId() == 0) {
            UserAccount userAccount = initial.getUserInfo().getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            cart.setStatus("New");
            userAccount.addCart(cart);
            initial.getUserEditor().editUser(userAccount);
        } else {
            initial.getUserEditor().editCart(cart);
        }

        cart = new Cart();
        return "success";
    }

    public String removeDishFromCart(int id) {
        cart.removeDish(id);
        return "success";
    }

    public String cancelCart(int userId, int cartId) {

        UserAccount userAccount = initial.getUserInfo().getUserByLogin(initial.getUserInfo().getUsers(userId).getLogin());
        List<Cart> carts = userAccount.getActualCarts();
        for(int i = 0; i<carts.size(); i++) {
            if(carts.get(i).getId() == cartId) {
                Cart cartTmp = carts.get(i);
                cartTmp.setStatus("Cancelled");
                initial.getMenu().editCart(cartTmp);
                break;
            }
        }
        return "success";
    }

    public String payFromSalary(int userId, int cartId) {

        UserAccount userAccount = initial.getUserInfo().getUserByLogin(initial.getUserInfo().getUsers(userId).getLogin());
        List<Cart> carts = userAccount.getActualCarts();
        for(int i = 0; i<carts.size(); i++) {
            if(carts.get(i).getId() == cartId) {
                Cart cartTmp = carts.get(i);
                cartTmp.setPaidFromSalary(true);
                initial.getMenu().editCart(cartTmp);
                break;
            }
        }
        return "success";
    }

    public String goToEditCart(Cart cart1) {
        cart = cart1;
        return "showCart";
    }

    public String cancelCart(){
        cart = new Cart();
        return "success";
    }



}
