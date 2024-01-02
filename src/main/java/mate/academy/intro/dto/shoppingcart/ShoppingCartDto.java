package mate.academy.intro.dto.shoppingcart;

import lombok.Data;
import mate.academy.intro.dto.cartitem.CartItemDto;
import java.util.List;

@Data
public class ShoppingCartDto {
    private Long id;
    private Long userId;
    private List<CartItemDto> cartItems;
}

