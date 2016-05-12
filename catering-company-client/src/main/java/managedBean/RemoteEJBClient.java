package managedBean;


import com.ejb.MenuEditor;
import com.ejb.MenuInfo;
import com.ejb.MenuInfoBean;
import com.model.Category;
import com.model.Dish;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;


@ManagedBean
@SessionScoped
public class RemoteEJBClient {
    private final static Hashtable jndiProperties = new Hashtable();
    private final static Logger logger = Logger.getLogger(RemoteEJBClient.class .getName());
    MenuInfo info;
    MenuEditor menu;
    Dish dish = new Dish();
    Category category = new Category();

    @PostConstruct
    public void initMethod() throws NamingException {
        Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
        Logger.getLogger("org.xnio").setLevel(Level.SEVERE);
        info = lookupMenuInfoEJB();
        menu=lookupMenuEditorEJB();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    public String addDish() throws NamingException {
        return menu.addDish(dish);
    }


    public String showDishes() throws NamingException {
        return info.printDishes();
    }

    public String addCategory() throws NamingException {
        return menu.addCategory(category);
    }


    public String showCategories() throws NamingException {
        return info.printCategories();
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private static MenuInfo lookupMenuInfoEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (MenuInfo) context.lookup("ejb:/catering-company//MenuInfoBean!com.ejb.MenuInfo");

    }
    private static MenuEditor lookupMenuEditorEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (MenuEditor) context.lookup("ejb:/catering-company//MenuEditorBean!com.ejb.MenuEditor?stateful");

    }

}