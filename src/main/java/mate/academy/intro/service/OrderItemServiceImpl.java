package mate.academy.intro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.orderitem.OrderItemDto;
import mate.academy.intro.exception.EntityNotFoundException;
import mate.academy.intro.mapper.OrderItemMapper;
import mate.academy.intro.model.CartItem;
import mate.academy.intro.model.Order;
import mate.academy.intro.model.OrderItem;
import mate.academy.intro.repository.cartitem.CartItemRepository;
import mate.academy.intro.repository.orderitem.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final ShoppingCartService shoppingCartService;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderItem> getOrderItemsByCart(Order order) {
        return cartItemRepository.findAllByShoppingCartId(shoppingCartService
                        .getShoppingCart().getId()).stream()
                .map(this::getOrderItemFromCart)
                .peek(o -> o.setOrder(order))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDto> getItemsFromOrder(Long id) {
        return orderItemRepository.getAllByOrderId(id).stream()
                .map(orderItemMapper::toOrderItemDto)
                .toList();
    }

    @Override
    public OrderItemDto getItemFromOrderById(Long orderId, Long itemId) {
        List<OrderItem> orderItems = orderItemRepository.getAllByOrderId(orderId);

        Optional<OrderItem> foundItem = orderItems.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();

        return foundItem.map(orderItemMapper::toOrderItemDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find item with id:" + itemId + " in order with id:" + orderId));
    }

    private OrderItem getOrderItemFromCart(CartItem cartItem) {
        OrderItem orderItem = orderItemMapper.convertCartItemToOrderItem(cartItem);
        orderItem.setPrice(orderItem.getBook().getPrice()
                .multiply(new BigDecimal(orderItem.getQuantity())));
        return orderItem;
    }
}
