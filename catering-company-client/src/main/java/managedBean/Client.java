package managedBean;

import com.model.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
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
    private Date date1 = new Date();
    private Date date2 = new Date();
    private List<Cart> carts = new ArrayList<>();

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public UserAccount getUser() {
        return user;
    }

    public  String finishedCartsBetweenDate() {
        List<Cart> result = new ArrayList();
        carts = user.getActualCarts().stream().filter(c -> date1.compareTo(c.getDateOfReceipt())<=0)
                .filter(c->date2.compareTo(c.getDateOfReceipt())>=0)
                .filter(c-> c.getStatus().equals("Finished")).collect(Collectors.toList());
        return "/customer/showCustomerRaport.xhtml";

    }

    public double allCostFromSalary() {
        List<Cart> result = new ArrayList();
        double allCost = 0.0;
        carts = user.getActualCarts().stream().filter(c -> date1.compareTo(c.getDateOfReceipt())<=0)
                .filter(c->date2.compareTo(c.getDateOfReceipt())>=0)
                .filter(c-> c.getStatus().equals("Finished")).collect(Collectors.toList());
        carts.stream().filter(c -> c.isPaidFromSalary()).collect(Collectors.toList());
        for(Cart cart : carts ){
            allCost += cart.getCost();
        }
        return allCost;
    }

    public List<Cart> showCartByStatus(String status) {
        return initial.getCartInfo().getCartsByStatusByUserId(user.getId(), status);
    }

    public List<Cart> showSubscribedCarts() {
        return initial.getCartInfo().getSubscribedCartsByUserId(user.getId());
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String goToUserPanel() {
        user =  initial.getUserInfo().getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return "/customer/userPanel.xhtml";
    }

//    public String removeDishFromCart(int dishId, int cartId) {
//        Cart cartTmp = initial.getCartInfo().getCart(cartId);
//        cartTmp.removeDish(dishId);
//        initial.getCartEditor().editCart(cartTmp);
//        return "/all/success.xhtml";
//
//    }

//    public String payCash(int userId, int cartId) {
//        UserAccount userAccount = initial.getUserInfo().getUserByLogin(initial.getUserInfo().getUsers(userId).getLogin());
//        List<Cart> carts = userAccount.getActualCarts();
//        for(int i = 0; i<carts.size(); i++) {
//            if(carts.get(i).getId() == cartId) {
//                Cart cartTmp = carts.get(i);
//                cartTmp.setPaidFromSalary(false);
//                initial.getMenu().editCart(cartTmp);
//                break;
//            }
//        }
//        return "/all/success.xhtml";
//    }

    public String addToCart(Dish dish) {
        cart.addDish(dish);
        return "/all/success.xhtml";
    }


    public String addCartToUser() {
        UserAccount user = initial.getUserInfo().getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());

        if(cart.getId() == 0) {
            cart.setStatus("New");
            cart.setCreateDate(new Date());
            initial.getUserEditor().addCartToUser(user.getId(), cart);
        } else initial.getCartEditor().editCart(cart);
        cart = new Cart();
        return "/all/success.xhtml";
    }

    public String removeDishFromCart(int id) {
        cart.removeDish(id);
        return "/all/success.xhtml";

    }

    public String cancelCart(Cart cart1) {
        initial.getCartEditor().changeCartStatus(cart1, "Cancelled");
        return "/all/success.xhtml";
    }



//    public String payFromSalary(int userId, int cartId) {
//
//        UserAccount userAccount = initial.getUserInfo().getUserByLogin(initial.getUserInfo().getUsers(userId).getLogin());
//        List<Cart> carts = userAccount.getActualCarts();
//        for(int i = 0; i<carts.size(); i++) {
//            if(carts.get(i).getId() == cartId) {
//                Cart cartTmp = carts.get(i);
//                cartTmp.setPaidFromSalary(true);
//                initial.getMenu().editCart(cartTmp);
//                break;
//            }
//        }
//        return "/all/success.xhtml";
//    }

    public String goToEditCart(Cart cart1) {
        cart = cart1;
        return "/customer/showCart.xhtml";
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
                .getActualCarts().stream().filter(c -> (c.getDateOfReceipt().getMonth() == new Date().getMonth()) && c.getDateOfReceipt().getYear() == new Date().getYear())
                .filter(c-> c.getStatus().equals("Finished")).collect(Collectors.toList());
    }

    public double getMontlyCostFromSalary(){
        double result=0.0;
        for(Cart cart : getCartsCurrentMoth()){
            if(cart.isPaidFromSalary()){
                result +=cart.getCost();
            }
        }
        return result;
    }

}
