package kg.easy.subsorderservice.models.appDto;

import lombok.Data;

@Data
public class OrderAppDto {

    private String msisdn;
    private String school;

    private Long regionId;
    private Long districtId;

}
