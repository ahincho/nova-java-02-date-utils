package pe.edu.nova.java.libs.date.utils.format;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

import pe.edu.nova.java.libs.date.utils.config.DateConfig;
import pe.edu.nova.java.libs.date.utils.exception.DateFormatException;
import pe.edu.nova.java.libs.date.utils.pattern.PatternValidator;

/**
 * Formateador de tipos temporales a representación String.
 * <p>
 * Soporta todos los tipos de Java 8+ ({@link LocalDate}, {@link LocalDateTime},
 * {@link ZonedDateTime}, {@link OffsetDateTime}, {@link Instant}) y
 * {@link java.util.Date} legacy.
 * </p>
 * <p>
 * Thread-safe: todos los métodos son estáticos y stateless.
 * </p>
 *
 * @author Nova Platform
 */
public final class DateFormatter {

    private DateFormatter() {
    }

    // ========================================================================
    // LocalDate
    // ========================================================================

    /**
     * Formatea un {@link LocalDate} con patrón, locale y configuración.
     *
     * @param date    fecha a formatear, o null para retornar null
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @param locale  locale para el idioma, o null para usar el locale por defecto
     * @param config  configuración con valores por defecto
     * @return representación string de la fecha, o null si date es null
     * @throws DateFormatException si el patrón es inválido o ocurre un error de formateo
     */
    public static String format(LocalDate date, String pattern, Locale locale, DateConfig config) {
        if (date == null) {
            return null;
        }
        return doFormat(date, resolvePattern(pattern, config), resolveLocale(locale, config));
    }

    /**
     * Formatea un {@link LocalDate} con patrón y locale.
     *
     * @param date    fecha a formatear, o null para retornar null
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @param locale  locale para el idioma, o null para usar el locale por defecto
     * @return representación string de la fecha, o null si date es null
     */
    public static String format(LocalDate date, String pattern, Locale locale) {
        return format(date, pattern, locale, DateConfig.defaults());
    }

    /**
     * Formatea un {@link LocalDate} con patrón.
     *
     * @param date    fecha a formatear, o null para retornar null
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @return representación string de la fecha, o null si date es null
     */
    public static String format(LocalDate date, String pattern) {
        return format(date, pattern, null, DateConfig.defaults());
    }

    /**
     * Formatea un {@link LocalDate} con el patrón por defecto.
     *
     * @param date fecha a formatear, o null para retornar null
     * @return representación string de la fecha, o null si date es null
     */
    public static String format(LocalDate date) {
        return format(date, null, null, DateConfig.defaults());
    }

    // ========================================================================
    // LocalDateTime
    // ========================================================================

    /**
     * Formatea un {@link LocalDateTime} con patrón, locale y configuración.
     *
     * @param dateTime fecha y hora a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @param locale   locale para el idioma, o null para usar el locale por defecto
     * @param config   configuración con valores por defecto
     * @return representación string de la fecha y hora, o null si dateTime es null
     */
    public static String format(LocalDateTime dateTime, String pattern, Locale locale, DateConfig config) {
        if (dateTime == null) {
            return null;
        }
        return doFormat(dateTime, resolvePattern(pattern, config), resolveLocale(locale, config));
    }

    /**
     * Formatea un {@link LocalDateTime} con patrón y locale.
     *
     * @param dateTime fecha y hora a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @param locale   locale para el idioma, o null para usar el locale por defecto
     * @return representación string de la fecha y hora, o null si dateTime es null
     */
    public static String format(LocalDateTime dateTime, String pattern, Locale locale) {
        return format(dateTime, pattern, locale, DateConfig.defaults());
    }

    /**
     * Formatea un {@link LocalDateTime} con patrón.
     *
     * @param dateTime fecha y hora a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @return representación string de la fecha y hora, o null si dateTime es null
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return format(dateTime, pattern, null, DateConfig.defaults());
    }

    /**
     * Formatea un {@link LocalDateTime} con el patrón por defecto.
     *
     * @param dateTime fecha y hora a formatear, o null para retornar null
     * @return representación string de la fecha y hora, o null si dateTime es null
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, null, null, DateConfig.defaults());
    }

    // ========================================================================
    // ZonedDateTime
    // ========================================================================

    /**
     * Formatea un {@link ZonedDateTime} con patrón, locale y configuración.
     *
     * @param dateTime fecha, hora y zona a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @param locale   locale para el idioma, o null para usar el locale por defecto
     * @param config   configuración con valores por defecto
     * @return representación string de la fecha, hora y zona, o null si dateTime es null
     */
    public static String format(ZonedDateTime dateTime, String pattern, Locale locale, DateConfig config) {
        if (dateTime == null) {
            return null;
        }
        return doFormat(dateTime, resolvePattern(pattern, config), resolveLocale(locale, config));
    }

