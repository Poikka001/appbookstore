package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.cartitem.CartItemDto;
import mate.academy.intro.model.CartItem;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(config = MapperConfig.class,
        uses = CartItemMapper.class)
public interface CartItemListMapper {
    List<CartItemDto> toCartItemDtoSet(List<CartItem> cartItemSet);
}
