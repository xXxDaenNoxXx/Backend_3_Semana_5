package bff_cajeros.banco.client;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import bff_cajeros.banco.dto.core.CuentaCoreDTO;
import bff_cajeros.banco.dto.core.RetiroRequestCoreDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MsCoreClient {

    private final RestClient coreRestClient;

    public CuentaCoreDTO obtenerCuenta(Long id) {
        return coreRestClient.get()
                .uri("/core/cuentas/{id}", id)
                .retrieve()
                .body(CuentaCoreDTO.class);
    }

    public CuentaCoreDTO retirar(Long id, BigDecimal monto) {
        return coreRestClient.post()
                .uri("/core/cuentas/{id}/retiro", id)
                .body(new RetiroRequestCoreDTO(monto))
                .retrieve()
                .body(CuentaCoreDTO.class);
    }
}