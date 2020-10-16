package kg.easy.subsorderservice.models.dto;

import kg.easy.subsorderservice.models.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderHistoryDto {

    private Long id;

    private Date startDate;
    private Date endDate;
    private String comment;


    private OrderStatus status;

}
