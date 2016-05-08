package managedBean;


import com.ejb.MenuEditor;
import com.ejb.MenuInfo;
import com.ejb.MenuInfoBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;



@ManagedBean
@SessionScoped
public class RemoteEJBClient {
    private final static Hashtable jndiProperties = new Hashtable();


    public String bookSeat(int seatId) {

        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        MenuInfo info = null;
        try {
            info = lookupMenuInfoEJB();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        final MenuEditor menu;
        try {
            menu = lookupMenuEditorEJB();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return info.printMenuList().toString();
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