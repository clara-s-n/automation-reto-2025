# 📊 Reporte de Ejecución de Tests

**Fecha de ejecución:** 22 de Diciembre de 2025, 14:38 hrs  
**Branch:** Lucas_Pruebas  
**Comando ejecutado:** `mvn clean test`  
**Tiempo total:** 6 minutos 16 segundos  
**Resultado:** ❌ BUILD FAILURE

---

## 📈 Resumen General

| Métrica              | Valor |
| -------------------- | ----- |
| **Tests ejecutados** | 28    |
| **Tests pasados**    | 22 ✅ |
| **Errores**          | 5 ❌  |
| **Skipped**          | 1 ⏭️  |
| **Tasa de éxito**    | 78.6% |

---

## ✅ Tests Exitosos (22)

| Clase de Test             | Tests         | Tiempo |
| ------------------------- | ------------- | ------ |
| `AppTest`                 | 1             | 0.029s |
| `EgresosCrearEmpresaTest` | 1             | 22.70s |
| `ExpoAdministrationTest`  | 6             | 65.74s |
| `ExpoCategoryTest`        | 1 (1 skipped) | 10.28s |
| `ExpoCreationUsuarioTest` | 2             | 34.88s |
| `ExpoDeleteUsuarioTest`   | 1             | 8.01s  |
| `ExpoEditionUsuarioTest`  | 1             | 14.58s |
| `ExpoHomeTest`            | 5             | 42.73s |
| `ExpoLoginTest`           | 2             | 15.75s |
| `IngresoDetallesTest`     | 1             | 12.10s |
| `LoginTest`               | 1             | 14.97s |

---

## ❌ Tests Fallidos (5)

---

### 1. EgresosEditarEmpresaTest.editarCITest

| Campo             | Valor                                                    |
| ----------------- | -------------------------------------------------------- |
| **Archivo**       | `src/test/java/automation/EgresosEditarEmpresaTest.java` |
| **Línea**         | 19                                                       |
| **Tipo de Error** | `TimeoutException`                                       |
| **Tiempo**        | 41.78s                                                   |

**Mensaje de error:**

```
Expected condition failed: waiting for element to be clickable:
[[ChromeDriver] -> xpath: //ion-button[contains(.,'Guardar') or contains(.,'guardar')]]
(tried for 20 second(s) with 500 milliseconds interval)
```

**Causa raíz:**  
El botón "Guardar" no aparece o no es clickeable después de intentar editar una empresa.

**Stack trace relevante:**

```
at pages.EgresosEditarEmpresaPage.editarCI(EgresosEditarEmpresaPage.java:158)
at automation.EgresosEditarEmpresaTest.editarCITest(EgresosEditarEmpresaTest.java:19)
```

**Posibles causas:**

- No existe ninguna empresa en la aplicación para editar
- El formulario de edición no se abre correctamente
- El botón tiene un texto diferente (ej: "Actualizar", "Confirmar")

---

### 2. ExpoSaldoEmpresaTest.consolidacionEmpresaTest ⭐ NUEVO

| Campo             | Valor                                                |
| ----------------- | ---------------------------------------------------- |
| **Archivo**       | `src/test/java/automation/ExpoSaldoEmpresaTest.java` |
| **Línea**         | 57                                                   |
| **Tipo de Error** | `NoSuchElementException`                             |
| **Tiempo**        | 20.14s                                               |

**Mensaje de error:**

```
no such element: Unable to locate element:
{"method":"xpath","selector":"//ion-card[.//ion-card-title[contains(normalize-space(), 'Galpones')]]"}
```

**Causa raíz:**  
No se encuentra la planilla llamada "Galpones" en la lista de planillas.

**Stack trace relevante:**

```
at pages.ExpoSaldoEmpresaPage.cardFila(ExpoSaldoEmpresaPage.java:97)
at pages.ExpoSaldoEmpresaPage.seleccionarPlanilla(ExpoSaldoEmpresaPage.java:119)
at automation.ExpoSaldoEmpresaTest.consolidacionEmpresaTest(ExpoSaldoEmpresaTest.java:57)
```

**Posibles causas:**

- La planilla "Galpones" no existe en la base de datos
- El nombre de la planilla es diferente (sensible a mayúsculas/minúsculas)
- La página no cargó completamente antes de buscar el elemento

---

### 3. IngresoCrearFilaTest.ingresoCrearFilaTest

| Campo             | Valor                                                |
| ----------------- | ---------------------------------------------------- |
| **Archivo**       | `src/test/java/automation/IngresoCrearFilaTest.java` |
| **Línea**         | 28                                                   |
| **Tipo de Error** | `NoSuchElementException`                             |
| **Tiempo**        | 14.83s                                               |

**Mensaje de error:**

```
no such element: Unable to locate element:
{"method":"xpath","selector":"/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-content/form/ion-item[2]/ion-input/label/div[2]/input"}
```

**Causa raíz:**  
El XPath absoluto usado para localizar el campo de entrada es muy frágil y no encuentra el elemento.

**Stack trace relevante:**

```
at pages.IngresoCrearFilaPage.completarFormulario(IngresoCrearFilaPage.java:75)
at automation.IngresoCrearFilaTest.ingresoCrearFilaTest(IngresoCrearFilaTest.java:28)
```

**Posibles causas:**

- El XPath absoluto es muy específico y cualquier cambio en la estructura del DOM lo rompe
- La página no ha cargado completamente cuando se intenta interactuar
- La estructura HTML de la página cambió

