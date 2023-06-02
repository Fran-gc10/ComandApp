package Proyecto.ComandApp.utils.constants;

public class ExceptionConstants {

    public static final String ERROR = "ERROR";

    public static final String MESSAGE_EXISTENT_PROD = "ERROR - Ya existe un producto con ese nombre";


    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR - An internal server error has ocurred";


    private ExceptionConstants() {
        throw new IllegalStateException("Utility Class");
    }

}
