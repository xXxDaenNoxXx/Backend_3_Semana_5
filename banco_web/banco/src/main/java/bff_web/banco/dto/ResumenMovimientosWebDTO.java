package bff_web.banco.dto;

import java.math.BigDecimal;

// Resumen calculado: valor agregado que solo tiene sentido en el canal Web
public record ResumenMovimientosWebDTO(
        long cantidadMovimientos,
        BigDecimal totalDepositos,
        BigDecimal totalRetiros,
        BigDecimal totalCompras,
        BigDecimal totalPagos
) {
}
