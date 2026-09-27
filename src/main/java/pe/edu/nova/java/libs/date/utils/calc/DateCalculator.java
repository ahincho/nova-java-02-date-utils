package pe.edu.nova.java.libs.date.utils.calc;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import pe.edu.nova.java.libs.date.utils.exception.DateException;

/**
 * Calculadora de operaciones temporales: diferencias, sumas, rangos y días hábiles.
 * <p>
 * Clase final utilitaria con constructor privado. Todos los métodos son estáticos
 * y stateless (thread-safe).
 * </p>
 *
 * @author Nova Platform
 */
public final class DateCalculator {

    private DateCalculator() {
    }

    // ========================================================================
    // Diferencia entre fechas
    // ========================================================================

    /**
     * Calcula la diferencia en días entre dos fechas.
     *
     * @param start fecha de inicio
     * @param end   fecha de fin
     * @return diferencia en días (negativo si start es posterior a end)
     * @throws DateException si alguna fecha es null
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        validateDates(start, end);
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Calcula la diferencia en meses completos entre dos fechas.
     *
     * @param start fecha de inicio
     * @param end   fecha de fin
     * @return diferencia en meses completos (negativo si start es posterior a end)
     * @throws DateException si alguna fecha es null
     */
    public static long monthsBetween(LocalDate start, LocalDate end) {
        validateDates(start, end);
        return ChronoUnit.MONTHS.between(start, end);
    }

    /**
     * Calcula la diferencia en años completos entre dos fechas.
     *
     * @param start fecha de inicio
     * @param end   fecha de fin
     * @return diferencia en años completos (negativo si start es posterior a end)
     * @throws DateException si alguna fecha es null
     */
    public static long yearsBetween(LocalDate start, LocalDate end) {
        validateDates(start, end);
        return ChronoUnit.YEARS.between(start, end);
    }

