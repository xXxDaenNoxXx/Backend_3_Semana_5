package bff_movil.banco.dto.core;

import java.math.BigDecimal;

// Vista mínima para el listado: ni siquiera el tipo de cuenta
public record CuentaMovilResumenDTO(Long cuentaId, String nombre, BigDecimal saldo) {
}