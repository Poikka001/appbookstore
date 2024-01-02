package mate.academy.intro.service;

import mate.academy.intro.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.intro.dto.shoppingcart.ShoppingCartDto;

public interface ShoppingCartService {
    void addItem(CreateCartItemRequestDto createCartItemRequestDto);
    ShoppingCartDto getShoppingCart();
}
