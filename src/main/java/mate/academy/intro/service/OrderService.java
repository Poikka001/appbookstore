package mate.academy.intro.service;

import java.util.List;

import mate.academy.intro.dto.order.CreateOrderDto;
import mate.academy.intro.dto.order.OrderDto;
import mate.academy.intro.dto.order.UpdateOrderStatusDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface OrderService {
    OrderDto save(CreateOrderDto requestDto);

    List<OrderDto> getAllOrders(@PageableDefault Pageable pageable);

    OrderDto updateOrderStatus(UpdateOrderStatusDto updateOrderStatusDto, Long id);
}
