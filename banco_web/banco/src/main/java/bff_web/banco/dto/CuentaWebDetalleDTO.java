package bff_web.banco.dto;

import java.math.BigDecimal;
import java.util.List;

// Para el detalle: datos completos + movimientos + resumen (esto es lo que hace "rico" al BFF Web)
public record CuentaWebDetalleDTO(
        Long cuentaId,
        String nombre,
        BigDecimal saldo,
        Integer edad,
        String tipo,
        List<MovimientoWebDTO> movimientos,
        ResumenMovimientosWebDTO resumen
) {
}