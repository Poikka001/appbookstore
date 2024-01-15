package mate.academy.intro.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.order.CreateOrderDto;
import mate.academy.intro.dto.order.OrderDto;
import mate.academy.intro.dto.order.UpdateOrderStatusDto;
import mate.academy.intro.exception.EntityNotFoundException;
import mate.academy.intro.mapper.OrderMapper;
import mate.academy.intro.model.Order;
import mate.academy.intro.model.OrderItem;
import mate.academy.intro.model.Status;
import mate.academy.intro.repository.order.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;

    @Override
    public OrderDto save(CreateOrderDto requestDto) {
        Order order = orderMapper.toOrder(requestDto);
        order.setUser(userService.getUserFromContext());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PROCESSING);

        List<OrderItem> orderItems = orderItemService.getOrderItemsByCart(order);
        BigDecimal total = orderItems.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setOrderItems(orderItems);
        order.setTotal(total);
        return orderMapper.toDtoOrder(orderRepository.save(order));
    }

    @Override
    public List<OrderDto> getAllOrders(Pageable pageable) {
        return orderRepository.getAllByUser(userService.getUserFromContext(),
                        pageable).stream()
                .map(orderMapper::toDtoOrder)
                .toList();
    }

    @Override
    public OrderDto updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto, Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order by id" + id));
        order.setStatus(updateOrderStatusDto.getStatus());
        return orderMapper.toDtoOrder(orderRepository.save(order));
    }
}
