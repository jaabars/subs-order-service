package kg.easy.subsorderservice.services;

import kg.easy.subsorderservice.models.dto.SubscriberDto;

public interface SubscriberService {

    SubscriberDto findSubsByMsisdn(String msisdn);

    SubscriberDto saveSubscriber(SubscriberDto subscriberDto);
}
