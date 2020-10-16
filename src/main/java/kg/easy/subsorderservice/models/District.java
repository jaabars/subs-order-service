package kg.easy.subsorderservice.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;


}
