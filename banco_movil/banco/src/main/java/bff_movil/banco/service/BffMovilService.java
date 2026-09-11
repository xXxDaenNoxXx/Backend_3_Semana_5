package bff_movil.banco.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import bff_movil.banco.client.MsCoreClient;
import bff_movil.banco.dto.core.CuentaCoreDTO;
import bff_movil.banco.dto.core.CuentaMovilDetalleDTO;
import bff_movil.banco.dto.core.CuentaMovilResumenDTO;
import bff_movil.banco.dto.core.MovimientoCoreDTO;
import bff_movil.banco.dto.core.MovimientoMovilDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BffMovilService {

    private static final int MAX_MOVIMIENTOS = 3;

    private final MsCoreClient msCoreClient;

    public List<CuentaMovilResumenDTO> listarCuentas() {
        return msCoreClient.obtenerCuentas().stream()
                .map(c -> new CuentaMovilResumenDTO(c.cuentaId(), c.nombre(), c.saldo()))
                .toList();
    }

    public CuentaMovilDetalleDTO obtenerDetalle(Long id) {
        CuentaCoreDTO cuenta = msCoreClient.obtenerCuenta(id);
        if (cuenta == null) return null;

        List<MovimientoMovilDTO> ultimos = msCoreClient.obtenerMovimientos(id).stream()
                .sorted(Comparator.comparing(MovimientoCoreDTO::fecha).reversed())
                .limit(MAX_MOVIMIENTOS)
                .map(m -> new MovimientoMovilDTO(m.fecha(), m.transaccion(), m.monto()))
                .toList();

        return new CuentaMovilDetalleDTO(cuenta.cuentaId(), cuenta.nombre(), cuenta.saldo(), cuenta.tipo(), ultimos);
    }
}