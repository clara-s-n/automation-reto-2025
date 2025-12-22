# 📊 Reporte de Tests - Ejecución en Modo Headless

**Fecha:** 22 de Diciembre de 2025  
**Navegador:** Chrome 143.0.7499.169 (Headless)  
**Comando:** `mvn test -Dbrowser=chrome -Dheadless=true`

---

## 📈 Resumen General

| Métrica                    | Valor     |
| -------------------------- | --------- |
| **Tests Ejecutados**       | 114       |
| **✅ Exitosos**            | 104       |
| **❌ Fallidos (Failures)** | 2         |
| **⚠️ Errores (Errors)**    | 7         |
| **⏭️ Omitidos (Skipped)**  | 1         |
| **Tasa de Éxito**          | 91.2%     |
| **Tiempo Total**           | 20:15 min |

---

## ❌ Tests Fallidos (Failures) - 2

### 1. `F1_GestionPlanillaIngresosTest.flujoCompletoPlanillaIngresos`

| Campo             | Valor                                                      |
| ----------------- | ---------------------------------------------------------- |
| **Clase**         | `automation.flujoscriticos.F1_GestionPlanillaIngresosTest` |
| **Método**        | `flujoCompletoPlanillaIngresos`                            |
| **Línea**         | 57                                                         |
| **Tipo de Error** | AssertionError                                             |
| **Mensaje**       | "Debería haber al menos una planilla de ingresos"          |

**Causa probable:**

- No hay planillas de ingresos en la base de datos de prueba
- El selector no encuentra los elementos `ion-card` esperados
- Posible problema de sincronización/carga de datos

**Recomendación:**

- Crear datos de prueba (seed data) antes de ejecutar el test
- Aumentar el tiempo de espera para la carga de planillas
- Verificar que el usuario de prueba tenga planillas asignadas

---

### 2. `F6_GestionAniosFiscalesTest.verificarSelectorAnioActivo`

| Campo             | Valor                                                             |
| ----------------- | ----------------------------------------------------------------- |
| **Clase**         | `automation.flujoscriticos.F6_GestionAniosFiscalesTest`           |
| **Método**        | `verificarSelectorAnioActivo`                                     |
| **Línea**         | 169                                                               |
| **Tipo de Error** | AssertionError                                                    |
| **Mensaje**       | "Debería mostrar el año activo, selector de año, o datos del año" |

**Causa probable:**

- El selector de año no está visible en la interfaz
- El año fiscal activo no está configurado en el sistema
- El elemento UI para seleccionar año tiene un selector diferente

**Recomendación:**

- Verificar que exista al menos un año fiscal configurado
- Revisar el selector CSS/XPath para el componente de año
- Agregar espera explícita para la carga del selector

---

## ⚠️ Tests con Errores (Errors) - 7

### 1. `EgresosEditarEmpresaTest.editarCITest`

| Campo                | Valor                                                 |
| -------------------- | ----------------------------------------------------- |
| **Clase**            | `automation.funcionalidades.EgresosEditarEmpresaTest` |
| **Archivo Page**     | `EgresosEditarEmpresaPage.java:158`                   |
| **Tipo de Error**    | `TimeoutException`                                    |
| **Tiempo de espera** | 20 segundos                                           |

**Selector fallido:**

```xpath
//ion-button[contains(.,'Guardar') or contains(.,'guardar')]
```

**Causa:**

- El botón "Guardar" no aparece o tiene un texto diferente
- El formulario de edición no se carga correctamente
- Posible popup o modal que bloquea la interacción

**Recomendación:**

- Verificar el texto exacto del botón en la UI
- Agregar wait para el modal/formulario antes de buscar el botón
- Considerar usar `ion-button[type='submit']` como selector alternativo

---

### 2. `ExpoEditionUsuarioTest.editarUsuarioTest`

| Campo             | Valor                                               |
| ----------------- | --------------------------------------------------- |
| **Clase**         | `automation.funcionalidades.ExpoEditionUsuarioTest` |
| **Archivo Page**  | `ExpoUsuariosPage.java:102`                         |
| **Tipo de Error** | `NoSuchElementException`                            |

**Selector fallido:**

```xpath
//ion-card[.//ion-card-title[contains(normalize-space(), 'usuarioTestAutomatico')]]
```

**Causa:**

- El usuario `usuarioTestAutomatico` no existe en la base de datos
- El test depende de un usuario creado por otro test que no se ejecutó
- Problema de orden de ejecución de tests

**Recomendación:**

- Crear el usuario de prueba en un `@Before` o usar datos existentes
- Hacer el test independiente creando su propio usuario
- Usar un usuario que siempre exista en el sistema

---

### 3. `ExpoSaldoEmpresaTest.consolidacionEmpresaTest`

| Campo             | Valor                                             |
| ----------------- | ------------------------------------------------- |
| **Clase**         | `automation.funcionalidades.ExpoSaldoEmpresaTest` |
| **Archivo Page**  | `ExpoSaldoEmpresaPage.java:97`                    |
| **Tipo de Error** | `NoSuchElementException`                          |

**Selector fallido:**

```xpath
//ion-card[.//ion-card-title[contains(normalize-space(), 'Galpones')]]
```

