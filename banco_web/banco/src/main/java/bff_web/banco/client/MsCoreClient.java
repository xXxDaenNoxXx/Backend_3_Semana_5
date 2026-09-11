package bff_web.banco.client;

import bff_web.banco.dto.core.CuentaCoreDTO;
import bff_web.banco.dto.core.MovimientoCoreDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MsCoreClient {

    private final RestClient coreRestClient;

    public List<CuentaCoreDTO> obtenerCuentas() {
        return coreRestClient.get()
                .uri("/core/cuentas")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public CuentaCoreDTO obtenerCuenta(Long id) {
        return coreRestClient.get()
                .uri("/core/cuentas/{id}", id)
                .retrieve()
                .body(CuentaCoreDTO.class);
    }

    public List<MovimientoCoreDTO> obtenerMovimientos(Long id) {
        return coreRestClient.get()
                .uri("/core/cuentas/{id}/movimientos", id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}