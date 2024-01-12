package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.order.CreateOrderDto;
import mate.academy.intro.dto.order.OrderDto;
import mate.academy.intro.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class,
        uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDtoOrder(Order order);

    Order toOrder(CreateOrderDto createOrderDto);
}
