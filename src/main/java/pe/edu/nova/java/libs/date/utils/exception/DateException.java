package pe.edu.nova.java.libs.date.utils.exception;

/**
 * Excepción base para todos los errores de la librería date-utils.
 * <p>
 * Extiende {@link RuntimeException} para no obligar al caller a manejar
 * excepciones con try-catch (unchecked).
 * </p>
 *
 * @author Nova Platform
 */
public class DateException extends RuntimeException {

    /**
     * Crea una nueva excepción con el mensaje especificado.
     *
     * @param message mensaje descriptivo del error en español
     */
    public DateException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción con el mensaje y la causa especificados.
     *
     * @param message mensaje descriptivo del error en español
     * @param cause   causa original de la excepción
     */
    public DateException(String message, Throwable cause) {
        super(message, cause);
    }
}