**Causa:**

- La planilla "Galpones" no existe en la base de datos
- Datos hardcodeados que no coinciden con el ambiente de prueba
- La empresa o planilla fue eliminada

**Recomendación:**

- Usar selectores dinámicos que busquen cualquier planilla disponible
- Crear la planilla "Galpones" como parte del setup del test
- Parametrizar el nombre de la planilla desde configuración

---

### 4. `IngresoCrearFilaTest.ingresoCrearFilaTest`

| Campo             | Valor                                             |
| ----------------- | ------------------------------------------------- |
| **Clase**         | `automation.funcionalidades.IngresoCrearFilaTest` |
| **Archivo Page**  | `IngresoCrearFilaPage.java:64`                    |
| **Tipo de Error** | `NoSuchElementException`                          |

**Selector fallido (XPath absoluto):**

```xpath
/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-ingresos/ion-content/ion-grid/ion-row[2]/ion-col[1]/ion-card
```

**Causa:**

- **XPath absoluto muy frágil** - cualquier cambio en la estructura rompe el test
- No hay planillas de ingresos para mostrar cards
- La estructura de la página cambió

**Recomendación:**

- ⚠️ **URGENTE:** Reemplazar XPath absoluto por selector relativo:
  ```xpath
  //ion-card[1]
  ```
  o
  ```css
  ion-card: first-of-type;
  ```
- Agregar verificación de que existen planillas antes de hacer click

---

### 5. `IngresoDetallesTest.ingresoDetallesTest`

| Campo             | Valor                                            |
| ----------------- | ------------------------------------------------ |
| **Clase**         | `automation.funcionalidades.IngresoDetallesTest` |
| **Archivo Page**  | `IngresoDetallesPage.java:39`                    |
| **Tipo de Error** | `NoSuchElementException`                         |

**Selector fallido (mismo XPath absoluto):**

```xpath
/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-ingresos/ion-content/ion-grid/ion-row[2]/ion-col[1]/ion-card
```

**Causa:** Mismo problema que `IngresoCrearFilaTest`

**Recomendación:** Misma solución - usar selectores relativos

---

### 6. `IngresoEliminarFilaTest.eliminarFilaTest`

| Campo                | Valor                                                |
| -------------------- | ---------------------------------------------------- |
| **Clase**            | `automation.funcionalidades.IngresoEliminarFilaTest` |
| **Archivo Page**     | `IngresoEliminarFilaPage.java:54`                    |
| **Tipo de Error**    | `TimeoutException`                                   |
| **Tiempo de espera** | 20 segundos                                          |

**Selector fallido:**

```css
ion-card
```

**Causa:**

- No hay ningún `ion-card` en la página de ingresos
- La página no terminó de cargar los datos
- Usuario sin planillas asignadas

**Recomendación:**

- Verificar que la sección de ingresos cargó completamente
- Crear datos de prueba antes del test
- Agregar precondición que verifique existencia de datos

---

### 7. `TotalesTest.desgloseIngresos_Egresos_Balance_totalesTest`

| Campo             | Valor                                    |
| ----------------- | ---------------------------------------- |
| **Clase**         | `automation.funcionalidades.TotalesTest` |
| **Archivo Page**  | `TotalesPage.java:60`                    |
| **Tipo de Error** | `NumberFormatException`                  |
| **Mensaje**       | "empty String"                           |

**Causa:**

- El método `getBalance()` retorna una cadena vacía
- El elemento que contiene el balance no tiene texto
- Formato de número no esperado (posiblemente con símbolos)

**Recomendación:**

- Agregar validación antes de parsear: `if (text.isEmpty()) return 0.0;`
- Limpiar el texto de símbolos ($, ,) antes de parsear
- Verificar que el elemento existe y tiene contenido

---

## 📋 Resumen de Causas Principales

| Categoría                               | Cantidad | Tests Afectados                                                            |
| --------------------------------------- | -------- | -------------------------------------------------------------------------- |
| **Datos de prueba inexistentes**        | 5        | F1, ExpoEdition, ExpoSaldo, IngresoCrear, IngresoDetalles, IngresoEliminar |
| **Selectores XPath absolutos frágiles** | 3        | IngresoCrear, IngresoDetalles, IngresoEliminar                             |
| **Timeout/Sincronización**              | 2        | EgresosEditar, IngresoEliminar                                             |
| **Parsing de datos**                    | 1        | Totales                                                                    |
| **UI no cargada/visible**               | 2        | F6, EgresosEditar                                                          |

---

## 🔧 Acciones Recomendadas (Prioridad)

### 🔴 Alta Prioridad

1. **Reemplazar XPath absolutos** en `IngresoCrearFilaPage`, `IngresoDetallesPage`, `IngresoEliminarFilaPage`
2. **Agregar seed data** o crear datos en `@Before` para tests que dependen de datos existentes

### 🟡 Media Prioridad

3. **Mejorar manejo de errores** en `TotalesPage.pasarADouble()` para cadenas vacías
4. **Aumentar tiempos de espera** o agregar waits explícitos para elementos dinámicos

### 🟢 Baja Prioridad

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
