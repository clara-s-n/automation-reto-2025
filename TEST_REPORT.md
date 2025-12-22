# 📊 Reporte de Ejecución de Tests

**Fecha de ejecución:** 22 de Diciembre de 2025  
**Comando ejecutado:** `mvn clean test`  
**Tiempo total:** 6 minutos 34 segundos  
**Resultado:** ❌ BUILD FAILURE

---

## 📈 Resumen General

| Métrica              | Valor |
| -------------------- | ----- |
| **Tests ejecutados** | 25    |
| **Tests pasados**    | 22 ✅ |
| **Errores**          | 2 ❌  |
| **Skipped**          | 1 ⏭️  |
| **Tasa de éxito**    | 88%   |

---

## ✅ Tests Exitosos (22)

| Clase de Test             | Tests         | Tiempo |
| ------------------------- | ------------- | ------ |
| `AppTest`                 | 1             | 0.097s |
| `EgresosCrearEmpresaTest` | 1             | 24.49s |
| `ExpoAdministrationTest`  | 5             | 66.20s |
| `ExpoCategoryTest`        | 1 (1 skipped) | 12.59s |
| `ExpoCreationUsuarioTest` | 2             | 37.82s |
| `ExpoDeleteUsuarioTest`   | 1             | 9.28s  |
| `ExpoEditionUsuarioTest`  | 1             | 17.13s |
| `ExpoHomeTest`            | 5             | 52.39s |
| `ExpoLoginTest`           | 2             | 18.40s |
| `IngresoCrearFilaTest`    | 1             | 28.41s |
| `IngresoDetallesTest`     | 1             | 13.61s |
| `LoginTest`               | 1             | 17.66s |

---

## ❌ Tests Fallidos (2)

### 1. EgresosEditarEmpresaTest.editarCITest

**Ubicación:** `src/test/java/automation/EgresosEditarEmpresaTest.java:19`

**Tipo de Error:** `TimeoutException`

**Mensaje completo:**

```
Expected condition failed: waiting for element to be clickable:
[[ChromeDriver: chrome on windows] -> xpath: //ion-button[contains(.,'Guardar') or contains(.,'guardar')]]
(tried for 20 second(s) with 500 milliseconds interval)
```

**Causa raíz:**  
El test no puede encontrar el botón "Guardar" después de intentar editar una empresa. El flujo falla en el método `editarCI()` de `EgresosEditarEmpresaPage.java` línea 158.

**Análisis detallado:**

1. El test navega a la página de empresas
2. Selecciona la primera empresa (card)
3. Intenta encontrar el botón de editar (con múltiples selectores)
4. **PROBLEMA:** El botón "Guardar" con el XPath `//ion-button[contains(.,'Guardar') or contains(.,'guardar')]` no aparece o no es clickeable

**Posibles causas:**

- ❓ No existe ninguna empresa en la aplicación para editar
- ❓ El formulario de edición no se abre correctamente
- ❓ El botón tiene un texto diferente (ej: "Actualizar", "Confirmar")
- ❓ El botón está dentro de un Shadow DOM de Ionic

**Stack Trace:**

```
at pages.EgresosEditarEmpresaPage.editarCI(EgresosEditarEmpresaPage.java:158)
at automation.EgresosEditarEmpresaTest.editarCITest(EgresosEditarEmpresaTest.java:19)
```

---

### 2. IngresoEliminarFilaTest.eliminarFilaTest

**Ubicación:** `src/test/java/automation/IngresoEliminarFilaTest.java:25`

**Tipo de Error:** `TimeoutException`

**Mensaje completo:**

```
Expected condition failed: waiting for element to be clickable:
[[ChromeDriver: chrome on windows] -> css selector: ion-card]
(tried for 20 second(s) with 500 milliseconds interval)
```

**Causa raíz:**  
El test no puede encontrar ningún elemento `ion-card` en la página de detalles de ingresos. El método `abrirPrimeraFila()` en `IngresoEliminarFilaPage.java` línea 74 falla al esperar por cards.

