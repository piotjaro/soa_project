package managedBean;


import com.ejb.*;
import com.model.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@ManagedBean
@SessionScoped
public class RemoteEJBClient {

    private final static Logger logger = Logger.getLogger(RemoteEJBClient.class.getName());


    private UserAccount userToAdd;

    private Initial initial = new Initial();


//    public Map<Category, List<Dish>> showCategoriesWithDishes() {
//        List<Category> categories = initial.getInfo().getCategories();
//        Map<Category, List<Dish>> result =  new TreeMap<>();
//        for(Category category : categories) {
//            result.put(category, initial.getInfo().getDishesFromCategory(category.getId()));
//        }
//
//        return result;
//    }

//    public List<Integer>getCategoriesId(){
//        List<Integer> result = new ArrayList<>();
//        for(Category category : initial.getInfo().getCategories()) {
//            result.add(category.getId());
//        }
//        return result;
//    }


    public List<UserAccount> showUsers() {
        return initial.getUserInfo().getUsers();
    }

    //INITIAL METHODS


}