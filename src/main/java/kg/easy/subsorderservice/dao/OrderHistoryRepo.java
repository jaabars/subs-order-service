package kg.easy.subsorderservice.dao;

import kg.easy.subsorderservice.models.Order;
import kg.easy.subsorderservice.models.OrderHistory;
import kg.easy.subsorderservice.models.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepo extends JpaRepository<OrderHistory, Long> {

    boolean existsByOrderAndEndDateIsNull(Order order);

    OrderHistory findByOrderAndEndDateIsNull(Order order);

    List<OrderHistory> findAllByStatusAndEndDateIsNull(OrderStatus status);

}