**Análisis detallado:**

1. El test hace login correctamente
2. Navega a `/ingresos`
3. Abre el primer ingreso (`abrirPrimerIngreso()`)
4. **PROBLEMA:** Al intentar abrir la primera fila con `abrirPrimeraFila()`, no encuentra ningún `ion-card`

**Posibles causas:**

- ❓ No hay datos de ingresos en la aplicación
- ❓ El ingreso seleccionado no tiene filas/detalles
- ❓ La navegación interna no carga los datos a tiempo
- ❓ El selector `ion-card` no es el correcto para los detalles de fila

**Stack Trace:**

```
at pages.IngresoEliminarFilaPage.abrirPrimeraFila(IngresoEliminarFilaPage.java:74)
at automation.IngresoEliminarFilaTest.eliminarFilaTest(IngresoEliminarFilaTest.java:25)
```

---

## ⏭️ Tests Skipped (1)

| Test                        | Razón                                                                                     |
| --------------------------- | ----------------------------------------------------------------------------------------- |
| `ExpoCategoryTest` (1 de 2) | El test fue marcado como skipped (probablemente `@Ignore` o condición previa no cumplida) |

---

## ⚠️ Warnings Detectados

### 1. Versión de CDP no soportada

```
WARNING: Unable to find CDP implementation matching 143
WARNING: Unable to find version of CDP to use for 143.0.7499.169
```

**Impacto:** No crítico. Los tests pueden ejecutarse pero algunas funcionalidades de DevTools pueden no estar disponibles.

**Solución recomendada:** Actualizar Selenium o agregar dependencia específica de CDP:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-devtools-v131</artifactId>
    <version>4.27.0</version>
</dependency>
```

### 2. Usuario no existe (Warning de test)

```
⚠ ADVERTENCIA: El usuario 'usuarioEditadoAutomatico' no existe.
Este test depende de que ExpoEditionUsuarioTest se ejecute primero.
```

**Impacto:** El test `ExpoDeleteUsuarioTest` no puede eliminar el usuario porque no existe.

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

## 📋 Recomendaciones

### Para `EgresosEditarEmpresaTest`:

1. **Verificar datos existentes:** Asegurarse de que existe al menos una empresa antes de ejecutar el test
2. **Inspeccionar el DOM:** Usar DevTools para verificar el texto exacto del botón de guardar
3. **Mejorar selectores:** Considerar usar atributos `data-testid` o `aria-label`
4. **Agregar logs:** Añadir logging para saber en qué paso falla exactamente

```java
// Sugerencia de mejora
@FindBy(css = "ion-button[type='submit'], ion-button.guardar-btn")
private WebElement botonGuardar;
```

### Para `IngresoEliminarFilaTest`:

1. **Crear datos de prueba:** Ejecutar `IngresoCrearFilaTest` antes para asegurar que existen filas
2. **Verificar navegación:** El ingreso seleccionado debe tener al menos una fila
3. **Espera explícita mejorada:** Aumentar tiempo de espera o usar condiciones más específicas

```java
// Sugerencia de mejora
wait.until(driver -> {
    List<WebElement> cards = driver.findElements(By.cssSelector("ion-card"));
    return !cards.isEmpty();
});
```

### Generales:

1. **Orden de ejecución:** Configurar `@FixMethodOrder` o usar TestNG para ordenar tests con dependencias
2. **Data fixtures:** Implementar `@Before` para crear datos necesarios
3. **Cleanup:** Implementar `@After` para limpiar datos de prueba
4. **Screenshots en fallo:** Agregar capturas automáticas cuando falle un test

---

## 📁 Archivos de Reporte

Los reportes detallados de Surefire se encuentran en:

```
target/surefire-reports/
├── automation.EgresosEditarEmpresaTest.txt
├── automation.IngresoEliminarFilaTest.txt
├── TEST-automation.EgresosEditarEmpresaTest.xml
└── TEST-automation.IngresoEliminarFilaTest.xml
```

---

_Reporte generado automáticamente el 22/12/2025_
