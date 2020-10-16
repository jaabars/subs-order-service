package kg.easy.subsorderservice.models;

import kg.easy.subsorderservice.models.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "order_histories")
public class OrderHistory {

    @Id
    @GeneratedValue
    private Long id;

    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @Column(columnDefinition = "text")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
