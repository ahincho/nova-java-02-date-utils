package pe.edu.nova.java.libs.date.utils.parse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;

import pe.edu.nova.java.libs.date.utils.config.DateConfig;
import pe.edu.nova.java.libs.date.utils.exception.DateParseException;
import pe.edu.nova.java.libs.date.utils.pattern.PatternValidator;

/**
 * Parseador de cadenas de texto a tipos temporales de Java 8+.
 * <p>
 * Soporta modo estricto y leniente, multi-locale y múltiples patrones.
 * </p>
 *
 * @author Nova Platform
 */
public final class DateParser {

    private DateParser() {
    }

    // ========================================================================
    // parseLocalDate
    // ========================================================================

    /**
     * Parsea un texto a {@link LocalDate} con patrón y locale.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param locale  locale para el idioma
     * @return objeto LocalDate, o null si text es null o vacío
     * @throws DateParseException si el patrón es inválido o el texto no coincide
     */
    public static LocalDate parseLocalDate(String text, String pattern, Locale locale) {
        return parseLocalDateStrict(text, pattern, locale);
    }

    /**
     * Parsea un texto a {@link LocalDate} con patrón.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @return objeto LocalDate, o null si text es null o vacío
     */
    public static LocalDate parseLocalDate(String text, String pattern) {
        return parseLocalDate(text, pattern, DateConfig.defaults().getLocale());
    }

    /**
     * Parsea un texto a {@link LocalDate} con el patrón ISO por defecto.
     *
     * @param text texto a parsear, o null/vacío para retornar null
     * @return objeto LocalDate, o null si text es null o vacío
     */
    public static LocalDate parseLocalDate(String text) {
        return parseLocalDate(text, DateConfig.defaults().getDefaultPattern());
    }

    // ========================================================================
    // parseLocalDateTime
    // ========================================================================

    /**
     * Parsea un texto a {@link LocalDateTime} con patrón y locale.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param locale  locale para el idioma
     * @return objeto LocalDateTime, o null si text es null o vacío
     * @throws DateParseException si el patrón es inválido o el texto no coincide
     */
    public static LocalDateTime parseLocalDateTime(String text, String pattern, Locale locale) {
        return parseLocalDateTimeStrict(text, pattern, locale);
    }

    /**
     * Parsea un texto a {@link LocalDateTime} con patrón.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @return objeto LocalDateTime, o null si text es null o vacío
     */
    public static LocalDateTime parseLocalDateTime(String text, String pattern) {
        return parseLocalDateTime(text, pattern, DateConfig.defaults().getLocale());
    }

    /**
     * Parsea un texto a {@link LocalDateTime} con el patrón ISO por defecto.
     *
     * @param text texto a parsear, o null/vacío para retornar null
     * @return objeto LocalDateTime, o null si text es null o vacío
     */
    public static LocalDateTime parseLocalDateTime(String text) {
        return parseLocalDateTime(text, DateConfig.defaults().getDefaultPattern());
    }

    // ========================================================================
    // parseZonedDateTime
    // ========================================================================

    /**
     * Parsea un texto a {@link ZonedDateTime} con patrón, zona y locale.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param zoneId  zona horaria a asignar
     * @param locale  locale para el idioma
     * @return objeto ZonedDateTime, o null si text es null o vacío
     * @throws DateParseException si el patrón es inválido o el texto no coincide
     */
    public static ZonedDateTime parseZonedDateTime(String text, String pattern, ZoneId zoneId, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        validatePattern(pattern);
        try {
            DateTimeFormatter formatter = createFormatter(pattern, locale, ResolverStyle.STRICT);
            LocalDateTime ldt = LocalDateTime.parse(text, formatter);
            return ldt.atZone(zoneId);
        } catch (DateTimeParseException e) {
            throw new DateParseException(
                    "No se pudo parsear el texto '%s' con el patrón '%s'".formatted(text, pattern), e);
        }
    }

