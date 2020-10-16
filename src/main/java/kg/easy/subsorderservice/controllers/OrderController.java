package kg.easy.subsorderservice.controllers;

import kg.easy.subsorderservice.models.appDto.OrderAppDto;
import kg.easy.subsorderservice.models.appDto.OrderCloseDto;
import kg.easy.subsorderservice.models.enums.OrderStatus;
import kg.easy.subsorderservice.models.responses.Response;
import kg.easy.subsorderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public Response saveOrder(@RequestBody OrderAppDto orderAppDto){
        return orderService.saveOrder(orderAppDto);
    }

    @GetMapping("/find")
    public Response findOrdersByStatus(@RequestParam OrderStatus status){
        return orderService.findOrdersByStatus(status);
    }

    @GetMapping("/process/{id}")
    public Response getOrderForProcess(@PathVariable Long id){
        return orderService.getOrderForProcess(id);
    }

    @PutMapping("/status")
    public Response changeOrderStatus(@RequestParam OrderStatus status, @RequestParam Long id){
        return orderService.changeOrderStatus(status, id);
    }

    @GetMapping("/next")
    public Response getNextOrder(){
        return null;
        //return orderService.getNextOrder();
    }

    @PutMapping("/close")
    public Response closeOrder(@RequestBody OrderCloseDto orderCloseDto){
        return null;
    }
}
