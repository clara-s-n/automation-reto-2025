# 🚀 Referencia Rápida - Tests con BaseTest

## Comando Rápido

```bash
# Ejecutar todos los tests con Edge (default)
mvn test

# Ejecutar con Chrome
mvn test -Dbrowser=chrome

# Ejecutar con Firefox
mvn test -Dbrowser=firefox

# Ejecutar un test específico
mvn test -Dtest=NombreDelTest -Dbrowser=chrome
```

---

## Template de Test Nuevo

```java
package automation;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import utils.utilsScreen;

public class MiNuevoTest extends BaseTest {
    
    // Setup adicional (OPCIONAL)
    @Before
    public void setUpMiNuevoTest() {
        // BaseTest ya inicializó el driver
        // Aquí solo configuración específica
    }
    
    @Test
    public void miPruebaTest() {
        try {
            // 1. Navegar
            driver.get("https://ejemplo.com");
            
            // 2. Interactuar
            // ... tus acciones ...
            
            // 3. Validar
            Assert.assertTrue("mensaje", condicion);
            
        } finally {
            // 4. Screenshot OBLIGATORIO
            utilsScreen.takeScreenshot(driver, "nombre_test");
        }
    }
}
```

---

## ✅ QUÉ HACE AUTOMÁTICAMENTE BASETEST

- ✅ Inicializa el WebDriver
- ✅ Maximiza la ventana
- ✅ Cierra el driver al terminar
- ✅ Lee el navegador de -Dbrowser=...

---

## ❌ QUÉ NO DEBES HACER

```java
// ❌ NO crear tu propio driver
private WebDriver driver;
driver = new EdgeDriver();

// ❌ NO usar @Before con nombre "setUp"
@Before
public void setUp() { }

// ❌ NO implementar @After tearDown
@After
public void tearDown() {
    driver.quit();
}

// ❌ NO importar DriverFactory en tests
import utils.DriverFactory;
```

---

## ✅ QUÉ DEBES HACER

```java
// ✅ Extender BaseTest
public class MiTest extends BaseTest {

// ✅ Usar el driver directamente
driver.get("...");

// ✅ Setup con nombre único (si necesario)
@Before
public void setUpMiTest() { }

// ✅ Tomar screenshot al final
utilsScreen.takeScreenshot(driver, "nombre");
```

---

## 📋 Checklist para Nuevo Test

- [ ] Extiende de `BaseTest`
- [ ] NO tiene su propio `WebDriver driver`
- [ ] NO tiene `@Before public void setUp()`
- [ ] NO tiene `@After public void tearDown()`
- [ ] Usa `driver` directamente (viene de BaseTest)
- [ ] Toma screenshot con `utilsScreen.takeScreenshot()`
- [ ] Compila sin errores: `mvn test-compile`

---

## 🔍 Debugging

```bash
# Ver qué navegador se está usando
mvn test -Dbrowser=chrome -X

# Compilar solo para verificar sintaxis
mvn test-compile

# Ejecutar un solo test con logs
mvn test -Dtest=MiTest -X
```

---

## 📁 Archivos Clave

| Archivo | Descripción |
|---------|-------------|
| `BaseTest.java` | Clase base - TODOS los tests la extienden |
| `DriverFactory.java` | Crea el driver según navegador |
| `.env` | Rutas de los drivers |
| `ARQUITECTURA_TESTS.md` | Documentación completa |
| `EjemploNuevoTest.java` | Test ejemplo comentado |

---

## 🎯 Ejemplos Rápidos

### Test Simple
```java
public class SimpleTest extends BaseTest {
    @Test
    public void test() {
        driver.get("https://google.com");
        Assert.assertTrue(driver.getTitle().contains("Google"));
        utilsScreen.takeScreenshot(driver, "simple_test");
    }
}
```

### Test con Setup
```java
public class ConSetupTest extends BaseTest {
    private LoginPage loginPage;
    
    @Before
    public void setUpConSetup() {
        loginPage = new LoginPage(driver);
        driver.get("https://app.com/login");
    }
    
    @Test
    public void testLogin() {
        loginPage.login("user", "pass");
        Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
        utilsScreen.takeScreenshot(driver, "login_test");
    }
}
```

### Test con Try-Finally
```java
public class RobustoTest extends BaseTest {
    @Test
    public void test() {
        try {
            driver.get("https://app.com");
            // ... acciones ...
            Assert.assertTrue("condición", true);
        } catch (Exception e) {
            utilsScreen.takeScreenshot(driver, "test_error");
            throw e;
        } finally {
            utilsScreen.takeScreenshot(driver, "test_final");
        }
    }
}
```

---

## 🎓 Reglas de Oro

1. **SIEMPRE** extiende `BaseTest`
2. **NUNCA** crees tu propio `WebDriver`
3. **SIEMPRE** toma screenshot al final
4. **NUNCA** uses `@Before public void setUp()`
5. **SIEMPRE** usa nombres únicos para `@Before`

---

*Creado: 19/12/2025 | Versión: 1.0*

