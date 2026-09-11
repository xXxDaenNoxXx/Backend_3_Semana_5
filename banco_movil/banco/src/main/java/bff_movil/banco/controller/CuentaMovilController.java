package bff_movil.banco.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bff_movil.banco.dto.core.CuentaMovilDetalleDTO;
import bff_movil.banco.dto.core.CuentaMovilResumenDTO;
import bff_movil.banco.service.BffMovilService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movil/cuentas")
@RequiredArgsConstructor
public class CuentaMovilController {

    private final BffMovilService bffMovilService;

    @GetMapping
    public List<CuentaMovilResumenDTO> listar() {
        return bffMovilService.listarCuentas();
    }

    @GetMapping("/{id}")
    public CuentaMovilDetalleDTO detalle(@PathVariable Long id) {
        return bffMovilService.obtenerDetalle(id);
    }
}