    /**
     * Formatea un {@link ZonedDateTime} con patrón y locale.
     *
     * @param dateTime fecha, hora y zona a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @param locale   locale para el idioma, o null para usar el locale por defecto
     * @return representación string, o null si dateTime es null
     */
    public static String format(ZonedDateTime dateTime, String pattern, Locale locale) {
        return format(dateTime, pattern, locale, DateConfig.defaults());
    }

    /**
     * Formatea un {@link ZonedDateTime} con patrón.
     *
     * @param dateTime fecha, hora y zona a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @return representación string, o null si dateTime es null
     */
    public static String format(ZonedDateTime dateTime, String pattern) {
        return format(dateTime, pattern, null, DateConfig.defaults());
    }

    /**
     * Formatea un {@link ZonedDateTime} con el patrón por defecto.
     *
     * @param dateTime fecha, hora y zona a formatear, o null para retornar null
     * @return representación string, o null si dateTime es null
     */
    public static String format(ZonedDateTime dateTime) {
        return format(dateTime, null, null, DateConfig.defaults());
    }

    /**
     * Formatea un {@link ZonedDateTime} convirtiendo a la zona horaria destino.
     *
     * @param dateTime   fecha, hora y zona a formatear, o null para retornar null
     * @param targetZone zona horaria destino
     * @param pattern    patrón de formato, o null para usar el patrón por defecto
     * @param locale     locale para el idioma, o null para usar el locale por defecto
     * @return representación string en la zona destino, o null si dateTime es null
     * @throws DateFormatException si la zona horaria es inválida
     */
    public static String format(ZonedDateTime dateTime, ZoneId targetZone, String pattern, Locale locale) {
        if (dateTime == null) {
            return null;
        }
        if (targetZone == null) {
            throw new DateFormatException("Zona horaria inválida: 'null'");
        }
        ZonedDateTime converted = dateTime.withZoneSameInstant(targetZone);
        DateConfig config = DateConfig.defaults();
        return doFormat(converted, resolvePattern(pattern, config), resolveLocale(locale, config));
    }

    // ========================================================================
    // OffsetDateTime
    // ========================================================================

    /**
     * Formatea un {@link OffsetDateTime} con patrón, locale y configuración.
     *
     * @param dateTime fecha, hora y offset a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @param locale   locale para el idioma, o null para usar el locale por defecto
     * @param config   configuración con valores por defecto
     * @return representación string, o null si dateTime es null
     */
    public static String format(OffsetDateTime dateTime, String pattern, Locale locale, DateConfig config) {
        if (dateTime == null) {
            return null;
        }
        return doFormat(dateTime, resolvePattern(pattern, config), resolveLocale(locale, config));
    }

    /**
     * Formatea un {@link OffsetDateTime} con patrón y locale.
     *
     * @param dateTime fecha, hora y offset a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @param locale   locale para el idioma, o null para usar el locale por defecto
     * @return representación string, o null si dateTime es null
     */
    public static String format(OffsetDateTime dateTime, String pattern, Locale locale) {
        return format(dateTime, pattern, locale, DateConfig.defaults());
    }

    /**
     * Formatea un {@link OffsetDateTime} con patrón.
     *
     * @param dateTime fecha, hora y offset a formatear, o null para retornar null
     * @param pattern  patrón de formato, o null para usar el patrón por defecto
     * @return representación string, o null si dateTime es null
     */
    public static String format(OffsetDateTime dateTime, String pattern) {
        return format(dateTime, pattern, null, DateConfig.defaults());
    }

    /**
     * Formatea un {@link OffsetDateTime} con el patrón por defecto.
     *
     * @param dateTime fecha, hora y offset a formatear, o null para retornar null
     * @return representación string, o null si dateTime es null
     */
    public static String format(OffsetDateTime dateTime) {
        return format(dateTime, null, null, DateConfig.defaults());
    }

