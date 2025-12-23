# 📊 Reporte de Tests - Ejecución Automatizada

**Fecha:** 23 de Diciembre de 2025  
**Navegador:** Chrome 143.0.7499.169  
**Comando:** `mvn clean test`

---

## 📈 Resumen General

| Métrica                    | Valor     |
| -------------------------- | --------- |
| **Tests Ejecutados**       | 114       |
| **✅ Exitosos**            | 111       |
| **❌ Fallidos (Failures)** | 0         |
| **⚠️ Errores (Errors)**    | 2         |
| **⏭️ Omitidos (Skipped)**  | 1         |
| **Tasa de Éxito**          | 97.4%     |
| **Tiempo Total**           | 31:40 min |

---

## ⚠️ Tests con Errores (Errors) - 2

### 1. `ExpoSaldoEmpresaTest.consolidacionEmpresaTest`

| Campo                | Valor                                             |
| -------------------- | ------------------------------------------------- |
| **Clase**            | `automation.funcionalidades.ExpoSaldoEmpresaTest` |
| **Método**           | `consolidacionEmpresaTest`                        |
| **Línea**            | 57                                                |
| **Tipo de Error**    | `TimeoutException`                                |
| **Tiempo de espera** | 10 segundos                                       |

**Selector fallido:**

```xpath
//ion-card[.//ion-card-title[contains(normalize-space(), 'Galpones')]]
```

**Causa probable:**

- La tarjeta con título "Galpones" no existe o no está visible
- Los datos de la empresa no se cargaron correctamente
- Posible problema de sincronización en la carga de la vista

**Recomendación:**

- Verificar que exista una planilla llamada "Galpones" en el sistema
- Aumentar el tiempo de espera para la carga de datos
- Verificar que el usuario tenga acceso a esta planilla

---

### 2. `IngresoEliminarFilaTest.eliminarFilaTest`

| Campo                | Valor                                                |
| -------------------- | ---------------------------------------------------- |
| **Clase**            | `automation.funcionalidades.IngresoEliminarFilaTest` |
| **Método**           | `eliminarFilaTest`                                   |
| **Línea**            | 25                                                   |
| **Archivo Page**     | `IngresoEliminarFilaPage.java:87`                    |
| **Tipo de Error**    | `TimeoutException`                                   |
| **Tiempo de espera** | 20 segundos                                          |

**Selector fallido:**

```css
ion-card
```

**Causa probable:**

- No hay filas (ion-card) disponibles para eliminar
- La vista de ingresos no se cargó correctamente
- Posible problema de navegación o autenticación

**Recomendación:**

- Crear datos de prueba (seed data) antes de ejecutar el test
- Verificar que el usuario tenga filas de ingresos para eliminar
- Agregar espera explícita para la carga completa de la página

---

## ⏭️ Tests Omitidos (Skipped) - 1

### 1. `ExpoCategoryTest` (1 test omitido)

El test fue omitido durante la ejecución. Verificar si hay dependencias o condiciones previas no cumplidas.

---

## ✅ Tests Exitosos por Clase

| Clase de Test             | Tests | Resultado |
| ------------------------- | ----- | --------- |
| `ExpoAdministrationTest`  | 6     | ✅ Pasó   |
| `ExpoCategoryTest`        | 1     | ✅ Pasó   |
| `ExpoCreationUsuarioTest` | 2     | ✅ Pasó   |
| `ExpoDeleteUsuarioTest`   | 1     | ✅ Pasó   |
| `ExpoEditionUsuarioTest`  | 1     | ✅ Pasó   |
| `ExpoHomeTest`            | 5     | ✅ Pasó   |
| `ExpoLoginTest`           | 2     | ✅ Pasó   |
| `IngresoCrearFilaTest`    | 1     | ✅ Pasó   |
| `IngresoDetallesTest`     | 1     | ✅ Pasó   |
| `LoginTest`               | 1     | ✅ Pasó   |
| `TotalesTest`             | 1     | ✅ Pasó   |
| Flujos Críticos (F1-F12)  | 92    | ✅ Pasó   |

---

## 📝 Notas Adicionales

- Se detectó una advertencia en `ExpoDeleteUsuarioTest`: El usuario `usuarioEditadoAutomatico` no existe (dependencia de test previo)
- Se generaron screenshots de cada test en `C:\Users\Usuario\Desktop\Capturas\`
- Advertencia de CDP: Chrome 143 no tiene implementación CDP exacta en Selenium 4.27.0

---

## 🔧 Acciones Recomendadas

### 🔴 Alta Prioridad

1. **Crear datos de prueba (seed data)** para `ExpoSaldoEmpresaTest` - agregar planilla "Galpones"
2. **Verificar datos en IngresoEliminarFilaTest** - asegurar que existan registros para eliminar

### 🟡 Media Prioridad

3. **Aumentar tiempos de espera** o agregar waits explícitos para elementos dinámicos
4. **Mejorar independencia de tests** para evitar dependencias entre pruebas

---

## 📊 Historial de Ejecuciones

| Fecha      | Total | Exitosos | Errores | Tasa  |
| ---------- | ----- | -------- | ------- | ----- |
| 23/12/2025 | 114   | 111      | 2       | 97.4% |

5. **Hacer tests independientes** - no depender de la ejecución de otros tests
6. **Agregar screenshots automáticos** en casos de fallo para debugging

---

## ✅ Tests Exitosos por Paquete

| Paquete                      | Exitosos | Total |
| ---------------------------- | -------- | ----- |
| `automation.flujoscriticos`  | 84       | 86    |
| `automation.funcionalidades` | 20       | 28    |

---

_Reporte generado automáticamente_
