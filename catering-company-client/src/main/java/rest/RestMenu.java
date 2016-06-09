package rest;

import com.model.Category;
import com.model.Dish;
import com.model.Menu;
import managedBean.Initial;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Locale;

/**
 * Session Bean implementation class HelloJAXRSWebService
 */
@Path("/menu")
@RequestScoped
@ManagedBean
public class RestMenu{

    private Initial initial = new Initial();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Menu> loadMenu(@Context Request request) {
//        Variant.VariantListBuilder vb = Variant.VariantListBuilder.newInstance();
//        vb.languages(new Locale("en"), new Locale("es")).add();
//
//        List<Variant> variants = vb.build();
//
//        Variant v = request.selectVariant(variants);


        return  initial.getInfo().getAllMenu();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id:[0-9][0-9]*}")
    public Category loadMenu(@PathParam("id") Integer id) {
        return  initial.getInfo().getCategory(id);
    }

    @POST
    @Path("/category/{id:[0-9][0-9]*}/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToCategory(@PathParam("id") Integer categoryId, Dish dish) {



        Category category = initial.getInfo().getCategory(categoryId);
        dish = initial.getMenu().addDish(dish);
        category.addDish(dish);
        initial.getMenu().editCategory(category);
        return Response.ok().build();
    }
}