    // ========================================================================
    // Instant
    // ========================================================================

    /**
     * Formatea un {@link Instant} con zona horaria, patrón y locale.
     *
     * @param instant instante a formatear, o null para retornar null
     * @param zoneId  zona horaria para la representación
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @param locale  locale para el idioma, o null para usar el locale por defecto
     * @return representación string en la zona especificada, o null si instant es null
     * @throws DateFormatException si la zona horaria es null
     */
    public static String format(Instant instant, ZoneId zoneId, String pattern, Locale locale) {
        if (instant == null) {
            return null;
        }
        if (zoneId == null) {
            throw new DateFormatException("Zona horaria inválida: 'null'");
        }
        ZonedDateTime zdt = instant.atZone(zoneId);
        DateConfig config = DateConfig.defaults();
        return doFormat(zdt, resolvePattern(pattern, config), resolveLocale(locale, config));
    }

    /**
     * Formatea un {@link Instant} con zona horaria y patrón.
     *
     * @param instant instante a formatear, o null para retornar null
     * @param zoneId  zona horaria para la representación
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @return representación string, o null si instant es null
     */
    public static String format(Instant instant, ZoneId zoneId, String pattern) {
        return format(instant, zoneId, pattern, null);
    }

    /**
     * Formatea un {@link Instant} con zona horaria y patrón por defecto.
     *
     * @param instant instante a formatear, o null para retornar null
     * @param zoneId  zona horaria para la representación
     * @return representación string, o null si instant es null
     */
    public static String format(Instant instant, ZoneId zoneId) {
        return format(instant, zoneId, null, null);
    }

    // ========================================================================
    // java.util.Date (legacy)
    // ========================================================================

    /**
     * Formatea un {@link java.util.Date} con patrón, zona horaria y locale.
     *
     * @param date   fecha legacy a formatear, o null para retornar null
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @param zoneId  zona horaria para la representación, o null para usar la del sistema
     * @param locale  locale para el idioma, o null para usar el locale por defecto
     * @return representación string, o null si date es null
     */
    public static String format(Date date, String pattern, ZoneId zoneId, Locale locale) {
        if (date == null) {
            return null;
        }
        ZoneId zone = zoneId != null ? zoneId : ZoneId.systemDefault();
        ZonedDateTime zdt = date.toInstant().atZone(zone);
        DateConfig config = DateConfig.defaults();
        return doFormat(zdt, resolvePattern(pattern, config), resolveLocale(locale, config));
    }

    /**
     * Formatea un {@link java.util.Date} con patrón y zona horaria.
     *
     * @param date    fecha legacy a formatear, o null para retornar null
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @param zoneId  zona horaria para la representación
     * @return representación string, o null si date es null
     */
    public static String format(Date date, String pattern, ZoneId zoneId) {
        return format(date, pattern, zoneId, null);
    }

    /**
     * Formatea un {@link java.util.Date} con patrón.
     *
     * @param date    fecha legacy a formatear, o null para retornar null
     * @param pattern patrón de formato, o null para usar el patrón por defecto
     * @return representación string, o null si date es null
     */
    public static String format(Date date, String pattern) {
        return format(date, pattern, null, null);
    }

    /**
     * Formatea un {@link java.util.Date} con el patrón por defecto.
     *
     * @param date fecha legacy a formatear, o null para retornar null
     * @return representación string, o null si date es null
     */
    public static String format(Date date) {
        return format(date, null, null, null);
    }

    // ========================================================================
    // Métodos internos
    // ========================================================================

    private static String resolvePattern(String pattern, DateConfig config) {
        return pattern != null ? pattern : config.getDefaultPattern();
    }

    private static Locale resolveLocale(Locale locale, DateConfig config) {
        return locale != null ? locale : config.getLocale();
    }

    private static String doFormat(TemporalAccessor temporal, String pattern, Locale locale) {
        PatternValidator.validate(pattern);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale);
            return formatter.format(temporal);
        } catch (Exception e) {
            throw new DateFormatException(
                    "Error al formatear la fecha '%s' con el patrón '%s': %s"
                            .formatted(temporal, pattern, e.getMessage()), e);
        }
    }
}
