package kg.easy.subsorderservice.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean active;
}
