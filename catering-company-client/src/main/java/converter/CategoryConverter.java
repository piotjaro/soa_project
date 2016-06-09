//package converter;
//
//import com.model.Category;
//import managedBean.Initial;
//
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//
///**
// * Created by piotrek on 09.06.16.
// */
//public class CategoryConverter implements Converter {
//
//    Initial initial = new Initial();
//
//    @Override
//    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
//        return initial.getInfo().getCategory(Integer.parseInt(s));
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
//        return ""+((Category) o).getId();
//    }
//}
