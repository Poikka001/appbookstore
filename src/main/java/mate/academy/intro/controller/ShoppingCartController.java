package mate.academy.intro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.intro.dto.cartitem.UpdateCartItemRequestDto;
import mate.academy.intro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.intro.service.CartItemService;
import mate.academy.intro.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping carts")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    private final CartItemService cartItemService;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Retrieve user's shopping cart",
            description = "Retrieve user's shopping cart with items")
    @GetMapping
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add a new cart to the shopping cart",
            description = "add an item to user's shopping cart")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void add(@RequestBody @Valid CreateCartItemRequestDto requestDto) {
        shoppingCartService.addItem(requestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "update a cart item",
            description = "update the certain cart item")
    @PutMapping("/cart-items/{cartItemId}")
    public void updateCartItem(@RequestBody @Valid UpdateCartItemRequestDto requestDto,
                               @PathVariable @Positive Long cartItemId) {
        cartItemService.update(requestDto, cartItemId);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Delete the certain cart item",
            description = "Delete the cart item from DB by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cart-items/{cartItemId}")
    public void delete(@PathVariable @Positive Long cartItemId) {
        cartItemService.delete(cartItemId);
    }
}
