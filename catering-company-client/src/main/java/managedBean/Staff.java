package managedBean;

import com.model.Cart;
import com.model.Dish;
import com.model.UserAccount;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@ManagedBean
@SessionScoped
public class Staff {
    private Initial initial = new Initial();
    private Cart cart = new Cart();

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String changeCartStatus(Cart cart, String status) {

        if(status.equals("FinishedCash")) {
//            if(!cart.getSubscribedType().equals("None")){
//                subscriptionCart(cart);
//            }
            cart.setStatus("Finished");

        } else if(status.equals("FinishedSalary")) {
//            if(!cart.getSubscribedType().equals("None")){
//                subscriptionCart(cart);
//            }
            cart.setStatus("Finished");
            cart.setPaidFromSalary(true);
            UserAccount userTmp = initial.getUserInfo().getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
            initial.getUserEditor().addDebt(userTmp, cart.getCost());
        } else if(status.equals("Cancelled")) {
//            if(!cart.getSubscribedType().equals("None")){
//                subscriptionCart(cart);
//            }
            cart.setStatus(status);
        } else
            cart.setStatus(status);

        initial.getCartEditor().editCart(cart);
        return "/all/success.xhtml";
    }

//    public void subscriptionCart(Cart cartInput) {
//            Cart newCart = cartInput;
//            newCart.setId(0);
//            newCart.setStatus("New");
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(newCart.getDateOfReceipt());
//            if (cartInput.getSubscribedType().equals("Regular")) {
//                cal.add(Calendar.DATE, 1);
//            } else if (cartInput.getSubscribedType().equals("Repeated")){
//                cal.add(Calendar.DATE, Integer.parseInt(cartInput.getSubscribedValue()));
//            }
//            newCart.setDateOfReceipt(cal.getTime());
//        for(UserAccount userAccount : initial.getUserInfo().getUsers()){
//            for(Cart cartTmp : userAccount.getActualCarts() ) {
//                if(cartTmp.getId() == cart.getId()){
//                    userAccount.addCart(newCart);
//                    initial.getUserEditor().editUser(userAccount);
//                    return;
//                }
//            }
//        }
//
//    }


    public List<Cart> getAllCarts() {
        return initial.getCartInfo().getCarts();
    }

    public List<Cart> getNewCarts() {
        return initial.getCartInfo().getNewCarts();
    }

    public List<Cart> getCancelledCarts() {
        return initial.getCartInfo().getCancelledCarts();
    }

    public List<Cart> getReadyCarts() {
        List<Cart> carts = initial.getCartInfo().getReadyCarts().stream().filter(c -> c.getAddress().getCity().equals("")).collect(Collectors.toList());
        return carts;
    }

    public List<Cart> getReadyToDeliverCarts() {
        return initial.getCartInfo().getReadyCarts().stream().filter(c -> !c.getAddress().getCity().equals("")).collect(Collectors.toList());
    }

    public List<Cart> getFinishedCarts() {
        return initial.getCartInfo().getFinishedCarts();
    }

    public List<Cart> getInProgressCarts() {
        return initial.getCartInfo().getInProgressCarts();
    }

    public List<Cart> getInDeliveryCarts() {
        return initial.getCartInfo().getInDeliveredCarts();
    }

    public String changeStatus(Cart cart, String status) {
        cart.setStatus(status);
        initial.getCartEditor().editCart(cart);
        return "/all/success.xhtml";
    }

    public String goToAddComment(Cart cart1) {
        cart = cart1;
        return "/staff/addCommentToCart.xhtml";
    }

    public String addComment(){
        initial.getCartEditor().editCart(cart);
        cart = new Cart();
        return "/all/success.xhtml";
    }

}
