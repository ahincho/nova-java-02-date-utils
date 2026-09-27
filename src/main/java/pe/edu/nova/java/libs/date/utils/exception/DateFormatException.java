package pe.edu.nova.java.libs.date.utils.exception;

/**
 * Excepción lanzada cuando falla una operación de formateo de fechas.
 *
 * @author Nova Platform
 */
public class DateFormatException extends DateException {

    /**
     * Crea una nueva excepción de formateo con el mensaje especificado.
     *
     * @param message mensaje descriptivo del error en español
     */
    public DateFormatException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción de formateo con el mensaje y la causa especificados.
     *
     * @param message mensaje descriptivo del error en español
     * @param cause   causa original de la excepción
     */
    public DateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
