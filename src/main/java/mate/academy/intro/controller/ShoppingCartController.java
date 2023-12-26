package mate.academy.intro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.intro.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.intro.service.ShoppingCartService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "ShoppingCart management", description = "Endpoints for managing ShoppingCart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(description = "Get all carts")
    @ApiResponse(responseCode = "200", description = "All carts")
    public List<ShoppingCartDto> getAll(Pageable pageable) {
        return shoppingCartService.findAll(pageable);
    }
}