**Recomendación:** Usar selectores más robustos:

```java
// En lugar de XPath absoluto, usar:
@FindBy(css = "ion-input[formcontrolname='campo'] input")
```

---

### 4. IngresoEliminarFilaTest.eliminarFilaTest

| Campo             | Valor                                                   |
| ----------------- | ------------------------------------------------------- |
| **Archivo**       | `src/test/java/automation/IngresoEliminarFilaTest.java` |
| **Línea**         | 25                                                      |
| **Tipo de Error** | `TimeoutException`                                      |
| **Tiempo**        | 33.21s                                                  |

**Mensaje de error:**

```
Expected condition failed: waiting for element to be clickable:
[[ChromeDriver] -> css selector: ion-card]
(tried for 20 second(s) with 500 milliseconds interval)
```

**Causa raíz:**  
No se encontró ningún elemento `ion-card` en la página de detalles de ingresos.

**Stack trace relevante:**

```
at pages.IngresoEliminarFilaPage.abrirPrimeraFila(IngresoEliminarFilaPage.java:74)
at automation.IngresoEliminarFilaTest.eliminarFilaTest(IngresoEliminarFilaTest.java:25)
```

**Posibles causas:**

- No hay datos de ingresos en la aplicación
- El ingreso seleccionado no tiene filas/detalles
- La navegación interna no carga los datos a tiempo

---

### 5. TotalesTest.desgloseIngresos_Egresos_Balance_totalesTest

| Campo             | Valor                                       |
| ----------------- | ------------------------------------------- |
| **Archivo**       | `src/test/java/automation/TotalesTest.java` |
| **Línea**         | 30                                          |
| **Tipo de Error** | `NumberFormatException`                     |
| **Tiempo**        | 13.25s                                      |

**Mensaje de error:**

```
java.lang.NumberFormatException: empty String
```

**Causa raíz:**  
El método `pasarADouble()` recibe un string vacío cuando intenta parsear el balance.

**Stack trace relevante:**

```
at java.base/jdk.internal.math.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:1866)
at java.base/java.lang.Double.parseDouble(Double.java:971)
at pages.TotalesPage.pasarADouble(TotalesPage.java:60)
at pages.TotalesPage.getBalance(TotalesPage.java:56)
at automation.TotalesTest.desgloseIngresos_Egresos_Balance_totalesTest(TotalesTest.java:30)
```

**Posibles causas:**

- El elemento que contiene el balance está vacío o no tiene texto
- El selector del elemento de balance no encuentra el elemento correcto
- No hay datos de ingresos/egresos para calcular el balance

**Recomendación:** Agregar validación antes de parsear:

```java
public double pasarADouble(String texto) {
    if (texto == null || texto.trim().isEmpty()) {
        return 0.0;
    }
    String limpio = texto.replaceAll("[^\\d.-]", "");
    return Double.parseDouble(limpio);
}
```

---

## ⏭️ Tests Skipped (1)

| Test   | Clase              |
| ------ | ------------------ |
| 1 test | `ExpoCategoryTest` |

---

## ⚠️ Warnings Detectados

### Versión de CDP no soportada

```
WARNING: Unable to find CDP implementation matching 143
WARNING: Unable to find version of CDP to use for 143.0.7499.169
```

**Impacto:** No crítico. Los tests pueden ejecutarse.

### Advertencia de dependencia de datos

```
⚠ ADVERTENCIA: El usuario 'usuarioEditadoAutomatico' no existe.
Este test depende de que ExpoEditionUsuarioTest se ejecute primero.
```

---

## 🔍 Información del Entorno

| Componente            | Versión           |
| --------------------- | ----------------- |
| **Sistema Operativo** | Windows 11 (10.0) |
| **Java**              | 25.0.1            |
| **Selenium**          | 4.27.0            |
| **Chrome**            | 143.0.7499.169    |
| **ChromeDriver**      | 143.0.7499.169    |
| **Maven Surefire**    | 3.2.5             |

---

## 📋 Resumen de Problemas por Categoría

### 🔴 Problemas de Datos (4 tests)

Tests que fallan porque dependen de datos que no existen:

- `EgresosEditarEmpresaTest` - Necesita empresa existente
- `ExpoSaldoEmpresaTest` - Necesita planilla "Galpones" ⭐ NUEVO
- `IngresoEliminarFilaTest` - Necesita filas de ingreso existentes
- `TotalesTest` - Necesita datos de ingresos/egresos

### 🔴 Problemas de Selectores (1 test)

Tests que fallan por XPath/selectores frágiles:

- `IngresoCrearFilaTest` - XPath absoluto muy específico

---

## 📝 Recomendaciones Generales

1. **Orden de ejecución:** Configurar dependencias entre tests
2. **Data fixtures:** Implementar `@Before` para crear datos necesarios
3. **Selectores robustos:** Reemplazar XPath absolutos por selectores CSS
4. **Validaciones:** Agregar null-checks antes de parsear datos
5. **Esperas explícitas:** Aumentar tiempos de espera o usar condiciones más específicas

---

## 📁 Archivos de Reporte Detallados

```
target/surefire-reports/
├── automation.EgresosEditarEmpresaTest.txt
├── automation.ExpoSaldoEmpresaTest.txt
├── automation.IngresoCrearFilaTest.txt
├── automation.IngresoEliminarFilaTest.txt
├── automation.TotalesTest.txt
└── TEST-*.xml (reportes XML)
```

---

_Reporte generado automáticamente el 22/12/2025 14:38 hrs_
