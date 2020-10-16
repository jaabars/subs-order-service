package kg.easy.subsorderservice.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String msisdn;


}
