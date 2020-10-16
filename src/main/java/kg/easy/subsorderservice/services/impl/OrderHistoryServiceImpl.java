package kg.easy.subsorderservice.services.impl;

import kg.easy.subsorderservice.dao.OrderHistoryRepo;
import kg.easy.subsorderservice.mappers.OrderHistoryMapper;
import kg.easy.subsorderservice.mappers.OrderMapper;
import kg.easy.subsorderservice.models.Order;
import kg.easy.subsorderservice.models.OrderHistory;
import kg.easy.subsorderservice.models.dto.OrderDto;
import kg.easy.subsorderservice.models.enums.OrderStatus;
import kg.easy.subsorderservice.services.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    private OrderHistoryRepo orderHistoryRepo;

    @Override
    public OrderDto appendHistory(OrderDto orderDto, OrderStatus newStatus) {

        if (orderHistoryRepo.existsByOrderAndEndDateIsNull(OrderMapper.INSTANCE.toOrder(orderDto))){
            OrderHistory currOrderHistory = orderHistoryRepo.findByOrderAndEndDateIsNull(OrderMapper.INSTANCE.toOrder(orderDto));
            currOrderHistory.setEndDate(new Date());
            orderHistoryRepo.save(currOrderHistory);
        }

        OrderHistory newOrderHistory = new OrderHistory();
        newOrderHistory.setStartDate(new Date());
        newOrderHistory.setStatus(newStatus);
        newOrderHistory.setOrder(OrderMapper.INSTANCE.toOrder(orderDto));

        newOrderHistory = orderHistoryRepo.save(newOrderHistory);

        orderDto.setOrderHistory(OrderHistoryMapper.INSTANCE.toOrderHistoryDto(newOrderHistory));

        return orderDto;
    }

    @Override
    public void closeHistory(Order order) {
        OrderHistory orderHistory = orderHistoryRepo.findByOrderAndEndDateIsNull(order);

        if (orderHistory != null){
            orderHistory.setEndDate(new Date());
            orderHistoryRepo.save(orderHistory);
        }
    }

    @Override
    public OrderStatus findActualOrderStatus(Order order) {
        OrderHistory orderHistory = orderHistoryRepo.findByOrderAndEndDateIsNull(order);
        return orderHistory.getStatus();
    }

    @Override
    public List<OrderHistory> findOrderHistoriesByStatus(OrderStatus status) {
        return orderHistoryRepo.findAllByStatusAndEndDateIsNull(status);
    }
}
