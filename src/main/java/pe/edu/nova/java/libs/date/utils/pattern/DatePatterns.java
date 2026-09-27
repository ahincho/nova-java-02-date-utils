package pe.edu.nova.java.libs.date.utils.pattern;

import java.util.concurrent.ConcurrentHashMap;

import pe.edu.nova.java.libs.date.utils.exception.DateException;

/**
 * Constantes de patrones de formato de fecha/hora organizados por región.
 * <p>
 * Incluye registro thread-safe de patrones personalizados mediante
 * {@link ConcurrentHashMap}.
 * </p>
 *
 * @author Nova Platform
 */
public final class DatePatterns {

    private DatePatterns() {
    }

    // --- Patrones ISO ---

    /** Patrón ISO de solo fecha: {@code yyyy-MM-dd} */
    public static final String ISO_DATE = "yyyy-MM-dd";

    /** Patrón ISO de fecha y hora: {@code yyyy-MM-dd'T'HH:mm:ss} */
    public static final String ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";

    /** Patrón ISO de fecha, hora y milisegundos: {@code yyyy-MM-dd'T'HH:mm:ss.SSS} */
    public static final String ISO_DATE_TIME_MILLIS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    // --- Patrones europeos/latinoamericanos ---

    /** Patrón europeo/latinoamericano de solo fecha: {@code dd/MM/yyyy} */
    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    /** Patrón europeo/latinoamericano de fecha y hora (sin segundos): {@code dd/MM/yyyy HH:mm} */
    public static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";

    /** Patrón europeo/latinoamericano de fecha y hora: {@code dd/MM/yyyy HH:mm:ss} */
    public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";

    /** Patrón europeo/latinoamericano con guión: {@code dd-MM-yyyy} */
    public static final String DD_MM_YYYY_DASH = "dd-MM-yyyy";

    // --- Patrones americanos ---

    /** Patrón americano de solo fecha: {@code MM/dd/yyyy} */
    public static final String MM_DD_YYYY = "MM/dd/yyyy";

    /** Patrón americano de fecha y hora: {@code MM/dd/yyyy HH:mm} */
    public static final String MM_DD_YYYY_HH_MM = "MM/dd/yyyy HH:mm";

    // --- Patrones brasileños ---

    /** Patrón brasileño de solo fecha: {@code dd/MM/yyyy} */
    public static final String DD_MM_YYYY_BR = "dd/MM/yyyy";

    // --- Patrones con nombre de mes ---

    /** Patrón español con nombre de mes: {@code dd 'de' MMMM 'de' yyyy} */
    public static final String DD_MMMM_YYYY_ES = "dd 'de' MMMM 'de' yyyy";

    /** Patrón inglés con nombre de mes: {@code MMMM dd, yyyy} */
    public static final String MMMM_DD_YYYY_EN = "MMMM dd, yyyy";

    // --- Patrones de solo hora ---

    /** Patrón de hora y minutos (24h): {@code HH:mm} */
    public static final String HH_MM = "HH:mm";

    /** Patrón de hora, minutos y segundos (24h): {@code HH:mm:ss} */
    public static final String HH_MM_SS = "HH:mm:ss";

    /** Patrón de hora, minutos y segundos (12h con AM/PM): {@code hh:mm:ss a} */
    public static final String HH_MM_SS_12H = "hh:mm:ss a";

    // --- Registro de patrones personalizados ---

    private static final ConcurrentHashMap<String, String> CUSTOM_PATTERNS = new ConcurrentHashMap<>();

    /**
     * Registra un patrón personalizado con el nombre especificado.
     *
     * @param name    nombre único del patrón
     * @param pattern patrón de formato con sintaxis válida de {@link java.time.format.DateTimeFormatter}
     * @throws DateException si el nombre es null o vacío, el patrón es null o vacío,
     *                       el nombre ya está registrado, o el patrón tiene sintaxis inválida
     */
    public static void register(String name, String pattern) {
        if (name == null || name.isEmpty()) {
            throw new DateException("El nombre del patrón es obligatorio");
        }
        if (pattern == null || pattern.isEmpty()) {
            throw new DateException("El patrón de formato es obligatorio");
        }
        if (CUSTOM_PATTERNS.containsKey(name)) {
            throw new DateException("El patrón '%s' ya está registrado".formatted(name));
        }
        if (!PatternValidator.isValid(pattern)) {
            throw new DateException(
                    "No se puede registrar el patrón '%s': el patrón '%s' tiene sintaxis inválida"
                            .formatted(name, pattern));
        }
        CUSTOM_PATTERNS.put(name, pattern);
    }

    /**
     * Obtiene un patrón registrado por nombre.
     *
     * @param name nombre del patrón registrado
     * @return patrón de formato asociado al nombre
     * @throws DateException si el nombre no está registrado
     */
    public static String get(String name) {
        String pattern = CUSTOM_PATTERNS.get(name);
        if (pattern == null) {
            throw new DateException("Patrón no encontrado: '%s'".formatted(name));
        }
        return pattern;
    }

    /**
     * Verifica si un patrón tiene sintaxis válida de {@link java.time.format.DateTimeFormatter}.
     *
     * @param pattern patrón a verificar
     * @return true si el patrón es válido, false en caso contrario
     */
    public static boolean isValidPattern(String pattern) {
        return PatternValidator.isValid(pattern);
    }
}
