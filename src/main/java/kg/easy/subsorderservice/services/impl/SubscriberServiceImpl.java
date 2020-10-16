package kg.easy.subsorderservice.services.impl;

import kg.easy.subsorderservice.dao.SubscriberRepo;
import kg.easy.subsorderservice.mappers.SubscriberMapper;
import kg.easy.subsorderservice.models.Subscriber;
import kg.easy.subsorderservice.models.dto.SubscriberDto;
import kg.easy.subsorderservice.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepo subscriberRepo;
    @Override
    public SubscriberDto findSubsByMsisdn(String msisdn) {

        Subscriber subscriber = subscriberRepo.findByMsisdn(msisdn);

        return SubscriberMapper.INSTANCE.toSubscriberDto(subscriber);
    }

    @Override
    public SubscriberDto saveSubscriber(SubscriberDto subscriberDto) {

        Subscriber subscriber = subscriberRepo.save(
                SubscriberMapper.INSTANCE.toSubscriber(subscriberDto)
        );
        return SubscriberMapper.INSTANCE.toSubscriberDto(subscriber);
    }
}
