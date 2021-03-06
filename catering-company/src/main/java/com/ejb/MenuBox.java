package com.ejb;

//import com.model.Dish;

import com.model.*;
import org.apache.log4j.Logger;
import org.jboss.annotation.security.SecurityDomain;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Lock;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;


@Singleton
@Startup
//@SecurityDomain(value="school")
//@DeclareRoles({"ADMIN"})
//@RolesAllowed({"ADMIN"})
public class MenuBox {

    private static final Logger logger = Logger.getLogger(MenuBox.class);
    @PersistenceContext(name="catering-company")
    private EntityManager em;


    @Lock(READ)
    public List<Dish> getDishes() {
        Query q1 = em.createNamedQuery("Dish.getAll");
        return (List<Dish>)q1.getResultList();
    }

    @Lock(WRITE)
    public Dish addDish(Dish dish) {
        em.persist(dish);
        return dish;
    }

    @Lock(WRITE)
    public void addMenu(Menu menu) {
        em.persist(menu);
    }

    @Lock(WRITE)
    public void editMenu(Menu menu) {
        for(Category category : menu.getCategories()){

            for(Dish dish : category.getDishes()) {

                for(Ingredient ingredient : dish.getIngredients()) {
                    if(ingredient.getId() == 0)
                        em.persist(ingredient);
                    else
                        em.merge(ingredient);
                }
                if(dish.getId()==0)
                    em.persist(dish);
                else
                    em.merge(dish);
            }

            if(category.getId()==0)
                em.persist(category);
            else
                em.merge(category);
        }
        em.merge(menu);
    }

    @Lock(READ)
    public List<Menu> getAllMenu() {
        Query q1 = em.createNamedQuery("Menu.getAll");
        return (List<Menu>)q1.getResultList();
    }

    @Lock(READ)
    public Menu getMenu(int id) {
        Query q1 = em.createQuery("Menu.getById");
        q1.setParameter(1, id);
        return (Menu)q1.getResultList();
    }

    @Lock(WRITE)
    public void removeMenu(int id) {
        Menu menu = em.find(Menu.class, id);
        em.remove(menu);
    }

    @Lock(WRITE)
    public void removeDish(int id) {

        Dish dish = em.find(Dish.class, id);
        em.remove(dish);
    }

    @Lock(WRITE)
    public void editDish(Dish dish) {
        em.merge(dish);
    }

    @Lock(READ)
    public List<Category> getCategories() {
        Query q1 = em.createNamedQuery("Category.getAll");
        return (List<Category>)q1.getResultList();
    }

    @Lock(READ)
//    @RolesAllowed({"ADMIN"})
    public Menu getCurrentMenu() {

        Query q1 = em.createNamedQuery("Menu.getCurrentMenu");
        if(q1.getResultList().size()>0)
            return (Menu)q1.getResultList().get(0);
        else return null;
    }

    @Lock(READ)
    public List<Menu> getArchivedMenu() {
        Query q1 = em.createNamedQuery("Menu.getArchivedMenu");
        return (List<Menu>)q1.getResultList();
    }


    @Lock(WRITE)
    public void addCategory(Category category) {
        em.persist(category);
    }

    @Lock(WRITE)
    public void editCategory(Category category) {
        em.merge(category);
    }

    @Lock(WRITE)
    public void editCart(Cart cart) {
        if(cart.getAddress().getId()==0)
            em.persist(cart.getAddress());
        else
            em.merge(cart.getAddress());
        em.merge(cart);
    }

    @Lock(WRITE)
    public void removeCategory(int id) {
        Category category = em.find(Category.class, id);
        em.remove(category);
    }

    @Lock(WRITE)
    public void removeIngredient(int id) {
        Ingredient ingredient = em.find(Ingredient.class, id);
        em.remove(ingredient);
    }

//    @Lock(READ)
//    public List<Dish> getDishesFromCategory(int id) {
//        Query q1 = em.createQuery("Select d from Dish d where d.category.id = "+ id +"");
//        return (List<Dish>)q1.getResultList();
//    }

    @Lock(READ)
    public Category getCategory(int id) {
        return em.find(Category.class, id);
    }

    @Lock(READ)
    public Dish getDish(int id) {
        return em.find(Dish.class, id);
    }


    @Lock(READ)
    public Ingredient getIngredient(int id) {
        return em.find(Ingredient.class, id);
    }

    @Lock(READ)
    public List<Ingredient> getIngredientFromDish(int id) {
        return em.find(Dish.class, id).getIngredients();
    }

    @Lock(WRITE)
    public void editIngredient(Ingredient ingredient) {
        em.merge(ingredient);
    }

    @Lock(WRITE)
    public Ingredient addIngredient(Ingredient ingredient) {
        em.persist(ingredient);
        return ingredient;
    }

    @Lock(WRITE)
    public void setCurrentMenu(Menu menu) {
        for(Menu menu1 : getAllMenu()){
            if(menu1.isCurrent()){
                menu1.setIsCurrent(false);
                em.merge(menu1);
            }
        }
        menu.setIsCurrent(true);
        em.merge(menu);
    }
}
