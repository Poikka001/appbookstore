package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.cartitem.CartItemDto;
import mate.academy.intro.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.intro.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toCartItem(CartItem cartItem);

    CartItem toCartItem(CreateCartItemRequestDto createCartItemRequestDto);
}
