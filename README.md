# Automatización de Pruebas con Selenium WebDriver y Java

## 📊 Reporte de Tests

Para ver el estado actual de las pruebas automatizadas, consulta el [Reporte de Tests](TEST_FAILURE_REPORT.md).

**Última ejecución:** 23 de Diciembre de 2025 | **Tasa de éxito:** 97.4% (111/114 tests)

---

## 📋 Convenciones de Código

### 1. Nomenclatura de Clases de Test

- **Clases de Test**: Deben seguir el patrón `[funcionalidadTest]`

  - Ejemplo: `LoginTest.java`, `RegistroTest.java`, `BusquedaTest.java`

- **Métodos de Test**: El nombre del método debe ser el mismo que la funcionalidad que prueba
  - Ejemplo:
    ```java
    @Test
    public void loginTest() {
        // Código del test
    }
    ```

### 2. Nomenclatura de Page Objects (POM)

- **Clases Page**: Deben seguir el patrón `[NombreDeLaVentanaPage]`
  - Ejemplo: `LoginPage.java`, `HomePage.java`, `DashboardPage.java`

### 3. Configuración del WebDriver

- **Driver Utilizado**: Microsoft Edge
- **Configuración**: La ruta del driver debe configurarse en un archivo `.env`
- **Creación del Driver**: Utilizar la clase `DriverFactory` ubicada en `src/main/java/utils/`

**Ejemplo de uso:**

```java
WebDriver driver = DriverFactory.getDriver("edge");
```

**Archivo .env requerido:**

```properties
WEBDRIVER_EDGE_PATH=C:\\ruta\\al\\msedgedriver.exe
SCREENSHOTS_PATH=C:\\ruta\\donde\\guardar\\imagenes
```

### 4. Gestión de Screenshots

- **Ruta de Imágenes**: Configurar la ruta para guardar capturas de pantalla en el archivo `.env`
- **Variable**: `SCREENSHOTS_PATH`
- Las capturas deben guardarse cuando ocurra un fallo o según se requiera en el test

### 5. Comentarios y Documentación

#### Comentario de Encabezado Obligatorio

Cada archivo de test DEBE comenzar con un comentario indicando el autor:

```java
/**
 * Autor: [Nombre del Tester]
 * Fecha: [Fecha de creación]
 * Descripción: [Breve descripción de qué funcionalidad se prueba]
 */
package automation;

public class LoginTest {
    // ...
}
```

#### Comentarios Explicativos

- Todos los tests deben contener comentarios explicativos sobre lo que hacen
- Los comentarios deben ser claros y concisos
- Explicar el "qué" y el "por qué" de cada acción importante

**Ejemplo:**

```java
@Test
public void login() {
    // Navegar a la página de login
    driver.get("https://ejemplo.com/login");

    // Ingresar credenciales válidas
    loginPage.ingresarUsuario("testuser");
    loginPage.ingresarPassword("password123");

    // Hacer clic en el botón de iniciar sesión
    loginPage.clickBotonLogin();

    // Verificar que el usuario fue redirigido al dashboard
    Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
}
```

### 6. Uso de Assertions

- **Obligatorio**: Todos los tests deben incluir assertions para validar los resultados esperados
- **Framework**: Utilizar JUnit

**Ejemplos:**

```java
// JUnit
import org.junit.Assert;

Assert.assertEquals("Mensaje esperado", elementoActual.getText());
Assert.assertTrue(condicion);
Assert.assertFalse(condicion);
Assert.assertNotNull(elemento);
```

## 🏗️ Estructura del Proyecto

```
automation-reto-2025/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── automation/
│   │       │   └── App.java
│   │       └── utils/
│   │           ├── DriverFactory.java
│   │           └── utilsScreen.java
│   └── test/
│       └── java/
│           └── automation/
│               └── [funcionalidadTest].java
├── .env
├── pom.xml
└── README.md
```

## 📝 Plantilla de Clase de Test

```java
/**
 * Autor: [Tu Nombre]
 * Fecha: [Fecha]
 * Descripción: Test de la funcionalidad [nombre]
 */
package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class [FuncionalidadTest] {

    private WebDriver driver;
    private [NombrePage] nombrePage;

    @Before
    public void setUp() {
        // Inicializar el driver usando DriverFactory
        driver = DriverFactory.getDriver("edge");
        driver.manage().window().maximize();

        // Inicializar Page Object
        nombrePage = new [NombrePage](driver);
    }

    @Test
    public void [nombreDelTest]() {
        // Navegar a la URL
        driver.get("https://url-de-la-aplicacion.com");

        // Realizar acciones del test
        // ...

        // Validar resultados con assertions
        Assert.assertTrue("Mensaje de error descriptivo", condicion);
    }

    @After
    public void tearDown() {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}
```

## 📝 Plantilla de Page Object

```java
/**
 * Autor: [Tu Nombre]
 * Fecha: [Fecha]
 * Descripción: Page Object para la ventana [nombre]
 */
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class [NombreDeLaVentanaPage] {

    private WebDriver driver;

    // Localizar elementos usando XPath o Full XPath
    @FindBy(xpath = "//input[@id='ejemplo']")
    private WebElement elementoEjemplo;

    // Constructor
    public [NombreDeLaVentanaPage](WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Métodos de la página
    public void realizarAccion() {
        // Implementación del método
        elementoEjemplo.click();
    }

    public String obtenerTexto() {
        return elementoEjemplo.getText();
    }
}
```

## 🚀 Ejecución de Tests

```bash
# Ejecutar todos los tests
mvn clean test

# Ejecutar un test específico
mvn clean test -Dtest=[FuncionalidadTest]

# Ejecutar con un navegador específico
mvn clean test -Dbrowser=edge
```

## ⚙️ Configuración Inicial

1. **Crear archivo `.env`** en la raíz del proyecto con las siguientes variables:

   ```properties
   WEBDRIVER_EDGE_PATH=C:\\ruta\\al\\msedgedriver.exe
   SCREENSHOTS_PATH=C:\\ruta\\screenshots
   ```

2. **Instalar dependencias**:

   ```bash
   mvn clean install
   ```

3. **Verificar configuración del driver** en `DriverFactory.java`

## 📌 Checklist Antes de Hacer Commit

- [ ] El nombre de la clase sigue el patrón `[funcionalidadTest]`
- [ ] La clase Page sigue el patrón `[NombreDeLaVentanaPage]`
- [ ] Incluye comentario de autor al inicio del archivo
- [ ] Todos los tests tienen comentarios explicativos
- [ ] Se utilizan XPath o Full XPath para localizar elementos
- [ ] Todos los tests incluyen assertions
- [ ] Cada test toma una captura de pantalla al final
- [ ] El código compila sin errores
- [ ] Los tests pasan exitosamente
- [ ] El archivo `.env` NO está incluido en el commit

## 📚 Recursos Adicionales

- [Documentación de Selenium](https://www.selenium.dev/documentation/)
- [JUnit Documentation](https://docs.junit.org/5.14.1/overview)

- [XPath Tutorial](https://www.w3schools.com/xml/xpath_intro.asp)
