package kg.easy.subsorderservice.models.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private int status;
    private String message;
    private Object object;

    public static Response getResponse(){

        return Response.builder()
                .status(1)
                .message("Успешно!")
                .build();
    }

}
