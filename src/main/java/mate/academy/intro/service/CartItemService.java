package mate.academy.intro.service;

import mate.academy.intro.dto.cartitem.UpdateCartItemRequestDto;

public interface CartItemService {
    void update(UpdateCartItemRequestDto updateCartItemRequestDto, Long id);

    void delete(Long id);
}
