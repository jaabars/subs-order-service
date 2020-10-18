package kg.easy.subsorderservice.mappers;

import kg.easy.subsorderservice.models.Order;
import kg.easy.subsorderservice.models.OrderHistory;
import kg.easy.subsorderservice.models.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


    OrderDto toOrderDto(Order order);
    Order toOrder(OrderDto orderDto);

    default List<OrderDto> toOrderDtos(List<OrderHistory> orderHistories){


        List<OrderDto> orderDtos = orderHistories.stream()
                .map(x-> {
                    OrderDto orderDto = toOrderDto(x.getOrder());
                    orderDto.setOrderHistory(OrderHistoryMapper.INSTANCE.toOrderHistoryDto(x));
                    return orderDto;
                })
                .collect(Collectors.toList());

        return orderDtos;

    }
    default OrderDto orderHistoryToOrderDto(OrderHistory orderHistory){
        OrderDto orderDto = OrderMapper.INSTANCE.toOrderDto(orderHistory.getOrder());
        orderDto.setOrderHistory(OrderHistoryMapper.INSTANCE.toOrderHistoryDto(orderHistory));
        return orderDto;
    }


}
