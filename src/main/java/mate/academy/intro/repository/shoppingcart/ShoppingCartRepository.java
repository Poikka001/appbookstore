package mate.academy.intro.repository.shoppingcart;

import mate.academy.intro.model.ShoppingCart;
import mate.academy.intro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    boolean existsShoppingCartByUser(User user);

    Optional<ShoppingCart> findShoppingCartByUser(User user);
}
