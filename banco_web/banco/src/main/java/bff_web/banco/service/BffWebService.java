package bff_web.banco.service;

import bff_web.banco.client.MsCoreClient;
import bff_web.banco.dto.*;
import bff_web.banco.dto.core.CuentaCoreDTO;
import bff_web.banco.dto.core.MovimientoCoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BffWebService {

    private final MsCoreClient msCoreClient;

    public List<CuentaWebResumenDTO> listarCuentas() {
        return msCoreClient.obtenerCuentas().stream()
                .map(c -> new CuentaWebResumenDTO(c.cuentaId(), c.nombre(), c.saldo(), c.tipo()))
                .toList();
    }

    public CuentaWebDetalleDTO obtenerDetalle(Long id) {
        CuentaCoreDTO cuenta = msCoreClient.obtenerCuenta(id);
        if (cuenta == null) return null;

        List<MovimientoCoreDTO> movimientos = msCoreClient.obtenerMovimientos(id);

        List<MovimientoWebDTO> movimientosWeb = movimientos.stream()
                .map(m -> new MovimientoWebDTO(m.id(), m.fecha(), m.transaccion(), m.monto(), m.descripcion()))
                .toList();

        return new CuentaWebDetalleDTO(
                cuenta.cuentaId(), cuenta.nombre(), cuenta.saldo(), cuenta.edad(), cuenta.tipo(),
                movimientosWeb, calcularResumen(movimientos)
        );
    }

    private ResumenMovimientosWebDTO calcularResumen(List<MovimientoCoreDTO> movimientos) {
        return new ResumenMovimientosWebDTO(
                movimientos.size(),
                sumarPorTipo(movimientos, "deposito"),
                sumarPorTipo(movimientos, "retiro"),
                sumarPorTipo(movimientos, "compra"),
                sumarPorTipo(movimientos, "pago")
        );
    }

    private BigDecimal sumarPorTipo(List<MovimientoCoreDTO> movimientos, String tipo) {
        return movimientos.stream()
                .filter(m -> tipo.equals(m.transaccion()))
                .map(MovimientoCoreDTO::monto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}