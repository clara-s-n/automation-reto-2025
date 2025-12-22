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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;
import utils.utilsScreen;

import java.time.Duration;

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

  // Credenciales de prueba
  protected static final String ADMIN_EMAIL = "ana@agraria.com";
  protected static final String ADMIN_PASSWORD = "Contraseña1";
  protected static final String USER_EMAIL = "juan@agraria.com";
  protected static final String USER_PASSWORD = "Contraseña1";

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
