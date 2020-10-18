package kg.easy.subsorderservice.services;

import kg.easy.subsorderservice.models.Order;
import kg.easy.subsorderservice.models.OrderHistory;
import kg.easy.subsorderservice.models.dto.OrderDto;
import kg.easy.subsorderservice.models.enums.OrderStatus;

import java.util.List;

public interface OrderHistoryService {

    OrderDto appendHistory(OrderDto orderDto, OrderStatus newStatus, String comment);

    void closeHistory(Order order);

    OrderStatus findActualOrderStatus(Order order);

    List<OrderHistory> findOrderHistoriesByStatus(OrderStatus status);

    OrderHistory getTopOrderHistoryForNextProcess();

}
