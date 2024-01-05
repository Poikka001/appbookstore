package mate.academy.intro.dto.order;

import lombok.Data;
import mate.academy.intro.dto.orderitem.OrderItemDto;
import mate.academy.intro.model.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private List<OrderItemDto> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Status status;
}
