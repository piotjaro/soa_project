package security;

import com.model.UserAccount;
import managedBean.Initial;
import org.apache.commons.codec.digest.DigestUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

/**
 * Created by piotrek on 31.05.16.
 */
@ManagedBean
@SessionScoped

public class RegistrationLogout {

    UserAccount user = new UserAccount();
    Initial initial = new Initial();

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/catering-company-client/all/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String regisration() {
        user.setDebt(0.0);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        initial.getUserEditor().addUser(user);
        user = new UserAccount();
        return "/all/success.xhtml";
    }
}
