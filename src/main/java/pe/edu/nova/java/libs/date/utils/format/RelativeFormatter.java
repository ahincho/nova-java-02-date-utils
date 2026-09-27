package pe.edu.nova.java.libs.date.utils.format;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.Duration;
import java.util.Locale;
import java.util.Map;

import pe.edu.nova.java.libs.date.utils.config.DateConfig;

/**
 * Formateador de fechas relativas ("hace 5 minutos", "5 minutes ago").
 * <p>
 * Soporta español, inglés, portugués y francés.
 * Usa mapas internos de mensajes — cero dependencias externas.
 * </p>
 *
 * @author Nova Platform
 */
public final class RelativeFormatter {

    private RelativeFormatter() {
    }

    // ========================================================================
    // Mapas de mensajes relativos por idioma
    // ========================================================================

    /**
     * Estructura: idioma → clave → [singular, plural]
     * Para "yesterday"/"tomorrow" solo hay un valor.
     */
    private static final Map<String, Map<String, String[]>> MESSAGES = Map.of(
            "es", Map.ofEntries(
                    Map.entry("seconds_ago", new String[]{"hace {0} segundo", "hace {0} segundos"}),
                    Map.entry("minutes_ago", new String[]{"hace {0} minuto", "hace {0} minutos"}),
                    Map.entry("hours_ago", new String[]{"hace {0} hora", "hace {0} horas"}),
                    Map.entry("days_ago", new String[]{"hace {0} día", "hace {0} días"}),
                    Map.entry("yesterday", new String[]{"ayer"}),
                    Map.entry("seconds_future", new String[]{"en {0} segundo", "en {0} segundos"}),
                    Map.entry("minutes_future", new String[]{"en {0} minuto", "en {0} minutos"}),
                    Map.entry("hours_future", new String[]{"en {0} hora", "en {0} horas"}),
                    Map.entry("days_future", new String[]{"en {0} día", "en {0} días"}),
                    Map.entry("tomorrow", new String[]{"mañana"})
            ),
            "en", Map.ofEntries(
                    Map.entry("seconds_ago", new String[]{"{0} second ago", "{0} seconds ago"}),
                    Map.entry("minutes_ago", new String[]{"{0} minute ago", "{0} minutes ago"}),
                    Map.entry("hours_ago", new String[]{"{0} hour ago", "{0} hours ago"}),
                    Map.entry("days_ago", new String[]{"{0} day ago", "{0} days ago"}),
                    Map.entry("yesterday", new String[]{"yesterday"}),
                    Map.entry("seconds_future", new String[]{"in {0} second", "in {0} seconds"}),
                    Map.entry("minutes_future", new String[]{"in {0} minute", "in {0} minutes"}),
                    Map.entry("hours_future", new String[]{"in {0} hour", "in {0} hours"}),
                    Map.entry("days_future", new String[]{"in {0} day", "in {0} days"}),
                    Map.entry("tomorrow", new String[]{"tomorrow"})
            ),
            "pt", Map.ofEntries(
                    Map.entry("seconds_ago", new String[]{"há {0} segundo", "há {0} segundos"}),
                    Map.entry("minutes_ago", new String[]{"há {0} minuto", "há {0} minutos"}),
                    Map.entry("hours_ago", new String[]{"há {0} hora", "há {0} horas"}),
                    Map.entry("days_ago", new String[]{"há {0} dia", "há {0} dias"}),
                    Map.entry("yesterday", new String[]{"ontem"}),
                    Map.entry("seconds_future", new String[]{"em {0} segundo", "em {0} segundos"}),
                    Map.entry("minutes_future", new String[]{"em {0} minuto", "em {0} minutos"}),
                    Map.entry("hours_future", new String[]{"em {0} hora", "em {0} horas"}),
                    Map.entry("days_future", new String[]{"em {0} dia", "em {0} dias"}),
                    Map.entry("tomorrow", new String[]{"amanhã"})
            ),
            "fr", Map.ofEntries(
                    Map.entry("seconds_ago", new String[]{"il y a {0} seconde", "il y a {0} secondes"}),
                    Map.entry("minutes_ago", new String[]{"il y a {0} minute", "il y a {0} minutes"}),
                    Map.entry("hours_ago", new String[]{"il y a {0} heure", "il y a {0} heures"}),
                    Map.entry("days_ago", new String[]{"il y a {0} jour", "il y a {0} jours"}),
                    Map.entry("yesterday", new String[]{"hier"}),
                    Map.entry("seconds_future", new String[]{"dans {0} seconde", "dans {0} secondes"}),
                    Map.entry("minutes_future", new String[]{"dans {0} minute", "dans {0} minutes"}),
                    Map.entry("hours_future", new String[]{"dans {0} heure", "dans {0} heures"}),
                    Map.entry("days_future", new String[]{"dans {0} jour", "dans {0} jours"}),
                    Map.entry("tomorrow", new String[]{"demain"})
            )
    );

    // ========================================================================
    // Métodos públicos
    // ========================================================================

