package Proyecto.ComandApp.exceptions;

import Proyecto.ComandApp.responses.ComandAppResponse;
import Proyecto.ComandApp.utils.constants.ExceptionConstants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ComandAppRestExceptionHandler {

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ComandAppResponse unhandledErrors(HttpServletRequest req, Exception ex) {
        return new ComandAppResponse(ExceptionConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
    }


    public ComandAppResponse handleLmException(final HttpServletRequest request, final HttpServletResponse response, final ComandAppException ex) {
        response.setStatus(ex.getCode());
        return new ComandAppResponse(ExceptionConstants.ERROR, String.valueOf(ex.getCode()), ex.getMessage(),
                ex.getErrorList());
    }
}
