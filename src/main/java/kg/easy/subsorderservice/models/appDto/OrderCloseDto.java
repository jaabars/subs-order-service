package kg.easy.subsorderservice.models.appDto;

import kg.easy.subsorderservice.models.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderCloseDto {

    private OrderStatus status;
    private Long orderId;
    private String comment;

    /*

        1. Найти заявку по ИД
        2. Проверить статус (IN_PROCESS)
        3. Создаем новый статус с комментарием

     */
}