    /**
     * Parsea un texto a {@link ZonedDateTime} con patrón y zona.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param zoneId  zona horaria a asignar
     * @return objeto ZonedDateTime, o null si text es null o vacío
     */
    public static ZonedDateTime parseZonedDateTime(String text, String pattern, ZoneId zoneId) {
        return parseZonedDateTime(text, pattern, zoneId, DateConfig.defaults().getLocale());
    }

    // ========================================================================
    // parseOffsetDateTime
    // ========================================================================

    /**
     * Parsea un texto a {@link OffsetDateTime} con patrón, offset y locale.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param offset  offset a asignar
     * @param locale  locale para el idioma
     * @return objeto OffsetDateTime, o null si text es null o vacío
     * @throws DateParseException si el patrón es inválido o el texto no coincide
     */
    public static OffsetDateTime parseOffsetDateTime(String text, String pattern, ZoneOffset offset, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        validatePattern(pattern);
        try {
            DateTimeFormatter formatter = createFormatter(pattern, locale, ResolverStyle.STRICT);
            LocalDateTime ldt = LocalDateTime.parse(text, formatter);
            return ldt.atOffset(offset);
        } catch (DateTimeParseException e) {
            throw new DateParseException(
                    "No se pudo parsear el texto '%s' con el patrón '%s'".formatted(text, pattern), e);
        }
    }

    /**
     * Parsea un texto a {@link OffsetDateTime} con patrón y offset.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param offset  offset a asignar
     * @return objeto OffsetDateTime, o null si text es null o vacío
     */
    public static OffsetDateTime parseOffsetDateTime(String text, String pattern, ZoneOffset offset) {
        return parseOffsetDateTime(text, pattern, offset, DateConfig.defaults().getLocale());
    }

    // ========================================================================
    // Modo estricto / leniente — LocalDate
    // ========================================================================

    /**
     * Parsea un texto a {@link LocalDate} en modo estricto.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param locale  locale para el idioma
     * @return objeto LocalDate, o null si text es null o vacío
     * @throws DateParseException si el texto no coincide exactamente con el patrón
     */
    public static LocalDate parseLocalDateStrict(String text, String pattern, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        validatePattern(pattern);
        try {
            DateTimeFormatter formatter = createFormatter(pattern, locale, ResolverStyle.STRICT);
            return LocalDate.parse(text, formatter);
        } catch (DateTimeParseException e) {
            throw new DateParseException(
                    "No se pudo parsear el texto '%s' en modo estricto con el patrón '%s'"
                            .formatted(text, pattern), e);
        }
    }

    /**
     * Parsea un texto a {@link LocalDate} en modo leniente.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param locale  locale para el idioma
     * @return objeto LocalDate, o null si text es null o vacío
     * @throws DateParseException si el texto no puede ser parseado
     */
    public static LocalDate parseLocalDateLenient(String text, String pattern, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        validatePattern(pattern);
        try {
            DateTimeFormatter formatter = createFormatter(pattern, locale, ResolverStyle.LENIENT);
            return LocalDate.parse(text, formatter);
        } catch (DateTimeParseException e) {
            throw new DateParseException(
                    "No se pudo parsear el texto '%s' con el patrón '%s'".formatted(text, pattern), e);
        }
    }

    // ========================================================================
    // Modo estricto / leniente — LocalDateTime
    // ========================================================================

    /**
     * Parsea un texto a {@link LocalDateTime} en modo estricto.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param locale  locale para el idioma
     * @return objeto LocalDateTime, o null si text es null o vacío
     * @throws DateParseException si el texto no coincide exactamente con el patrón
     */
    public static LocalDateTime parseLocalDateTimeStrict(String text, String pattern, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        validatePattern(pattern);
        try {
            DateTimeFormatter formatter = createFormatter(pattern, locale, ResolverStyle.STRICT);
            return LocalDateTime.parse(text, formatter);
        } catch (DateTimeParseException e) {
            throw new DateParseException(
                    "No se pudo parsear el texto '%s' en modo estricto con el patrón '%s'"
                            .formatted(text, pattern), e);
        }
    }

