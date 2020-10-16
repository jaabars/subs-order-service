package kg.easy.subsorderservice.models.dto;

import kg.easy.subsorderservice.models.Region;
import lombok.Data;

@Data
public class DistrictDto {

    private Long id;

    private String name;

    private boolean active;
    private RegionDto region;


}
