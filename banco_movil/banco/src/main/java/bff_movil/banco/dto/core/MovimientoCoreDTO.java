package bff_movil.banco.dto.core;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimientoCoreDTO(Long id, Long cuentaId, LocalDate fecha, String transaccion, BigDecimal monto, String descripcion) {
}