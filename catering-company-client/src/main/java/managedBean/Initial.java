package managedBean;

import com.ejb.MenuEditor;
import com.ejb.MenuInfo;
import com.ejb.UserEditor;
import com.ejb.UserInfo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@ApplicationScoped
public class Initial {
    private final static Hashtable jndiProperties = new Hashtable();
    private MenuInfo info;
    private MenuEditor menu;
    private UserEditor userEditor;
    private UserInfo userInfo;

    public Initial(){
        Logger.getLogger("org.jboss").setLevel(Level.SEVERE);
        Logger.getLogger("org.xnio").setLevel(Level.SEVERE);
        try {
            info = lookupMenuInfoEJB();
            menu = lookupMenuEditorEJB();
            userEditor = lookupUserEditorEJB();
            userInfo = lookupUserInfoEJB();
        } catch (NamingException e) {
            e.printStackTrace();
        }



        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    public MenuInfo getInfo() {
        return info;
    }

    public void setInfo(MenuInfo info) {
        this.info = info;
    }

    public MenuEditor getMenu() {
        return menu;
    }

    public void setMenu(MenuEditor menu) {
        this.menu = menu;
    }

    public UserEditor getUserEditor() {
        return userEditor;
    }

    public void setUserEditor(UserEditor userEditor) {
        this.userEditor = userEditor;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

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
