package kg.easy.subsorderservice.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {


    private Long id;

    private Date addDate;

    private RegionDto region;

    private DistrictDto district;

    private String imgUrl;

    private String school;

    private SubscriberDto subscriber;

    private OrderHistoryDto orderHistory;



}