    /**
     * Calcula la diferencia en horas entre dos fechas y horas.
     *
     * @param start fecha y hora de inicio
     * @param end   fecha y hora de fin
     * @return diferencia en horas (negativo si start es posterior a end)
     * @throws DateException si alguna fecha es null
     */
    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        validateDateTimes(start, end);
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * Calcula la diferencia en minutos entre dos fechas y horas.
     *
     * @param start fecha y hora de inicio
     * @param end   fecha y hora de fin
     * @return diferencia en minutos (negativo si start es posterior a end)
     * @throws DateException si alguna fecha es null
     */
    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        validateDateTimes(start, end);
        return ChronoUnit.MINUTES.between(start, end);
    }

    /**
     * Calcula la diferencia en segundos entre dos fechas y horas.
     *
     * @param start fecha y hora de inicio
     * @param end   fecha y hora de fin
     * @return diferencia en segundos (negativo si start es posterior a end)
     * @throws DateException si alguna fecha es null
     */
    public static long secondsBetween(LocalDateTime start, LocalDateTime end) {
        validateDateTimes(start, end);
        return ChronoUnit.SECONDS.between(start, end);
    }

    // ========================================================================
    // Suma y resta de períodos
    // ========================================================================

    /**
     * Suma o resta días a una fecha.
     *
     * @param date fecha base
     * @param days número de días a sumar (positivo) o restar (negativo)
     * @return nueva fecha con los días sumados o restados
     * @throws DateException si date es null
     */
    public static LocalDate addDays(LocalDate date, long days) {
        validateDate(date);
        return date.plusDays(days);
    }

    /**
     * Suma o resta semanas a una fecha.
     *
     * @param date  fecha base
     * @param weeks número de semanas a sumar (positivo) o restar (negativo)
     * @return nueva fecha con las semanas sumadas o restadas
     * @throws DateException si date es null
     */
    public static LocalDate addWeeks(LocalDate date, long weeks) {
        validateDate(date);
        return date.plusWeeks(weeks);
    }

    /**
     * Suma o resta meses a una fecha, ajustando el día si el mes destino tiene menos días.
     *
     * @param date   fecha base
     * @param months número de meses a sumar (positivo) o restar (negativo)
     * @return nueva fecha con los meses sumados o restados
     * @throws DateException si date es null
     */
    public static LocalDate addMonths(LocalDate date, long months) {
        validateDate(date);
        return date.plusMonths(months);
    }

    /**
     * Suma o resta años a una fecha.
     *
     * @param date  fecha base
     * @param years número de años a sumar (positivo) o restar (negativo)
     * @return nueva fecha con los años sumados o restados
     * @throws DateException si date es null
     */
    public static LocalDate addYears(LocalDate date, long years) {
        validateDate(date);
        return date.plusYears(years);
    }

    /**
     * Suma o resta horas a una fecha y hora.
     *
     * @param dateTime fecha y hora base
     * @param hours    número de horas a sumar (positivo) o restar (negativo)
     * @return nueva fecha y hora con las horas sumadas o restadas
     * @throws DateException si dateTime es null
     */
    public static LocalDateTime addHours(LocalDateTime dateTime, long hours) {
        validateDateTime(dateTime);
        return dateTime.plusHours(hours);
    }

    /**
     * Suma o resta minutos a una fecha y hora.
     *
     * @param dateTime fecha y hora base
     * @param minutes  número de minutos a sumar (positivo) o restar (negativo)
     * @return nueva fecha y hora con los minutos sumados o restados
     * @throws DateException si dateTime es null
     */
    public static LocalDateTime addMinutes(LocalDateTime dateTime, long minutes) {
        validateDateTime(dateTime);
        return dateTime.plusMinutes(minutes);
    }

    // ========================================================================
    // Rangos
    // ========================================================================

    /**
     * Verifica si una fecha está dentro de un rango (inclusive).
     *
     * @param date  fecha a verificar
     * @param range rango de fechas
     * @return true si la fecha está dentro del rango
     * @throws DateException si date o range es null
     */
    public static boolean isWithinRange(LocalDate date, DateRange range) {
        if (date == null || range == null) {
            throw new DateException("Las fechas de entrada son obligatorias para calcular la diferencia");
        }
        return range.contains(date);
    }

    /**
     * Verifica si una fecha y hora está dentro de un rango (inclusive).
     *
     * @param dateTime   fecha y hora a verificar
     * @param rangeStart inicio del rango
     * @param rangeEnd   fin del rango
     * @return true si la fecha y hora está dentro del rango
     * @throws DateException si algún parámetro es null
     */
    public static boolean isWithinRange(LocalDateTime dateTime, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        if (dateTime == null || rangeStart == null || rangeEnd == null) {
            throw new DateException("Las fechas de entrada son obligatorias para calcular la diferencia");
        }
        return !dateTime.isBefore(rangeStart) && !dateTime.isAfter(rangeEnd);
    }

    /**
     * Verifica si dos rangos se solapan.
     *
     * @param range1 primer rango
     * @param range2 segundo rango
     * @return true si los rangos se solapan
     * @throws DateException si algún rango es null
     */
    public static boolean overlaps(DateRange range1, DateRange range2) {
        if (range1 == null || range2 == null) {
            throw new DateException("Las fechas de entrada son obligatorias para calcular la diferencia");
        }
        return range1.overlaps(range2);
    }

    // ========================================================================
    // Días hábiles
    // ========================================================================

    /**
     * Avanza N días hábiles desde una fecha, saltando sábados y domingos.
     * <p>
     * Si la fecha base cae en fin de semana, comienza desde el siguiente día hábil.
     * </p>
     *
     * @param date         fecha base
     * @param businessDays número de días hábiles a avanzar
     * @return fecha resultante después de avanzar los días hábiles
     * @throws DateException si date es null
     */
    public static LocalDate addBusinessDays(LocalDate date, int businessDays) {
        validateDate(date);
        LocalDate result = date;

        // Si la fecha base cae en fin de semana, avanzar al siguiente día hábil
        result = moveToNextBusinessDay(result);

        int daysAdded = 0;
        int direction = businessDays >= 0 ? 1 : -1;
        int absDays = Math.abs(businessDays);

        while (daysAdded < absDays) {
            result = result.plusDays(direction);
            if (isBusinessDay(result)) {
                daysAdded++;
            }
        }
        return result;
    }

    /**
     * Cuenta los días hábiles entre dos fechas (excluyendo sábados y domingos).
     *
     * @param start fecha de inicio
     * @param end   fecha de fin
     * @return número de días hábiles entre las fechas
     * @throws DateException si alguna fecha es null
     */
    public static long businessDaysBetween(LocalDate start, LocalDate end) {
        validateDates(start, end);
        long count = 0;
        LocalDate current = start;
        boolean forward = !start.isAfter(end);

        if (forward) {
            current = current.plusDays(1);
            while (!current.isAfter(end)) {
                if (isBusinessDay(current)) {
                    count++;
                }
                current = current.plusDays(1);
            }
        } else {
            current = current.minusDays(1);
            while (!current.isBefore(end)) {
                if (isBusinessDay(current)) {
                    count--;
                }
                current = current.minusDays(1);
            }
        }
        return count;
    }

    // ========================================================================
    // Inicio y fin de períodos
    // ========================================================================

    /**
     * Retorna el inicio del día (00:00:00) para la fecha dada.
     *
     * @param date fecha
     * @return fecha y hora al inicio del día
     * @throws DateException si date es null
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        validateDateForPeriod(date);
        return date.atStartOfDay();
    }

    /**
     * Retorna el fin del día (23:59:59.999999999) para la fecha dada.
     *
     * @param date fecha
     * @return fecha y hora al fin del día
     * @throws DateException si date es null
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        validateDateForPeriod(date);
        return date.atTime(LocalTime.MAX);
    }

    /**
     * Retorna el lunes de la semana que contiene la fecha.
     *
     * @param date fecha
     * @return lunes de la semana
     * @throws DateException si date es null
     */
    public static LocalDate startOfWeek(LocalDate date) {
        validateDateForPeriod(date);
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    /**
     * Retorna el domingo de la semana que contiene la fecha.
     *
     * @param date fecha
     * @return domingo de la semana
     * @throws DateException si date es null
     */
    public static LocalDate endOfWeek(LocalDate date) {
        validateDateForPeriod(date);
        return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

    /**
     * Retorna el primer día del mes.
     *
     * @param date fecha
     * @return primer día del mes
     * @throws DateException si date es null
     */
    public static LocalDate startOfMonth(LocalDate date) {
        validateDateForPeriod(date);
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * Retorna el último día del mes.
     *
     * @param date fecha
     * @return último día del mes
     * @throws DateException si date es null
     */
    public static LocalDate endOfMonth(LocalDate date) {
        validateDateForPeriod(date);
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * Retorna el 1 de enero del año.
     *
     * @param date fecha
     * @return primer día del año
     * @throws DateException si date es null
     */
    public static LocalDate startOfYear(LocalDate date) {
        validateDateForPeriod(date);
        return date.with(TemporalAdjusters.firstDayOfYear());
    }

    /**
     * Retorna el 31 de diciembre del año.
     *
     * @param date fecha
     * @return último día del año
     * @throws DateException si date es null
     */
    public static LocalDate endOfYear(LocalDate date) {
        validateDateForPeriod(date);
        return date.with(TemporalAdjusters.lastDayOfYear());
    }

    // ========================================================================
    // Métodos internos de validación
    // ========================================================================

    private static void validateDates(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            throw new DateException("Las fechas de entrada son obligatorias para calcular la diferencia");
        }
    }

    private static void validateDateTimes(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new DateException("Las fechas de entrada son obligatorias para calcular la diferencia");
        }
    }

    private static void validateDate(LocalDate date) {
        if (date == null) {
            throw new DateException("La fecha base es obligatoria");
        }
    }

    private static void validateDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new DateException("La fecha base es obligatoria");
        }
    }

    private static void validateDateForPeriod(LocalDate date) {
        if (date == null) {
            throw new DateException("La fecha es obligatoria para calcular el inicio/fin del período");
        }
    }

    private static boolean isBusinessDay(LocalDate date) {
        DayOfWeek dow = date.getDayOfWeek();
        return dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY;
    }

    private static LocalDate moveToNextBusinessDay(LocalDate date) {
        while (!isBusinessDay(date)) {
            date = date.plusDays(1);
        }
        return date;
    }
}
