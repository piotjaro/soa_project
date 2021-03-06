package managedBean;

import com.model.*;
import com.sun.net.httpserver.Authenticator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;


@ManagedBean
@SessionScoped
public class MenuManager {
    private Initial initial = new Initial();
    private Menu menu = new Menu();
    private Dish dish = new Dish();
    private Category category = new Category();
    private Ingredient ingredient;

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

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

//    public List<Dish> getCategoryDishes(int id) {
//        return initial.getInfo().getDishesFromCategory(id);
//    }

    //ADD

    public String goToAddDish(Category categoryTmp) {
        dish = new Dish();
        ingredient = new Ingredient();
        category = categoryTmp;
        return "/menuManager/addDish.xhtml";
    }

    public String addDish() throws NamingException {
        category.addDish(dish);
        dish = new Dish();
        ingredient = new Ingredient();
        category = new Category();
        return "/all/success.xhtml";
    }

    public String goToAddIngredient (Dish dish1) {
        dish = dish1;
        ingredient = new Ingredient();
        return "/menuManager/addIngredient.xhtml";
    }

    public void addIngredient() {
        if(ingredient.getName()!=null  && (!ingredient.getName().equals(""))) {
            dish.addIngredient(ingredient);
            ingredient = new Ingredient();
        }
    }

    public String goToAddCategory() {
        category = new Category();
        return "/menuManager/addCategory.xhtml";
    }

    public String addCategory() throws NamingException {
        menu.addCategory(category);
        category = new Category();
        return "/all/success.xhtml";
    }

    public String addIngredientFromMenu(){
        if(ingredient.getName()!=null  && (!ingredient.getName().equals(""))) {
            dish.addIngredient(ingredient);
            ingredient = new Ingredient();
        }
        dish = new Dish();
        ingredient = new Ingredient();
        category = new Category();
        return "/all/success.xhtml";
    }

    public void goToCreateMenu() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/catering-company-client/menuManager/showEditedMenu.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EDIT

    public String goToEditMenu(Menu menu1) {
        menu = menu1;
        return "/menuManager/showEditedMenu.xhtml";
    }

    public String goToEditDish(Dish dish1) {
        dish = dish1;
        return "/menuManager/editDish.xhtml";
    }

    public String saveEditedDish(){
        return "/all/success.xhtml";
    }

    public String goToEditIngredient(Ingredient ingredient1) {
        ingredient = ingredient1;
        return "/menuManager/editIngredient.xhtml";
    }

//    public String editIngredient(int id) {
//        ingredient = initial.getInfo().getIngredient(id);
//        return "editIngredient";
//    }

    public String goToEditCategory(Category category1) {
        category = category1;
        return "/menuManager/editCategory.xhtml";
    }

    public String saveEditedCategory(){
        // initial.getMenu().editCategory(category);
        return "/all/success.xhtml";
    }

    public String saveEditedIngredient(){
       // initial.getMenu().editIngredient(ingredient);
        return "/all/success.xhtml";
    }

    //REMOVE

    public String removeCategory(Category category){
        menu.removeCategory(category);
        return "/all/success.xhtml";
    }

    public String removeDish(Category category, Dish dish) {
        category.removeDish(dish);
        return "/all/success.xhtml";
    }

    public List<Dish> showTopTenDishes(){

        return initial.getInfo().getTopTenDishes();
    }

    //    public String removeIngredient(int id) {
//        initial.getMenu().removeIngredient(id);
//        return "success";
//    }
    //    public String removeDish(int id) {
//        Dish dish1 = initial.getInfo().getDish(id);
//        List<Ingredient> ingredients = dish1.getIngredients();
//        dish1.setIngredients(new ArrayList<Ingredient>());
//        initial.getMenu().editDish(dish1);
//
//        for(Ingredient ingredient : ingredients) {
//            removeIngredient(ingredient.getId());
//        }
//        initial.getMenu().removeDish(id);
//        return "success";
//    }

//    public String removeSingleIngredient(int dishId, int id) {
//        Dish dish1 = initial.getInfo().getDish(dishId);
//        List<Ingredient> ingredients = dish1.getIngredients();
//        for (int i=0; i<ingredients.size(); i++) {
//            if(ingredients.get(i).getId() == id){
//                ingredients.remove(i);
//                break;
//            }
//        }
//        dish1.setIngredients(ingredients);
//        initial.getMenu().editDish(dish1);
//        initial.getMenu().removeIngredient(id);
//        return "success";
//    }

//    public String removeCategories(int id) {
//        List<Dish> dishes = initial.getInfo().getDishesFromCategory(id);
//        for(Dish dish: dishes) {
//            removeDish(dish.getId());
//        }
//        initial.getMenu().removeCategory(id);
//        return "success";
//    }

    //sho

    public String removeIngredient(Dish dish, Ingredient ingredient) {
        dish.removeIngredient(ingredient);
        return "/all/success.xhtml";
    }

    public String removeMenu(Menu menu1) {
        initial.getMenu().removeMenu(menu1.getId());
        return "/all/success.xhtml";
    }

    //SHOW

    public List<Ingredient> showIngredients() {
        return dish.getIngredients();
    }

//    public List<Dish> showDishes() throws NamingException {
//        return initial.getInfo().getDishes();
//    }
//
//    //CATEGORY METHODS
//
    public List<Category> showCategories() throws NamingException {
        return menu.getCategories();
    }


    public String saveMenu() {
        if(menu.getId()==0)
            initial.getMenu().addMenu(menu);
        else
            initial.getMenu().editMenu(menu);

        menu = new Menu();
        return "/all/success.xhtml";
    }
//
//    public List<Menu> showAllMenu() {
//        menu = new Menu();
//        return initial.getInfo().getAllMenu();
//
//    }

    public Menu showCurrentMenu(){
        return initial.getInfo().getCurrentMenu();
    }

    public List<Menu> showArchiveMenu(){
        return initial.getInfo().getArchivedMenu();
    }

//    public List<Cart> showBetweenDate(){
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.set(Calendar.YEAR, 2016);
//        cal.set(Calendar.MONTH, 6);
//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.set(Calendar.DAY_OF_MONTH, 30);
//        cal2.set(Calendar.YEAR, 2016);
//        cal2.set(Calendar.MONTH, 6);
//
//
//        //return initial.getCartInfo().getCartsBetweenDate(cal.getTime(), cal2.getTime());
//    }

    public String cancelMenu() {
        menu = new Menu();
        return "/all/success.xhtml";
    }

    public String archiveMenu(Menu menu1) {
        menu1.setIsCurrent(false);
        initial.getMenu().editMenu(menu1);
        return "/all/success.xhtml";
    }


    public String currentMenu(Menu menu1) {
        initial.getMenu().setCurrentMenu(menu1);
        return "/all/success.xhtml";
    }

    public String setDayDish(Dish dish) {
        menu.setDayDish(dish);
        return "/all/success.xhtml";

    }
}