    /**
     * Formatea un {@link LocalDateTime} como distancia relativa desde ahora.
     *
     * @param dateTime fecha y hora a formatear, o null para retornar null
     * @param locale   locale para el idioma de salida
     * @param config   configuración con patrón por defecto para fallback
     * @return string relativo o null si dateTime es null
     */
    public static String format(LocalDateTime dateTime, Locale locale, DateConfig config) {
        if (dateTime == null) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now();
        return formatRelative(dateTime, now, locale, config);
    }

    /**
     * Formatea un {@link LocalDate} como distancia relativa desde hoy.
     *
     * @param date   fecha a formatear, o null para retornar null
     * @param locale locale para el idioma de salida
     * @param config configuración con patrón por defecto para fallback
     * @return string relativo o null si date es null
     */
    public static String format(LocalDate date, Locale locale, DateConfig config) {
        if (date == null) {
            return null;
        }
        return format(date.atStartOfDay(), locale, config);
    }

    /**
     * Formatea un {@link ZonedDateTime} como distancia relativa desde ahora.
     *
     * @param dateTime fecha, hora y zona a formatear, o null para retornar null
     * @param locale   locale para el idioma de salida
     * @param config   configuración con patrón por defecto para fallback
     * @return string relativo o null si dateTime es null
     */
    public static String format(ZonedDateTime dateTime, Locale locale, DateConfig config) {
        if (dateTime == null) {
            return null;
        }
        return format(dateTime.toLocalDateTime(), locale, config);
    }

    /**
     * Formatea un {@link Instant} como distancia relativa desde ahora.
     *
     * @param instant instante a formatear, o null para retornar null
     * @param zoneId  zona horaria para la conversión
     * @param locale  locale para el idioma de salida
     * @param config  configuración con patrón por defecto para fallback
     * @return string relativo o null si instant es null
     */
    public static String format(Instant instant, ZoneId zoneId, Locale locale, DateConfig config) {
        if (instant == null) {
            return null;
        }
        ZoneId zone = zoneId != null ? zoneId : ZoneId.systemDefault();
        return format(LocalDateTime.ofInstant(instant, zone), locale, config);
    }

    /**
     * Formatea un {@link LocalDateTime} como distancia relativa con locale.
     *
     * @param dateTime fecha y hora a formatear, o null para retornar null
     * @param locale   locale para el idioma de salida
     * @return string relativo o null si dateTime es null
     */
    public static String format(LocalDateTime dateTime, Locale locale) {
        return format(dateTime, locale, DateConfig.defaults());
    }

    /**
     * Formatea un {@link LocalDateTime} como distancia relativa con valores por defecto.
     *
     * @param dateTime fecha y hora a formatear, o null para retornar null
     * @return string relativo o null si dateTime es null
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, null, DateConfig.defaults());
    }

    // ========================================================================
    // Lógica interna
    // ========================================================================

    private static String formatRelative(LocalDateTime dateTime, LocalDateTime now,
                                         Locale locale, DateConfig config) {
        Duration duration = Duration.between(dateTime, now);
        long totalSeconds = duration.getSeconds();
        boolean isPast = totalSeconds >= 0;
        long absSeconds = Math.abs(totalSeconds);

        Map<String, String[]> msgs = resolveMessages(locale);

        // Segundos: 0-60
        if (absSeconds < 60) {
            long value = absSeconds;
            return getMessage(msgs, isPast ? "seconds_ago" : "seconds_future", value);
        }

        long absMinutes = absSeconds / 60;
        // Minutos: 1-59
        if (absMinutes < 60) {
            return getMessage(msgs, isPast ? "minutes_ago" : "minutes_future", absMinutes);
        }

        long absHours = absMinutes / 60;
        // Horas: 1-23
        if (absHours < 24) {
            return getMessage(msgs, isPast ? "hours_ago" : "hours_future", absHours);
        }

        long absDays = absHours / 24;
        // Ayer / Mañana
        if (absDays == 1) {
            String key = isPast ? "yesterday" : "tomorrow";
            String[] templates = msgs.get(key);
            return templates != null ? templates[0] : (isPast ? "yesterday" : "tomorrow");
        }

        // 2-6 días
        if (absDays >= 2 && absDays <= 6) {
            return getMessage(msgs, isPast ? "days_ago" : "days_future", absDays);
        }

        // >= 7 días: formato absoluto
        Locale resolvedLocale = locale != null ? locale : config.getLocale();
        return DateFormatter.format(dateTime, config.getDefaultPattern(), resolvedLocale, config);
    }

    private static Map<String, String[]> resolveMessages(Locale locale) {
        if (locale == null) {
            return MESSAGES.getOrDefault("en", MESSAGES.get("en"));
        }
        String lang = locale.getLanguage();
        return MESSAGES.getOrDefault(lang, MESSAGES.get("en"));
    }

    private static String getMessage(Map<String, String[]> msgs, String key, long value) {
        String[] templates = msgs.get(key);
        if (templates == null) {
            return String.valueOf(value);
        }
        String template = (value == 1 && templates.length > 0) ? templates[0]
                : (templates.length > 1 ? templates[1] : templates[0]);
        return template.replace("{0}", String.valueOf(value));
    }
}
