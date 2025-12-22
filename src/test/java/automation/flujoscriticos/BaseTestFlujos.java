/**
 * Clase base para tests de flujos críticos
 * Extiende la funcionalidad de BaseTest con métodos comunes para flujos completos
 */
package automation.flujoscriticos;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;
import utils.utilsScreen;

import java.time.Duration;
import java.util.List;

public class BaseTestFlujos {

  protected WebDriver driver;
  protected WebDriverWait wait;

  // URLs base
  protected static final String BASE_URL = "https://reto2025.brazilsouth.cloudapp.azure.com";
  protected static final String LOGIN_URL = BASE_URL + "/login";
  protected static final String INGRESOS_URL = BASE_URL + "/ingresos";
  protected static final String EGRESOS_URL = BASE_URL + "/egresos";
  protected static final String EMPRESAS_URL = BASE_URL + "/empresas";
  protected static final String TOTALES_URL = BASE_URL + "/totales";
  protected static final String ADMIN_URL = BASE_URL + "/administracion";
  protected static final String ANIOS_URL = BASE_URL + "/anio";

  // Credenciales de prueba
  protected static final String ADMIN_EMAIL = "ana@agraria.com";
  protected static final String ADMIN_PASSWORD = "Contraseña1";
  protected static final String USER_EMAIL = "juan@agraria.com";
  protected static final String USER_PASSWORD = "Contraseña1";

  // Año de pruebas - usar año antiguo para no interferir con datos reales
  protected static final String TEST_YEAR = "2002";

  @Before
  public void setUp() {
    String browser = DriverFactory.getConfiguredBrowser();
    driver = DriverFactory.getDriver(browser);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    utilsScreen.maximizeWindow(driver);
  }

  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  /**
   * Setup completo: login + asegurar año de pruebas
   * Usar este método en lugar de loginAsAdmin() cuando se necesiten datos de
   * prueba
   */
  protected void setupTestEnvironment() {
    loginAsAdmin();
    waitForPageLoad();
    ensureTestYearExists();
    selectTestYear();
  }

  /**
   * Asegura que el año de pruebas (2002) exista, creándolo si es necesario
   */
  protected void ensureTestYearExists() {
    try {
      // Navegar a la sección de años
      driver.get(ANIOS_URL);
      waitForPageLoad();

      // Verificar si el año ya existe
      if (isYearPresent(TEST_YEAR)) {
        System.out.println("Año " + TEST_YEAR + " ya existe");
        return;
      }

      // Si no existe, intentar crearlo
      createYear(TEST_YEAR);

    } catch (Exception e) {
      System.out.println("No se pudo verificar/crear año de pruebas: " + e.getMessage());
      // Continuar sin fallar - algunos tests pueden funcionar sin esto
    }
  }

