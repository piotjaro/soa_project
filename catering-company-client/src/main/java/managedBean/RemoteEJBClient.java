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
    private final static Hashtable jndiProperties = new Hashtable();
    private final static Logger logger = Logger.getLogger(RemoteEJBClient.class.getName());
    private MenuInfo info;
    private MenuEditor menu;
    private UserEditor userEditor;
    private UserInfo userInfo;
    private Dish dish;
    private Category category;
    private Ingredient ingredient;
    private String result;
    private UserAccount user;
    private UserAccount userToAdd;
    private Cart cart = new Cart();

    //CONSTRUCTORS

    public RemoteEJBClient() {
        dish = new Dish();
        category = new Category();
        ingredient = new Ingredient();
    }

    //POST CONTRUCTORS

    @PostConstruct
    public void initMethod() throws NamingException {
        Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
        Logger.getLogger("org.xnio").setLevel(Level.SEVERE);
        info = lookupMenuInfoEJB();
        menu = lookupMenuEditorEJB();
        userEditor = lookupUserEditorEJB();
        userInfo = lookupUserInfoEJB();
        cart.setDishes(new ArrayList<>());

        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    //GETTERY, SETTERY

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
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

    public List<Dish> getCategoryDishes(int id) {
        return info.getDishesFromCategory(id);
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public UserAccount getUserToAdd() {
        return userToAdd;
    }

    public void setUserToAdd(UserAccount userToAdd) {
        this.userToAdd = userToAdd;
    }

    //DISH METHODS

    public String addDish() throws NamingException {
        menu.addDish(dish);
        dish = new Dish();
        ingredient = new Ingredient();
        return "success";
    }

    public List<Dish> showDishes() throws NamingException {
        return info.getDishes();
    }

    public String removeDish(int id) {
        Dish dish1 = info.getDish(id);
        List<Ingredient> ingredients = dish1.getIngredients();
        dish1.setIngredients(new ArrayList<Ingredient>());
        menu.editDish(dish1);

        for(Ingredient ingredient : ingredients) {
            removeIngredient(ingredient.getId());
        }
        menu.removeDish(id);
        return "success";
    }

    public String saveEditedDish(){
        menu.editDish(dish);
        return "success";
    }

    public String editDish(int id) {
        dish = info.getDish(id);
        return "editDish";
    }

    //INGREDIENT METHODS

    public List<Ingredient> showIngredients() {
        return dish.getIngredients();
    }

    public String addCategory() throws NamingException {
        menu.addCategory(category);
        return "success";
    }

    public void addIngredient() {
        if(ingredient.getName()!=null  && (!ingredient.getName().equals(""))) {
            dish.addIngredient(ingredient);
            ingredient = new Ingredient();
        }
    }

    public String goToAddIngredient (int dishId) {
        dish = info.getDish(dishId);
        ingredient = new Ingredient();
        return "addIngredient";
    }

    public String addIngredientFromMenu(){
        List <Ingredient> ingredients = dish.getIngredients();
        ingredient = menu.addIngredient(ingredient);
        ingredients.add(ingredient);
        dish.setIngredients(ingredients);
        menu.editDish(dish);
        return "success";
    }

    public String cancelCart(int userId, int cartId) {

        UserAccount userAccount = userInfo.getUserByLogin(userInfo.getUsers(userId).getLogin());
        List<Cart> carts = userAccount.getActualCarts();
        for(int i = 0; i<carts.size(); i++) {
            if(carts.get(i).getId() == cartId) {
                Cart cartTmp = carts.get(i);
                cartTmp.setStatus("Canceled");
                menu.editCart(cartTmp);
                break;
            }
        }
        return "success";
    }

    public String removeDishFromCart(int dishId, int cartId) {
        Cart cartTmp = userInfo.getCart(cartId);
        cartTmp.removeDish(dishId);
        userEditor.editCart(cartTmp);
        return "success";
    }

    public String removeIngredient(int id) {
        menu.removeIngredient(id);
        return "success";
    }

    public String removeSingleIngredient(int dishId, int id) {
        Dish dish1 = info.getDish(dishId);
        List<Ingredient> ingredients = dish1.getIngredients();
        for (int i=0; i<ingredients.size(); i++) {
            if(ingredients.get(i).getId() == id){
                ingredients.remove(i);
                break;
            }
        }
        dish1.setIngredients(ingredients);
        menu.editDish(dish1);
        menu.removeIngredient(id);
        return "success";
    }

    public String editIngredient(int id) {
        ingredient = info.getIngredient(id);
        return "editIngredient";
    }

    public String saveEditedIngredient(){
        menu.editIngredient(ingredient);
        return "success";
    }

    //CATEGORY METHODS

    public List<Category> showCategories() throws NamingException {
        return info.getCategories();
    }

    public String removeCategories(int id) {
        List<Dish> dishes = info.getDishesFromCategory(id);
        for(Dish dish: dishes) {
            removeDish(dish.getId());
        }
        menu.removeCategory(id);
        return "success";
    }

    public String editCategory(int id) {
        category = info.getCategory(id);
        return "editCategory";
    }

    public String saveEditedCategory(){
        menu.editCategory(category);
        return "success";
    }

    public String goToAddCategory() {
        category = new Category();
        return "addCategory";
    }

    public String goToAddDish() {
        dish = new Dish();
        ingredient = new Ingredient();
        category = new Category();
        return "addDish";
    }

    public Map<Category, List<Dish>> showCategoriesWithDishes() {
        List<Category> categories = info.getCategories();
        Map<Category, List<Dish>> result =  new TreeMap<>();
        for(Category category : categories) {
            result.put(category, info.getDishesFromCategory(category.getId()));
        }

        return result;
    }

    public List<Integer>getCategoriesId(){
        List<Integer> result = new ArrayList<>();
        for(Category category : info.getCategories()) {
            result.add(category.getId());
        }
        return result;
    }

    //USER METHODS

    public String goToAddUser() {
        userToAdd = new UserAccount();
        return "addUser";
    }

    public String goToUserPanel() {
        user =  userInfo.getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        return "userPanel";
    }

    public String addUser() {
        userEditor.addUser(userToAdd);
        return "success";
    }

    public Cart showCart() {
        return cart;
    }

    public String addToCart(int id) {
        result = info.getDish(id).toString();
        result += cart.getId() + " " + cart.getDishes();
        cart.addDish(info.getDish(id));
        return "success";
    }

    public String addCartToUser() {
        UserAccount userAccount = userInfo.getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        cart.setStatus("New");
        Cart cart1 = userEditor.addCart(cart);
        userAccount.addCart(cart1);
        userEditor.editUser(userAccount);
        cart = new Cart();
        return "success";
    }

    public String removeDishFromCart(int id) {
        cart.removeDish(id);
        return "success";
    }

    public List<UserAccount> showUsers() {
        return userInfo.getUsers();
    }

    //INITIAL METHODS

    private static MenuInfo lookupMenuInfoEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (MenuInfo) context.lookup("ejb:/catering-company//MenuInfoBean!com.ejb.MenuInfo");

    }

    private static MenuEditor lookupMenuEditorEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (MenuEditor) context.lookup("ejb:/catering-company//MenuEditorBean!com.ejb.MenuEditor?stateful");

    }

    private static UserInfo lookupUserInfoEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (UserInfo) context.lookup("ejb:/catering-company//UserInfoBean!com.ejb.UserInfo");

    }

    private static UserEditor lookupUserEditorEJB() throws NamingException {

        final Context context = new InitialContext(jndiProperties);
        return (UserEditor) context.lookup("ejb:/catering-company//UserEditorBean!com.ejb.UserEditor?stateful");

    }

}