package bff_movil.banco.dto.core;


import java.math.BigDecimal;
import java.util.List;

// Sin "edad" (no le sirve a una app de saldo/movimientos), y solo los últimos movimientos
public record CuentaMovilDetalleDTO(
        Long cuentaId,
        String nombre,
        BigDecimal saldo,
        String tipo,
        List<MovimientoMovilDTO> ultimosMovimientos
) {
}