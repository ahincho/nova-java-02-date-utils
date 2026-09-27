package pe.edu.nova.java.libs.date.utils.pattern;

import java.time.format.DateTimeFormatter;

import pe.edu.nova.java.libs.date.utils.exception.DateFormatException;

/**
 * Validador de patrones de formato de {@link DateTimeFormatter}.
 * <p>
 * Usado internamente por DateFormatter, DateParser y DatePatterns.
 * </p>
 *
 * @author Nova Platform
 */
public final class PatternValidator {

    private PatternValidator() {
    }

    /**
     * Valida que el patrón tenga sintaxis válida de {@link DateTimeFormatter}.
     *
     * @param pattern patrón a validar
     * @return true si el patrón es válido, false en caso contrario (incluyendo null y vacío)
     */
    public static boolean isValid(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter.ofPattern(pattern);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Valida el patrón y lanza excepción si es inválido.
     *
     * @param pattern patrón a validar
     * @throws DateFormatException si el patrón es null, vacío o tiene sintaxis inválida
     */
    public static void validate(String pattern) {
        if (pattern == null || pattern.isEmpty()) {
            throw new DateFormatException(
                    "Patrón de formato inválido: '%s'. El patrón no puede ser nulo o vacío".formatted(pattern));
        }
        try {
            DateTimeFormatter.ofPattern(pattern);
        } catch (IllegalArgumentException e) {
            throw new DateFormatException(
                    "Patrón de formato inválido: '%s'. %s".formatted(pattern, e.getMessage()), e);
        }
    }
}
