# Banco XYZ — Patrón Backend for Frontend (BFF)
PBY2203 - Desarrollo Backend III - Exp2 Semana 5 (Sumativa Individual)
Daniel Erices

## Objetivo
Implementar el patrón arquitectónico BFF para exponer la información de cuentas
del Banco XYZ a través de tres canales con necesidades distintas: Web, Móvil y
Cajeros Automáticos, cada uno con su propia transformación de datos y su propio
mecanismo de autenticación/autorización.

## Estrategia elegida: Backends independientes por cliente
Se optó por esta estrategia porque los tres canales presentan requisitos de
información y de seguridad claramente diferenciados (ver justificación completa
en `docs/analisis-estrategia.md` o en el punto 1 de este README).
[Aquí pega el párrafo de justificación de la Parte 1]

## Estructura del proyecto
- `ms-core-banco/` — backend legacy central (puerto 8090, HTTP). Carga
  `intereses.csv` y `cuentas_anuales.csv`, expone los datos sin personalizar.
- `bff-web/` — BFF canal Web (puerto 8091, HTTPS, Basic Auth rol WEB).
- `bff-movil/` — BFF canal Móvil (puerto 8092, HTTPS, API Key).
- `bff-cajeros/` — BFF canal Cajeros (puerto 8094, HTTPS, Basic Auth rol CAJERO).

## Cómo ejecutar
1. Levantar `ms-core-banco` primero (los 3 BFF dependen de él).
2. Levantar `bff-web`, `bff-movil` y `bff-cajeros` (en cualquier orden).

## Endpoints y credenciales

### ms-core-banco (http://localhost:8090)
- GET /core/cuentas
- GET /core/cuentas/{id}
- GET /core/cuentas/{id}/movimientos
- POST /core/cuentas/{id}/retiro

### bff-web (https://localhost:8091) — Basic Auth: web-client / web123
- GET /web/cuentas
- GET /web/cuentas/{id}

### bff-movil (https://localhost:8092) — Header: X-API-KEY: movil-secreto-2026
- GET /movil/cuentas
- GET /movil/cuentas/{id}

### bff-cajeros (https://localhost:8094) — Basic Auth: cajero-client / cajero123
- GET /cajero/cuentas/{id}/saldo
- POST /cajero/cuentas/{id}/retiro   body: {"monto": 1000}

## Seguridad
| Canal | Mecanismo | Justificación |
|---|---|---|
| Web | Basic Auth (rol WEB) | Canal de escritorio, sesión de usuario habitual |
| Móvil | API Key (header) | Liviano, sin overhead de sesión |
| Cajeros | Basic Auth (rol CAJERO), sin rutas públicas | Máxima restricción por operar retiros de dinero |

Todos los BFF exponen HTTPS con certificado autofirmado (uso académico/local).

## Evidencia de ejecución
Ver carpeta `/evidencia` con capturas de consola y de las pruebas de cada endpoint.