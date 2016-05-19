package managedBean;


import com.ejb.MenuEditor;
import com.ejb.MenuInfo;
import com.ejb.MenuInfoBean;
import com.model.Category;
import com.model.Dish;
import com.model.Ingredient;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    MenuInfo info;
    MenuEditor menu;
    private Dish dish;
    private Category category;
    private Ingredient ingredient;
    private String result;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    @PostConstruct
    public void initMethod() throws NamingException {
        Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
        Logger.getLogger("org.xnio").setLevel(Level.SEVERE);
        info = lookupMenuInfoEJB();
        menu = lookupMenuEditorEJB();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }


    public RemoteEJBClient() {
        dish = new Dish();
        category = new Category();
        ingredient = new Ingredient();
    }

    public String addDish() throws NamingException {
        menu.addDish(dish);
        dish = new Dish();
        ingredient = new Ingredient();
        return "success";
    }

    public List<Dish> showDishes() throws NamingException {
        return info.getDishes();
    }

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
        ingredients.add(ingredient);
        dish.setIngredients(ingredients);
        menu.editDish(dish);
        dish = info.getDish(dish.getId());
        Ingredient ingredient1 = dish.getIngredients().get(dish.getIngredients().size()-1);
        ingredient1.setName(ingredient.getName());
        ingredient1.setQuantity(ingredient.getQuantity());
        menu.editIngredient(ingredient1);
        return "success";
    }


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

    public String editCategory(int id) {
        category = info.getCategory(id);
        return "editCategory";
    }

    public String saveEditedCategory(){
        menu.editCategory(category);
        return "success";
    }
    public String saveEditedDish(){
        menu.editDish(dish);
        return "success.html";
    }

    public String editDish(int id) {
        dish = info.getDish(id);
        return "editDish";
    }

    public String editIngredient(int id) {
        ingredient = info.getIngredient(id);
        return "editIngredient";
    }

    public String saveEditedIngredient(){
        menu.editIngredient(ingredient);
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

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
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