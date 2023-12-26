package mate.academy.intro.service;

import mate.academy.intro.dto.shoppingcart.ShoppingCartDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCartDto getUserCart(Long userId);
    List<ShoppingCartDto> findAll(Pageable pageable);
}
