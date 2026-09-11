package bff_web.banco.controller;

import bff_web.banco.dto.CuentaWebDetalleDTO;
import bff_web.banco.dto.CuentaWebResumenDTO;
import bff_web.banco.service.BffWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/web/cuentas")
@RequiredArgsConstructor
public class CuentaWebController {

    private final BffWebService bffWebService;

    @GetMapping
    public List<CuentaWebResumenDTO> listar() {
        return bffWebService.listarCuentas();
    }

    @GetMapping("/{id}")
    public CuentaWebDetalleDTO detalle(@PathVariable Long id) {
        return bffWebService.obtenerDetalle(id);
    }
}