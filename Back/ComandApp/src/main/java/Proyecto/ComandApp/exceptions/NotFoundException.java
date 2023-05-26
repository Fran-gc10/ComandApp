package Proyecto.ComandApp.exceptions;

import Proyecto.ComandApp.errorDto.ErrorDto;
import org.springframework.http.HttpStatus;
import java.util.Arrays;

public class NotFoundException extends ComandAppException{

    private static final long serialVersionUID = -6870732210014274010L;

    public NotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundException(final String message, final ErrorDto data) {
        super(HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
    }
}
