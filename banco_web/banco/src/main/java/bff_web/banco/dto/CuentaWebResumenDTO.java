package bff_web.banco.dto;

import java.math.BigDecimal;

// Para el listado: vista resumida
public record CuentaWebResumenDTO(Long cuentaId, String nombre, BigDecimal saldo, String tipo) {
}