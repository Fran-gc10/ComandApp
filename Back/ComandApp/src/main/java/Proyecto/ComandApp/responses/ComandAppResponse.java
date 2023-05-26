package Proyecto.ComandApp.responses;

import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class ComandAppResponse<T> implements Serializable {

    private String status;
    private String code;
    private String message;
    private T data;

    private static final long serialVersionUID = 7302319210373510173L;


    public ComandAppResponse() {
        super();
    }

    public ComandAppResponse(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ComandAppResponse(String status, String code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
