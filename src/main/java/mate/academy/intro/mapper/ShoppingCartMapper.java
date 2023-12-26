package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.shoppingcart.CreateShoppingCartRequestDto;
import mate.academy.intro.dto.shoppingcart.ShoppingCartDto;
import mate.academy.intro.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    ShoppingCartDto toDoShoppingCart(ShoppingCart shoppingCart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    ShoppingCart toEntity(CreateShoppingCartRequestDto categoryDto);
}
