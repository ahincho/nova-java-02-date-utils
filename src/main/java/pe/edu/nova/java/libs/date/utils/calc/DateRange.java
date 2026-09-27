package pe.edu.nova.java.libs.date.utils.calc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import pe.edu.nova.java.libs.date.utils.exception.DateException;

/**
 * Rango de fechas inmutable con validación de orden.
 * <p>
 * La fecha de inicio debe ser anterior o igual a la fecha de fin.
 * Ambos extremos son inclusivos.
 * </p>
 *
 * @param start fecha de inicio del rango (inclusive)
 * @param end   fecha de fin del rango (inclusive)
 * @author Nova Platform
 */
public record DateRange(LocalDate start, LocalDate end) {

    /**
     * Constructor compacto con validación.
     *
     * @param start fecha de inicio del rango (inclusive)
     * @param end   fecha de fin del rango (inclusive)
     * @throws DateException si start o end son null, o si start es posterior a end
     */
    public DateRange {
        if (start == null || end == null) {
            throw new DateException("Las fechas de inicio y fin del rango son obligatorias");
        }
        if (start.isAfter(end)) {
            throw new DateException(
                    "La fecha de inicio (%s) debe ser anterior o igual a la fecha de fin (%s)"
                            .formatted(start, end));
        }
    }

    /**
     * Verifica si una fecha está dentro del rango (inclusive).
     *
     * @param date fecha a verificar
     * @return true si la fecha está dentro del rango
     */
    public boolean contains(LocalDate date) {
        if (date == null) {
            return false;
        }
        return !date.isBefore(start) && !date.isAfter(end);
    }

    /**
     * Verifica si este rango se solapa con otro.
     *
     * @param other otro rango a verificar
     * @return true si los rangos se solapan
     */
    public boolean overlaps(DateRange other) {
        if (other == null) {
            return false;
        }
        return !this.start.isAfter(other.end) && !other.start.isAfter(this.end);
    }

    /**
     * Retorna la cantidad de días en el rango (inclusive).
     *
     * @return número de días entre start y end, inclusive
     */
    public long days() {
        return ChronoUnit.DAYS.between(start, end) + 1;
    }
}
