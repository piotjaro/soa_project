package managedBean;

import com.model.Cart;
import com.model.Dish;
import com.model.UserAccount;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        Cart cartTmp = initial.getCartInfo().getCart(cartId);
        cartTmp.removeDish(dishId);
        initial.getCartEditor().editCart(cartTmp);
        return "/all/success.xhtml";

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
        return "/all/success.xhtml";
    }

    public String addToCart(Dish dish) {
        cart.addDish(dish);
        return "/all/success.xhtml";
    }

    public String addToModyfiedCart(Dish dish) {
        cart.addDish(dish);
        return "/all/success.xhtml";

    }

    public String addCartToUser() {
        cart.setCreateDate(new Date());
        if(cart.getId() == 0) {
            UserAccount userAccount = initial.getUserInfo().getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            cart.setStatus("New");
            userAccount.addCart(cart);
            initial.getUserEditor().editUser(userAccount);
        } else {
            initial.getCartEditor().editCart(cart);
        }

        cart = new Cart();
        return "/all/success.xhtml";
    }

    public String removeDishFromCart(int id) {
        cart.removeDish(id);
        return "/all/success.xhtml";

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
        return "/all/success.xhtml";
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
        return "/all/success.xhtml";
    }

    public String goToEditCart(Cart cart1) {
        cart = cart1;
        return "showCart";
    }

    public String cancelCart(){
        cart = new Cart();
        return "/all/success.xhtml";
    }

    public UserAccount getUserFromDatabase() {
        return initial.getUserInfo().getUserByLogin(user.getLogin());
    }

    public List<Cart> getCartsCurrentMoth() {
        return initial.getUserInfo().getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser())
                .getActualCarts().stream().filter(c -> c.getDateOfReceipt().getMonth() == new Date().getMonth()).collect(Collectors.toList());
    }
}