  /**
   * Verifica si un año específico está presente en la lista
   */
  protected boolean isYearPresent(String year) {
    try {
      List<WebElement> yearElements = driver.findElements(
          By.xpath("//*[contains(text(),'" + year + "')]"));
      return yearElements.size() > 0;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Crea un nuevo año fiscal
   */
  protected void createYear(String year) {
    try {
      System.out.println("Intentando crear año: " + year);

      // Buscar botón para crear año (FAB o botón regular)
      WebElement createButton = null;

      // Intentar encontrar FAB button primero
      List<WebElement> fabButtons = driver.findElements(By.cssSelector("ion-fab-button"));
      if (fabButtons.size() > 0) {
        createButton = fabButtons.get(0);
      }

      // Si no hay FAB, buscar botón con texto "Crear" o "Agregar"
      if (createButton == null) {
        List<WebElement> buttons = driver.findElements(
            By.xpath("//ion-button[contains(.,'Crear') or contains(.,'Agregar') or contains(.,'Nuevo')]"));
        if (buttons.size() > 0) {
          createButton = buttons.get(0);
        }
      }

      // Si no hay botón específico, buscar cualquier botón de acción
      if (createButton == null) {
        List<WebElement> allButtons = driver.findElements(
            By.cssSelector("ion-button[color='primary'], ion-button:not([fill='clear'])"));
        if (allButtons.size() > 0) {
          createButton = allButtons.get(0);
        }
      }

      if (createButton != null) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);
        waitForPageLoad();

        // Buscar campo de input para el año
        WebElement yearInput = null;

        // Intentar diferentes selectores para el input
        String[] inputSelectors = {
            "ion-input input",
            "input[type='number']",
            "input[type='text']",
            "ion-input[formcontrolname='anio'] input",
            "ion-input[formcontrolname='year'] input",
            "input"
        };

        for (String selector : inputSelectors) {
          List<WebElement> inputs = driver.findElements(By.cssSelector(selector));
          if (inputs.size() > 0) {
            yearInput = inputs.get(0);
            break;
          }
        }

        if (yearInput != null) {
          yearInput.clear();
          yearInput.sendKeys(year);
          shortWait();

          // Buscar y hacer click en botón de guardar/confirmar
          WebElement saveButton = findSaveButton();
          if (saveButton != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
            waitForPageLoad();
            System.out.println("Año " + year + " creado exitosamente");
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Error al crear año: " + e.getMessage());
    }
  }

  /**
   * Busca el botón de guardar en un formulario
   */
  protected WebElement findSaveButton() {
    String[] buttonSelectors = {
        "//ion-button[contains(.,'Guardar') or contains(.,'guardar')]",
        "//ion-button[contains(.,'Crear') or contains(.,'crear')]",
        "//ion-button[contains(.,'Confirmar') or contains(.,'confirmar')]",
        "//ion-button[contains(.,'Aceptar') or contains(.,'aceptar')]",
        "//button[contains(.,'Guardar')]",
        "//ion-button[@type='submit']"
    };

    for (String xpath : buttonSelectors) {
      List<WebElement> buttons = driver.findElements(By.xpath(xpath));
      if (buttons.size() > 0) {
        return buttons.get(0);
      }
    }
    return null;
  }

  /**
   * Selecciona el año de pruebas (2002) como año activo
   */
  protected void selectTestYear() {
    try {
      // Navegar a una página que tenga selector de año (ingresos o egresos)
      navigateToIngresos();
      waitForPageLoad();

      // Buscar selector de año (ion-select, dropdown, etc.)
      List<WebElement> yearSelectors = driver.findElements(
          By.cssSelector("ion-select, select, ion-segment-button"));

      // Intentar encontrar un selector que contenga años
      for (WebElement selector : yearSelectors) {
        try {
          String text = selector.getText();
          if (text.contains("20") || selector.getAttribute("placeholder") != null) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selector);
            shortWait();

            // Buscar la opción del año de prueba
            List<WebElement> options = driver.findElements(
                By.xpath("//ion-select-option[contains(.,'" + TEST_YEAR + "')] | " +
                    "//ion-item[contains(.,'" + TEST_YEAR + "')] | " +
                    "//option[contains(.,'" + TEST_YEAR + "')]"));

            if (options.size() > 0) {
              ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(0));
              waitForPageLoad();
              System.out.println("Año " + TEST_YEAR + " seleccionado");
              return;
            }
          }
        } catch (Exception e) {
          // Continuar con el siguiente selector
        }
      }

      // Alternativa: buscar directamente el año en la página y hacer click
      List<WebElement> yearLinks = driver.findElements(
          By.xpath("//*[text()='" + TEST_YEAR + "' or contains(@class,'year') and contains(.,'" + TEST_YEAR + "')]"));
      if (yearLinks.size() > 0) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yearLinks.get(0));
        waitForPageLoad();
        System.out.println("Año " + TEST_YEAR + " seleccionado via link directo");
      }

    } catch (Exception e) {
      System.out.println("No se pudo seleccionar año de pruebas: " + e.getMessage());
    }
  }

  /**
   * Crea una planilla de ingresos de prueba en el año actual
   */
  protected void createTestPlanillaIngresos(String nombre) {
    try {
      navigateToIngresos();
      waitForPageLoad();

      // Buscar botón para crear planilla
      WebElement createButton = null;
      List<WebElement> fabButtons = driver.findElements(By.cssSelector("ion-fab-button"));
      if (fabButtons.size() > 0) {
        createButton = fabButtons.get(0);
      }

      if (createButton != null) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);
        waitForPageLoad();

        // Buscar input para nombre
        List<WebElement> inputs = driver.findElements(By.cssSelector("ion-input input, input[type='text']"));
        if (inputs.size() > 0) {
          inputs.get(0).clear();
          inputs.get(0).sendKeys(nombre);
        }

        // Guardar
        WebElement saveButton = findSaveButton();
        if (saveButton != null) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
          waitForPageLoad();
          System.out.println("Planilla de ingresos '" + nombre + "' creada");
        }
      }
    } catch (Exception e) {
      System.out.println("Error al crear planilla de ingresos: " + e.getMessage());
    }
  }

  /**
   * Crea una planilla de egresos de prueba en el año actual
   */
  protected void createTestPlanillaEgresos(String nombre) {
    try {
      navigateToEgresos();
      waitForPageLoad();

      // Buscar botón para crear planilla
      WebElement createButton = null;
      List<WebElement> fabButtons = driver.findElements(By.cssSelector("ion-fab-button"));
      if (fabButtons.size() > 0) {
        createButton = fabButtons.get(0);
      }

      if (createButton != null) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);
        waitForPageLoad();

        // Buscar input para nombre
        List<WebElement> inputs = driver.findElements(By.cssSelector("ion-input input, input[type='text']"));
        if (inputs.size() > 0) {
          inputs.get(0).clear();
          inputs.get(0).sendKeys(nombre);
        }

        // Guardar
        WebElement saveButton = findSaveButton();
        if (saveButton != null) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
          waitForPageLoad();
          System.out.println("Planilla de egresos '" + nombre + "' creada");
        }
      }
    } catch (Exception e) {
      System.out.println("Error al crear planilla de egresos: " + e.getMessage());
    }
  }

  /**
   * Realiza login con credenciales de administrador
   */
  protected void loginAsAdmin() {
    login(ADMIN_EMAIL, ADMIN_PASSWORD);
  }

  /**
   * Realiza login con credenciales de usuario normal
   */
  protected void loginAsUser() {
    login(USER_EMAIL, USER_PASSWORD);
  }

  /**
   * Realiza login con credenciales específicas
   */
  protected void login(String email, String password) {
    driver.get(LOGIN_URL);
    waitForPageLoad();

    WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(
        By.cssSelector("ion-input[formcontrolname='email'] input, input[formcontrolname='email']")));
    emailInput.clear();
    emailInput.sendKeys(email);

    WebElement passwordInput = driver.findElement(
        By.cssSelector("ion-input[formcontrolname='password'] input, input[formcontrolname='password']"));
    passwordInput.clear();
    passwordInput.sendKeys(password);

    WebElement loginButton = driver.findElement(
        By.cssSelector("ion-button[type='submit'], button[type='submit']"));
    loginButton.click();

    waitForPageLoad();
  }

  /**
   * Navega a la sección de Administración
   */
  protected void navigateToAdmin() {
    driver.get(ADMIN_URL);
    waitForPageLoad();
  }

  /**
   * Navega a la sección de Ingresos
   */
  protected void navigateToIngresos() {
    driver.get(INGRESOS_URL);
    waitForPageLoad();
  }

  /**
   * Navega a la sección de Egresos
   */
  protected void navigateToEgresos() {
    driver.get(EGRESOS_URL);
    waitForPageLoad();
  }

  /**
   * Navega a la sección de Totales
   */
  protected void navigateToTotales() {
    driver.get(TOTALES_URL);
    waitForPageLoad();
  }

  /**
   * Navega a la sección de Empresas
   */
  protected void navigateToEmpresas() {
    driver.get(EMPRESAS_URL);
    waitForPageLoad();
  }

  /**
   * Espera a que la página cargue completamente
   */
  protected void waitForPageLoad() {
    try {
      Thread.sleep(1500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Espera breve para animaciones
   */
  protected void shortWait() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Toma screenshot con nombre descriptivo
   */
  protected void takeScreenshot(String testName) {
    utilsScreen.takeScreenshot(driver, "flujos_criticos_" + testName);
  }

  /**
   * Verifica si un elemento está presente en la página
   */
  protected boolean isElementPresent(By locator) {
    try {
      return driver.findElements(locator).size() > 0;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Obtiene el texto de un elemento de forma segura
   */
  protected String getElementText(By locator) {
    try {
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      return element.getText().trim();
    } catch (Exception e) {
      return "";
    }
  }

  /**
   * Hace click en un elemento esperando que sea clickeable
   */
  protected void clickElement(By locator) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    element.click();
  }

  /**
   * Ingresa texto en un campo de input
   */
  protected void enterText(By locator, String text) {
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    element.clear();
    element.sendKeys(text);
  }

  /**
   * Parsea un valor monetario de string a double
   * Maneja formatos como "$1,234.56" o "1234.56"
   */
  protected double parseMoneyValue(String value) {
    if (value == null || value.trim().isEmpty()) {
      return 0.0;
    }
    String cleaned = value.replaceAll("[^0-9.,\\-]", "")
        .replace(",", "");
    try {
      return Double.parseDouble(cleaned);
    } catch (NumberFormatException e) {
      return 0.0;
    }
  }
}
