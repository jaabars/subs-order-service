package kg.easy.subsorderservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date addDate;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    private String imgUrl;

    private String school;

    @ManyToOne
    @JoinColumn(name = "subs_id")
    private Subscriber subscriber;



}
