package pe.edu.nova.java.libs.date.utils.config;

import java.time.ZoneId;
import java.util.Locale;

/**
 * Configuración inmutable para operaciones de fecha.
 * <p>
 * Define locale, zona horaria y patrón por defecto.
 * Thread-safe por inmutabilidad.
 * </p>
 *
 * @author Nova Platform
 */
public final class DateConfig {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd";

    private final Locale locale;
    private final ZoneId zoneId;
    private final String defaultPattern;

    private DateConfig(Builder builder) {
        this.locale = builder.locale != null ? builder.locale : Locale.getDefault();
        this.zoneId = builder.zoneId != null ? builder.zoneId : ZoneId.systemDefault();
        this.defaultPattern = builder.defaultPattern != null ? builder.defaultPattern : DEFAULT_PATTERN;
    }

    /**
     * Retorna configuración con valores del sistema.
     *
     * @return configuración por defecto con locale del sistema, zona del sistema y patrón ISO
     */
    public static DateConfig defaults() {
        return new Builder().build();
    }

    /**
     * Retorna el locale configurado.
     *
     * @return locale para formateo y parseo
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Retorna la zona horaria configurada.
     *
     * @return zona horaria por defecto
     */
    public ZoneId getZoneId() {
        return zoneId;
    }

    /**
     * Retorna el patrón de formato por defecto.
     *
     * @return patrón de formato por defecto
     */
    public String getDefaultPattern() {
        return defaultPattern;
    }

    /**
     * Crea un nuevo builder para construir instancias de {@link DateConfig}.
     *
     * @return nuevo builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder fluido para construir instancias de {@link DateConfig}.
     */
    public static final class Builder {

        private Locale locale;
        private ZoneId zoneId;
        private String defaultPattern;

        /**
         * Configura el locale para formateo y parseo.
         *
         * @param locale locale a usar, o null para usar el del sistema
         * @return este builder
         */
        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        /**
         * Configura la zona horaria por defecto.
         *
         * @param zoneId zona horaria a usar, o null para usar la del sistema
         * @return este builder
         */
        public Builder zoneId(ZoneId zoneId) {
            this.zoneId = zoneId;
            return this;
        }

        /**
         * Configura el patrón de formato por defecto.
         *
         * @param pattern patrón de formato, o null para usar "yyyy-MM-dd"
         * @return este builder
         */
        public Builder defaultPattern(String pattern) {
            this.defaultPattern = pattern;
            return this;
        }

        /**
         * Construye una nueva instancia de {@link DateConfig}.
         *
         * @return nueva configuración inmutable
         */
        public DateConfig build() {
            return new DateConfig(this);
        }
    }
}
