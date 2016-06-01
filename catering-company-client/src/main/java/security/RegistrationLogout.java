package security;

import com.model.UserAccount;
import managedBean.Initial;
import org.apache.commons.codec.digest.DigestUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String regisration() {
        user.setDebt(0.0);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        initial.getUserEditor().addUser(user);
        user = new UserAccount();
        return "success";
    }
}
