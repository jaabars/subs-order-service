package kg.easy.subsorderservice.mappers;

import kg.easy.subsorderservice.models.OrderHistory;
import kg.easy.subsorderservice.models.dto.OrderHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderHistoryMapper {

    OrderHistoryMapper INSTANCE = Mappers.getMapper(OrderHistoryMapper.class);

    OrderHistoryDto toOrderHistoryDto(OrderHistory orderHistory);


}
