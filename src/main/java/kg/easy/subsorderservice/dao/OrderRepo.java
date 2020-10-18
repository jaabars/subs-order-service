package kg.easy.subsorderservice.dao;

import kg.easy.subsorderservice.models.Order;
import kg.easy.subsorderservice.models.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findTopBySubscriberOrderByIdDesc(Subscriber subscriber);

}
