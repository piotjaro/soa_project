package managedBean;

import com.model.Category;
import com.model.Dish;
import com.model.Ingredient;
import com.model.Menu;
import com.sun.net.httpserver.Authenticator;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;


@ManagedBean
@SessionScoped
public class MenuManager {
    private Initial initial = new Initial();
    private Menu menu = new Menu();
    private Dish dish;
    private Category category;
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
        return "addDish";
    }

    public String addDish() throws NamingException {
        category.addDish(dish);
        dish = new Dish();
        ingredient = new Ingredient();
        category = new Category();
        return "success";
    }

    public String goToAddIngredient (Dish dish1) {
        dish = dish1;
        ingredient = new Ingredient();
        return "addIngredient";
    }

    public void addIngredient() {
        if(ingredient.getName()!=null  && (!ingredient.getName().equals(""))) {
            dish.addIngredient(ingredient);
            ingredient = new Ingredient();
        }
    }

    public String goToAddCategory() {
        category = new Category();
        return "addCategory";
    }

    public String addCategory() throws NamingException {
        menu.addCategory(category);
        category = new Category();
        return "success";
    }

    public String addIngredientFromMenu(){
        if(ingredient.getName()!=null  && (!ingredient.getName().equals(""))) {
            dish.addIngredient(ingredient);
            ingredient = new Ingredient();
        }
        dish = new Dish();
        ingredient = new Ingredient();
        category = new Category();
        return "success";
    }

    public String goToCreateMenu() {
        return "showEditedMenu";
    }

    //EDIT

    public String goToEditMenu(Menu menu1) {
        menu = menu1;
        return "showEditedMenu";
    }

    public String goToEditDish(Dish dish1) {
        dish = dish1;
        return "editDish";
    }

    public String saveEditedDish(){
        return "success";
    }

    public String goToEditIngredient(Ingredient ingredient1) {
        ingredient = ingredient1;
        return "editIngredient";
    }

//    public String editIngredient(int id) {
//        ingredient = initial.getInfo().getIngredient(id);
//        return "editIngredient";
//    }

    public String goToEditCategory(Category category1) {
        category = category1;
        return "editCategory";
    }

    public String saveEditedCategory(){
        // initial.getMenu().editCategory(category);
        return "success";
    }

    public String saveEditedIngredient(){
       // initial.getMenu().editIngredient(ingredient);
        return "success";
    }

    //REMOVE

    public String removeCategory(Category category){
        menu.removeCategory(category);
        return "success";
    }

    public String removeDish(Category category, Dish dish) {
        category.removeDish(dish);
        return "success";
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
        return "success";
    }

    public String removeMenu(Menu menu1) {
        initial.getMenu().removeMenu(menu1.getId());
        return "success";
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
//    public List<Category> showCategories() throws NamingException {
//        return initial.getInfo().getCategories();
//    }


    public String saveMenu() {
        if(menu.getId()==0){
        initial.getMenu().addMenu(menu);
        } else {
            initial.getMenu().editMenu(menu);
        }
        menu = new Menu();
        return "success";
    }

    public List<Menu> showAllMenu() {
        menu = new Menu();
        return initial.getInfo().getAllMenu();

    }

    public Menu showCurrentMenu(){
        for(Menu menuTmp : initial.getInfo().getAllMenu()){
            if(menuTmp.isCurrent()){
                return menuTmp;
            }
        }
        return null;
    }

    public List<Menu> showArchiveMenu(){
        List<Menu> archivedMenu = new ArrayList<>();
        for(Menu menuTmp : initial.getInfo().getAllMenu()){
            if(!menuTmp.isCurrent()){
                archivedMenu.add(menuTmp);
            }
        }
        return archivedMenu;
    }

    public String cancelMenu() {
        menu = new Menu();
        return "success";
    }

    public String archiveMenu(Menu menu1) {
        menu1.setIsCurrent(false);
        return "success";
    }


    public String currentMenu(Menu menu1) {

        for(Menu menuTmp : initial.getInfo().getAllMenu()){
            if(menuTmp.isCurrent() == true){
                menuTmp.setIsCurrent(false);
                initial.getMenu().editMenu(menuTmp);
            }
        }
        menu1.setIsCurrent(true);
        initial.getMenu().editMenu(menu1);
        return "success";
    }

    public String setDayDish(Dish dish) {
        menu.setDayDish(dish);
        return "success";
    }
}
