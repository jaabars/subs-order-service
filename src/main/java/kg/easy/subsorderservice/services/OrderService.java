package kg.easy.subsorderservice.services;

import kg.easy.subsorderservice.models.appDto.OrderAppDto;
import kg.easy.subsorderservice.models.enums.OrderStatus;
import kg.easy.subsorderservice.models.responses.Response;

public interface OrderService {


    Response saveOrder(OrderAppDto orderAppDto);

    Response findOrdersByStatus(OrderStatus status);

    Response getOrderForProcess(Long id);

    Response changeOrderStatus(OrderStatus status, Long id);
}
