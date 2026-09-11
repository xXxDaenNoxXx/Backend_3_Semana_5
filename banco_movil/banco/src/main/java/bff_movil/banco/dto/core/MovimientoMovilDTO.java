package bff_movil.banco.dto.core;

import java.math.BigDecimal;
import java.time.LocalDate;

// Sin "id" ni "descripcion" — la app móvil no los necesita para mostrar el historial
public record MovimientoMovilDTO(LocalDate fecha, String transaccion, BigDecimal monto) {
}