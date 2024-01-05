package mate.academy.intro.service;

import mate.academy.intro.dto.orderitem.OrderItemDto;
import mate.academy.intro.model.Order;
import mate.academy.intro.model.OrderItem;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> getOrderItemsByCart(Order order);

    List<OrderItemDto> getItemsFromOrder(Long id);

    OrderItemDto getItemFromOrderById(Long orderId, Long itemId);
}
