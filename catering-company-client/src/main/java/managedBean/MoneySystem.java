package managedBean;

import com.model.Cart;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by piotr on 02.06.16.
 */

@ManagedBean
@ApplicationScoped
public class MoneySystem {
    private Initial initial = new Initial();

    public List<Cart> paymentRaport() {
        return initial.getCartInfo().getCarts();
    }
}
