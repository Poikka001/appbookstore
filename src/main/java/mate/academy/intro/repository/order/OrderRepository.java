package mate.academy.intro.repository.order;

import mate.academy.intro.model.Order;
import mate.academy.intro.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getAllByUser(User user, Pageable pageable);
}
