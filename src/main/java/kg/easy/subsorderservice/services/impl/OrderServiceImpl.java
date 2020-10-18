package kg.easy.subsorderservice.services.impl;

import kg.easy.subsorderservice.dao.OrderRepo;
import kg.easy.subsorderservice.mappers.OrderMapper;
import kg.easy.subsorderservice.mappers.SubscriberMapper;
import kg.easy.subsorderservice.models.Order;
import kg.easy.subsorderservice.models.OrderHistory;
import kg.easy.subsorderservice.models.appDto.OrderAppDto;
import kg.easy.subsorderservice.models.appDto.OrderCloseDto;
import kg.easy.subsorderservice.models.dto.OrderDto;
import kg.easy.subsorderservice.models.dto.SubscriberDto;
import kg.easy.subsorderservice.models.enums.OrderStatus;
import kg.easy.subsorderservice.models.responses.Response;
import kg.easy.subsorderservice.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private OrderRepo orderRepo;


    @Autowired
    private OrderHistoryService orderHistoryService;

    @Override
    public Response saveOrder(OrderAppDto orderAppDto) {

        Response response = Response.getResponse();

        SubscriberDto subscriberDto = subscriberService.findSubsByMsisdn(orderAppDto.getMsisdn());

        if (subscriberDto == null){

            subscriberDto = new SubscriberDto();
            subscriberDto.setMsisdn(orderAppDto.getMsisdn());
            subscriberDto = subscriberService.saveSubscriber(subscriberDto);

        }else{
            Order currOrder = orderRepo.findTopBySubscriberOrderByIdDesc(SubscriberMapper.INSTANCE.toSubscriber(subscriberDto));
            OrderStatus orderStatus = orderHistoryService.findActualOrderStatus(currOrder);
            if (orderStatus.equals(OrderStatus.IN_PROCESS)){
                response.setMessage("Ваш запрос обрабатывается");
                return response;
            }else if (orderStatus.equals(OrderStatus.APPROVED)){
                response.setMessage("У Вас услуга подключена");
                return response;
            }
            orderHistoryService.closeHistory(currOrder);
        }

        Order order = new Order();
        order.setAddDate(new Date());
        order.setDistrict(null);
        order.setRegion(null);
        order.setSchool(orderAppDto.getSchool());
        order.setSubscriber(SubscriberMapper.INSTANCE.toSubscriber(subscriberDto));

        order = orderRepo.save(order);

        OrderDto orderDto = orderHistoryService.appendHistory(OrderMapper.INSTANCE.toOrderDto(order), OrderStatus.NEW, null);

        response.setObject(orderDto);

        return response;
    }

    @Override
    public Response findOrdersByStatus(OrderStatus status) {

        Response response = Response.getResponse();

        List<OrderHistory> orderHistories = orderHistoryService.findOrderHistoriesByStatus(status);

        List<OrderDto> orderDtos = OrderMapper.INSTANCE.toOrderDtos(orderHistories);

        response.setObject(orderDtos);

        return response;
    }

    @Override
    public Response getOrderForProcess(Long id) {

        Response response = Response.getResponse();

        Order order = orderRepo.findById(id).orElse(null);

        if (order == null){
            response.setStatus(0);
            response.setMessage("Заявка не найдена");
            return response;
        }

        OrderStatus status = orderHistoryService.findActualOrderStatus(order);

        if (!status.equals(OrderStatus.NEW)){
            response.setStatus(0);
            response.setMessage("Некорректный статус заявки!");
            return response;
        }

        OrderDto orderDto = orderHistoryService.appendHistory(OrderMapper.INSTANCE.toOrderDto(order), OrderStatus.IN_PROCESS, null);

        response.setObject(orderDto);
        return response;
    }

    @Override
    public Response changeOrderStatus(OrderStatus status, Long id) {

        Response response = Response.getResponse();

        Order order = orderRepo.findById(id).orElse(null);

        if (order == null){
            response.setStatus(0);
            response.setMessage("Заявка не найдена");
            return response;
        }

        OrderDto orderDto = orderHistoryService.appendHistory(OrderMapper.INSTANCE.toOrderDto(order),status, null);

        response.setObject(orderDto);

        return response;
    }

    @Override
    public Response getNextOrder() {
        Response response = Response.getResponse();
        OrderHistory orderHistory = orderHistoryService.getTopOrderHistoryForNextProcess();
        if (orderHistory == null){
            response.setStatus(1);
            response.setMessage("Заявки для следующей обработки отсутсвуют");
            return response;
        }
        OrderDto orderDto = OrderMapper.INSTANCE.orderHistoryToOrderDto(orderHistory);
        response.setObject(orderDto);
        return response;
    }

    @Override
    public Response closeOrder(OrderCloseDto orderCloseDto) {

        Response response = Response.getResponse();
        Order order = orderRepo.findById(orderCloseDto.getOrderId()).orElse(null);
        if (order==null){
            response.setStatus(404);
            response.setMessage("Не найдено");
            return response;
        }
          orderHistoryService.closeHistory(order);
            OrderDto orderDto = orderHistoryService.appendHistory(OrderMapper.INSTANCE.toOrderDto(order),orderCloseDto.getStatus(),orderCloseDto.getComment());
            response.setObject(orderDto);

        return response;
    }
}
