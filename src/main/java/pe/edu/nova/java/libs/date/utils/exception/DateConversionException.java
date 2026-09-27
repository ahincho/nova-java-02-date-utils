package pe.edu.nova.java.libs.date.utils.exception;

/**
 * Excepción lanzada cuando falla una operación de conversión entre tipos temporales.
 *
 * @author Nova Platform
 */
public class DateConversionException extends DateException {

    /**
     * Crea una nueva excepción de conversión con el mensaje especificado.
     *
     * @param message mensaje descriptivo del error en español
     */
    public DateConversionException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción de conversión con el mensaje y la causa especificados.
     *
     * @param message mensaje descriptivo del error en español
     * @param cause   causa original de la excepción
     */
    public DateConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}
