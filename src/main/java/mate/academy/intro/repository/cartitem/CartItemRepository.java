package mate.academy.intro.repository.cartitem;

import mate.academy.intro.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByShoppingCartId(Long id);
}
