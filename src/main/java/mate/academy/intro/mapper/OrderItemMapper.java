package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.orderitem.OrderItemDto;
import mate.academy.intro.model.CartItem;
import mate.academy.intro.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "book.id", target = "bookId")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    OrderItem convertCartItemToOrderItem(CartItem cartItem);
}
