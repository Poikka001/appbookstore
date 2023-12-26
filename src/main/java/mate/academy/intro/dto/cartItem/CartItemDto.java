package mate.academy.intro.dto.cartItem;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private Long shoppingCartId;
    private Long bookId;
    private int quantity;
}
