# Arquitectura de Tests - Guía de Uso

## 📋 Resumen

Todos los tests ahora utilizan una arquitectura centralizada:
- **BaseTest**: Clase base que todos los tests deben extender
- **DriverFactory**: Factory que gestiona la creación del WebDriver según el navegador configurado

## 🏗️ Estructura

```
BaseTest (usa DriverFactory)
    ↓
Todos los Tests heredan de BaseTest
```

### BaseTest
- Inicializa el WebDriver usando `DriverFactory`
- Maximiza la ventana automáticamente
- Gestiona el ciclo de vida del driver (setUp/tearDown)
- Usa `@Before` y `@After` de JUnit

### DriverFactory
- Soporta múltiples navegadores: **Edge**, **Chrome**, **Firefox**
- Lee la configuración del archivo `.env`
- Permite seleccionar el navegador mediante propiedades del sistema

## 🚀 Cómo Ejecutar Tests

### Opción 1: Ejecutar con el navegador por defecto (Edge)
```bash
mvn test
```

### Opción 2: Ejecutar con un navegador específico
```bash
# Con Chrome
mvn test -Dbrowser=chrome

# Con Firefox
mvn test -Dbrowser=firefox

# Con Edge (explícito)
mvn test -Dbrowser=edge
```

### Opción 3: Ejecutar un test específico
```bash
mvn test -Dtest=LoginTest -Dbrowser=chrome
```

### Opción 4: Ejecutar múltiples tests
```bash
mvn test -Dtest=LoginTest,ExpoLoginTest -Dbrowser=edge
```

## 📝 Cómo Crear un Nuevo Test

### 1. Extender de BaseTest
```java
package automation;

import org.junit.Test;

public class MiNuevoTest extends BaseTest {
    
    @Test
    public void miPruebaTest() {
        // El driver ya está disponible gracias a BaseTest
        driver.get("https://ejemplo.com");
        
        // Tu lógica de test aquí
    }
}
```

### 2. Si necesitas setup adicional, usa @Before con nombre diferente
```java
package automation;

import org.junit.Before;
import org.junit.Test;
import pages.MiPage;

public class MiNuevoTest extends BaseTest {
    
    private MiPage miPage;
    
    @Before
    public void setUpMiTest() {
        // BaseTest ya inicializó el driver
        miPage = new MiPage(driver);
        driver.get("https://ejemplo.com/login");
        // Tu configuración adicional aquí
    }
    
    @Test
    public void miPruebaTest() {
        miPage.hacerAlgo();
        // Assertions...
    }
}
```

## ⚙️ Configuración del Navegador

### Archivo .env
El proyecto lee las rutas de los drivers desde el archivo `.env`:
```properties
WEBDRIVER_EDGE_PATH=C:\\Users\\Usuario\\Downloads\\edgedriver_win64\\msedgedriver.exe
WEBDRIVER_CHROME_PATH=C:\\ruta\\al\\chromedriver.exe
WEBDRIVER_FIREFOX_PATH=C:\\ruta\\al\\geckodriver.exe
SCREENSHOTS_PATH=C:\\Users\\Usuario\\Desktop\\Capturas\\
```

### Configuración en pom.xml (Opcional)
También puedes configurar el navegador por defecto en el `pom.xml`:
```xml
<properties>
    <browser>edge</browser> <!-- chrome, firefox, edge -->
</properties>
```

## 📦 Tests Migrados

Todos los tests han sido actualizados para usar `BaseTest`:

✅ **LoginTest** - Extiende BaseTest  
✅ **BusquedaTest** - Extiende BaseTest  
✅ **ExpoLoginTest** - Extiende BaseTest  
✅ **ExpoHomeTest** - Extiende BaseTest  
✅ **ExpoAdministrationTest** - Extiende BaseTest  
✅ **ExpoCreationUsuarioTest** - Extiende BaseTest  
✅ **ExpoDeleteUsuarioTest** - Extiende BaseTest  
✅ **ExpoEditionUsuarioTest** - Extiende BaseTest  
✅ **ExpoCategoryTest** - Extiende BaseTest  
✅ **IngresoCrearFilaTest** - Extiende BaseTest  
✅ **IngresoDetallesTest** - Extiende BaseTest  
✅ **IngresoEliminarFilaTest** - Extiende BaseTest  
✅ **EgresosCrearEmpresaTest** - Extiende BaseTest  
✅ **EgresosEditarEmpresaTest** - Extiende BaseTest  

## 🔍 Ventajas de esta Arquitectura

1. **Centralización**: Un solo punto de configuración del driver
2. **Flexibilidad**: Fácil cambio de navegador sin modificar tests
3. **Mantenibilidad**: Cambios en la configuración del driver se hacen en un solo lugar
4. **Consistencia**: Todos los tests siguen el mismo patrón
5. **Escalabilidad**: Agregar nuevos navegadores es sencillo

## 🛠️ DriverFactory - Opciones Avanzadas

### Soporte para ChromeOptions, EdgeOptions, FirefoxOptions
El DriverFactory ya incluye opciones optimizadas:
- `--remote-allow-origins=*`
- `--no-sandbox`
- `--disable-dev-shm-usage`

### Agregar un nuevo navegador
Para agregar soporte de Safari u otro navegador:

```java
case "safari":
    driver = new SafariDriver();
    break;
```

## 📞 Soporte

Si tienes problemas o preguntas sobre la arquitectura de tests, revisa:
1. Este documento
2. La clase `BaseTest.java`
3. La clase `DriverFactory.java`