    /**
     * Parsea un texto a {@link LocalDateTime} en modo leniente.
     *
     * @param text    texto a parsear, o null/vacío para retornar null
     * @param pattern patrón de formato
     * @param locale  locale para el idioma
     * @return objeto LocalDateTime, o null si text es null o vacío
     * @throws DateParseException si el texto no puede ser parseado
     */
    public static LocalDateTime parseLocalDateTimeLenient(String text, String pattern, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        validatePattern(pattern);
        try {
            DateTimeFormatter formatter = createFormatter(pattern, locale, ResolverStyle.LENIENT);
            return LocalDateTime.parse(text, formatter);
        } catch (DateTimeParseException e) {
            throw new DateParseException(
                    "No se pudo parsear el texto '%s' con el patrón '%s'".formatted(text, pattern), e);
        }
    }

    // ========================================================================
    // Parseo con múltiples patrones
    // ========================================================================

    /**
     * Parsea un texto a {@link LocalDate} intentando múltiples patrones.
     *
     * @param text     texto a parsear, o null/vacío para retornar null
     * @param patterns lista ordenada de patrones a intentar
     * @param locale   locale para el idioma
     * @return objeto LocalDate del primer patrón exitoso, o null si text es null o vacío
     * @throws DateParseException si la lista está vacía o ningún patrón coincide
     */
    public static LocalDate parseLocalDate(String text, List<String> patterns, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        if (patterns == null || patterns.isEmpty()) {
            throw new DateParseException("La lista de patrones de formato está vacía");
        }
        for (String pattern : patterns) {
            try {
                return parseLocalDateStrict(text, pattern, locale);
            } catch (DateParseException ignored) {
                // Intentar siguiente patrón
            }
        }
        throw new DateParseException(
                "No se pudo parsear el texto '%s' con ninguno de los patrones: %s"
                        .formatted(text, patterns));
    }

    /**
     * Parsea un texto a {@link LocalDateTime} intentando múltiples patrones.
     *
     * @param text     texto a parsear, o null/vacío para retornar null
     * @param patterns lista ordenada de patrones a intentar
     * @param locale   locale para el idioma
     * @return objeto LocalDateTime del primer patrón exitoso, o null si text es null o vacío
     * @throws DateParseException si la lista está vacía o ningún patrón coincide
     */
    public static LocalDateTime parseLocalDateTime(String text, List<String> patterns, Locale locale) {
        if (isNullOrEmpty(text)) {
            return null;
        }
        if (patterns == null || patterns.isEmpty()) {
            throw new DateParseException("La lista de patrones de formato está vacía");
        }
        for (String pattern : patterns) {
            try {
                return parseLocalDateTimeStrict(text, pattern, locale);
            } catch (DateParseException ignored) {
                // Intentar siguiente patrón
            }
        }
        throw new DateParseException(
                "No se pudo parsear el texto '%s' con ninguno de los patrones: %s"
                        .formatted(text, patterns));
    }

    // ========================================================================
    // Métodos internos
    // ========================================================================

    private static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    private static void validatePattern(String pattern) {
        try {
            PatternValidator.validate(pattern);
        } catch (pe.edu.nova.java.libs.date.utils.exception.DateFormatException e) {
            throw new DateParseException(
                    "Patrón de formato inválido: '%s'. %s".formatted(pattern, e.getMessage()), e);
        }
    }

    private static DateTimeFormatter createFormatter(String pattern, Locale locale, ResolverStyle resolverStyle) {
        Locale resolvedLocale = locale != null ? locale : DateConfig.defaults().getLocale();
        return DateTimeFormatter.ofPattern(pattern, resolvedLocale)
                .withResolverStyle(resolverStyle);
    }
}
