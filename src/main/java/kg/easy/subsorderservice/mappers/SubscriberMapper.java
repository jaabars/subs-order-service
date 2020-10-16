package kg.easy.subsorderservice.mappers;

import kg.easy.subsorderservice.models.Subscriber;
import kg.easy.subsorderservice.models.dto.SubscriberDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriberMapper {

    SubscriberMapper INSTANCE = Mappers.getMapper(SubscriberMapper.class);

    Subscriber toSubscriber(SubscriberDto subscriberDto);
    SubscriberDto toSubscriberDto(Subscriber subscriber);
}
