package pe.edu.nova.java.libs.date.utils.convert;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import pe.edu.nova.java.libs.date.utils.exception.DateConversionException;

/**
 * Conversor entre tipos temporales de Java 8+ y {@link java.util.Date} legacy.
 * <p>
 * Todas las conversiones preservan el instante en el tiempo cuando aplica.
 * Thread-safe: todos los métodos son estáticos y stateless.
 * </p>
 *
 * @author Nova Platform
 */
public final class DateConverter {

    private DateConverter() {
    }

    // ========================================================================
    // Conversiones entre tipos modernos
    // ========================================================================

    /**
     * Convierte un {@link LocalDate} a {@link LocalDateTime} asignando hora 00:00:00.
     *
     * @param date fecha a convertir, o null para retornar null
     * @return fecha y hora al inicio del día, o null si date es null
     */
    public static LocalDateTime toLocalDateTime(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.atStartOfDay();
    }

    /**
     * Convierte un {@link LocalDateTime} a {@link LocalDate} descartando la hora.
     *
     * @param dateTime fecha y hora a convertir, o null para retornar null
     * @return solo la fecha, o null si dateTime es null
     */
    public static LocalDate toLocalDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toLocalDate();
    }

    /**
     * Convierte un {@link LocalDateTime} a {@link ZonedDateTime} con la zona especificada.
     *
     * @param dateTime fecha y hora a convertir, o null para retornar null
     * @param zoneId   zona horaria a asignar
     * @return fecha, hora y zona, o null si dateTime es null
     * @throws DateConversionException si zoneId es null
     */
    public static ZonedDateTime toZonedDateTime(LocalDateTime dateTime, ZoneId zoneId) {
        if (dateTime == null) {
            return null;
        }
        if (zoneId == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return dateTime.atZone(zoneId);
    }

    /**
     * Convierte un {@link ZonedDateTime} a {@link LocalDateTime} descartando la zona.
     *
     * @param dateTime fecha, hora y zona a convertir, o null para retornar null
     * @return fecha y hora sin zona, o null si dateTime es null
     */
    public static LocalDateTime toLocalDateTime(ZonedDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toLocalDateTime();
    }

    /**
     * Convierte un {@link ZonedDateTime} a {@link Instant} preservando el punto en el tiempo.
     *
     * @param dateTime fecha, hora y zona a convertir, o null para retornar null
     * @return instante equivalente, o null si dateTime es null
     */
    public static Instant toInstant(ZonedDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toInstant();
    }

    /**
     * Convierte un {@link Instant} a {@link ZonedDateTime} en la zona especificada.
     *
     * @param instant instante a convertir, o null para retornar null
     * @param zoneId  zona horaria destino
     * @return fecha, hora y zona, o null si instant es null
     * @throws DateConversionException si zoneId es null
     */
    public static ZonedDateTime toZonedDateTime(Instant instant, ZoneId zoneId) {
        if (instant == null) {
            return null;
        }
        if (zoneId == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return instant.atZone(zoneId);
    }

    /**
     * Convierte un {@link ZonedDateTime} a {@link OffsetDateTime} preservando el offset.
     *
     * @param dateTime fecha, hora y zona a convertir, o null para retornar null
     * @return fecha, hora y offset, o null si dateTime es null
     */
    public static OffsetDateTime toOffsetDateTime(ZonedDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toOffsetDateTime();
    }

    /**
     * Convierte un {@link OffsetDateTime} a {@link ZonedDateTime} usando el offset como zona.
     *
     * @param dateTime fecha, hora y offset a convertir, o null para retornar null
     * @return fecha, hora y zona, o null si dateTime es null
     */
    public static ZonedDateTime toZonedDateTime(OffsetDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toZonedDateTime();
    }

    // ========================================================================
    // Conversiones legacy ↔ moderno
    // ========================================================================

    /**
     * Convierte un {@link java.util.Date} a {@link Instant}.
     *
     * @param date fecha legacy a convertir, o null para retornar null
     * @return instante equivalente, o null si date es null
     */
    public static Instant toInstant(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant();
    }

    /**
     * Convierte un {@link java.util.Date} a {@link LocalDateTime} en la zona especificada.
     *
     * @param date   fecha legacy a convertir, o null para retornar null
     * @param zoneId zona horaria para la conversión
     * @return fecha y hora local, o null si date es null
     * @throws DateConversionException si zoneId es null
     */
    public static LocalDateTime toLocalDateTime(Date date, ZoneId zoneId) {
        if (date == null) {
            return null;
        }
        if (zoneId == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return date.toInstant().atZone(zoneId).toLocalDateTime();
    }

    /**
     * Convierte un {@link java.util.Date} a {@link ZonedDateTime} en la zona especificada.
     *
     * @param date   fecha legacy a convertir, o null para retornar null
     * @param zoneId zona horaria para la conversión
     * @return fecha, hora y zona, o null si date es null
     * @throws DateConversionException si zoneId es null
     */
    public static ZonedDateTime toZonedDateTime(Date date, ZoneId zoneId) {
        if (date == null) {
            return null;
        }
        if (zoneId == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return date.toInstant().atZone(zoneId);
    }

    /**
     * Convierte un {@link Instant} a {@link java.util.Date}.
     *
     * @param instant instante a convertir, o null para retornar null
     * @return fecha legacy equivalente, o null si instant es null
     */
    public static Date toDate(Instant instant) {
        if (instant == null) {
            return null;
        }
        return Date.from(instant);
    }

    /**
     * Convierte un {@link LocalDateTime} a {@link java.util.Date} usando la zona especificada.
     *
     * @param dateTime fecha y hora a convertir, o null para retornar null
     * @param zoneId   zona horaria para la conversión
     * @return fecha legacy equivalente, o null si dateTime es null
     * @throws DateConversionException si zoneId es null
     */
    public static Date toDate(LocalDateTime dateTime, ZoneId zoneId) {
        if (dateTime == null) {
            return null;
        }
        if (zoneId == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return Date.from(dateTime.atZone(zoneId).toInstant());
    }

    // ========================================================================
    // Conversión de zona horaria
    // ========================================================================

    /**
     * Convierte un {@link ZonedDateTime} a otra zona horaria preservando el instante.
     *
     * @param dateTime   fecha, hora y zona a convertir, o null para retornar null
     * @param targetZone zona horaria destino
     * @return fecha, hora y zona en la zona destino, o null si dateTime es null
     * @throws DateConversionException si targetZone es null
     */
    public static ZonedDateTime convertZone(ZonedDateTime dateTime, ZoneId targetZone) {
        if (dateTime == null) {
            return null;
        }
        if (targetZone == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return dateTime.withZoneSameInstant(targetZone);
    }

    /**
     * Convierte un {@link OffsetDateTime} a otra zona horaria preservando el instante.
     *
     * @param dateTime   fecha, hora y offset a convertir, o null para retornar null
     * @param targetZone zona horaria destino
     * @return fecha, hora y zona en la zona destino, o null si dateTime es null
     * @throws DateConversionException si targetZone es null
     */
    public static ZonedDateTime convertZone(OffsetDateTime dateTime, ZoneId targetZone) {
        if (dateTime == null) {
            return null;
        }
        if (targetZone == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return dateTime.atZoneSameInstant(targetZone);
    }

    /**
     * Convierte un {@link LocalDateTime} de una zona origen a una zona destino.
     *
     * @param dateTime   fecha y hora a convertir, o null para retornar null
     * @param sourceZone zona horaria origen
     * @param targetZone zona horaria destino
     * @return fecha, hora y zona en la zona destino, o null si dateTime es null
     * @throws DateConversionException si alguna zona es null
     */
    public static ZonedDateTime convertZone(LocalDateTime dateTime, ZoneId sourceZone, ZoneId targetZone) {
        if (dateTime == null) {
            return null;
        }
        if (sourceZone == null || targetZone == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return dateTime.atZone(sourceZone).withZoneSameInstant(targetZone);
    }

    // ========================================================================
    // Conversión UTC
    // ========================================================================

    /**
     * Convierte una hora local a {@link Instant} UTC.
     *
     * @param dateTime  fecha y hora local, o null para retornar null
     * @param localZone zona horaria local
     * @return instante UTC equivalente, o null si dateTime es null
     * @throws DateConversionException si localZone es null
     */
    public static Instant toUtc(LocalDateTime dateTime, ZoneId localZone) {
        if (dateTime == null) {
            return null;
        }
        if (localZone == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return dateTime.atZone(localZone).toInstant();
    }

    /**
     * Convierte un {@link Instant} UTC a {@link LocalDateTime} en la zona local.
     *
     * @param utcInstant instante UTC, o null para retornar null
     * @param localZone  zona horaria local
     * @return fecha y hora local, o null si utcInstant es null
     * @throws DateConversionException si localZone es null
     */
    public static LocalDateTime fromUtc(Instant utcInstant, ZoneId localZone) {
        if (utcInstant == null) {
            return null;
        }
        if (localZone == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        return LocalDateTime.ofInstant(utcInstant, localZone);
    }

    /**
     * Convierte un {@link ZonedDateTime} a {@link Instant} UTC.
     *
     * @param dateTime fecha, hora y zona a convertir, o null para retornar null
     * @return instante UTC equivalente, o null si dateTime es null
     */
    public static Instant toUtc(ZonedDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toInstant();
    }

    // ========================================================================
    // Diferencia horaria
    // ========================================================================

    /**
     * Calcula la diferencia en horas entre dos zonas horarias en el momento actual.
     *
     * @param zone1 primera zona horaria
     * @param zone2 segunda zona horaria
     * @return diferencia en horas (zone2 - zone1)
     * @throws DateConversionException si alguna zona es null
     */
    public static long hoursDifference(ZoneId zone1, ZoneId zone2) {
        return hoursDifference(zone1, zone2, Instant.now());
    }

    /**
     * Calcula la diferencia en horas entre dos zonas horarias en un instante específico.
     *
     * @param zone1     primera zona horaria
     * @param zone2     segunda zona horaria
     * @param atInstant instante en el que calcular la diferencia
     * @return diferencia en horas (zone2 - zone1)
     * @throws DateConversionException si algún parámetro es null
     */
    public static long hoursDifference(ZoneId zone1, ZoneId zone2, Instant atInstant) {
        if (zone1 == null || zone2 == null) {
            throw new DateConversionException("La zona horaria es obligatoria para esta conversión");
        }
        if (atInstant == null) {
            throw new DateConversionException("El instante es obligatorio para calcular la diferencia horaria");
        }
        ZoneOffset offset1 = zone1.getRules().getOffset(atInstant);
        ZoneOffset offset2 = zone2.getRules().getOffset(atInstant);
        int totalSeconds1 = offset1.getTotalSeconds();
        int totalSeconds2 = offset2.getTotalSeconds();
        return (totalSeconds2 - totalSeconds1) / 3600;
    }
}
