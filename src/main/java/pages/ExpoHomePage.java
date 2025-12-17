/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-16
 * Descripción: Page Object para la página principal de la app (Ingresos)
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;
import utils.utilsScreen;

import java.time.Duration;

public class ExpoHomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final SafeClick safeClick;

    /*
     * Page Object para la página principal de la app (Expo Home)
     * Contiene métodos para navegar entre las pestañas principales:
     * - Ingresos
     * - Egresos
     * - Empresas
     * - Totales
     * - Administración
     */

    // Botones de navegación inferiores,los id son sacados del body
    @FindBy(id = "tab-button-ingresos")
    private WebElement tabIngresos;

    @FindBy(id = "tab-button-egresos")
    private WebElement tabEgresos;

    @FindBy(id = "tab-button-empresas")
    private WebElement tabEmpresas;

    @FindBy(id = "tab-button-totales")
    private WebElement tabTotales;

    @FindBy(id = "tab-button-administracion")
    private WebElement tabAdministracion;

    @FindBy(css = "ion-button[routerlink='/administracion']")
    private WebElement headerAdministracionButton;

    public ExpoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public boolean isAtHomePage() {
        try {
            return tabIngresos != null && tabIngresos.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNavItem(String label) {
        try {
            WebElement el = driver.findElement(
                    By.xpath("//a[normalize-space()='" + label + "']|//button[normalize-space()='" + label + "']"));
            safeClick.safeClick(el);
        } catch (Exception e) {
            // capturar pantalla en caso de fallo
            utilsScreen.takeScreenshot(driver, "clickNavItem_error_" + label.replaceAll("\\s+", "_"));
            throw e;
        }
    }

    // Métodos específicos para los tabs principales
    public void clickIngresos() {
        try {
            safeClick.safeClick(tabIngresos);
        } catch (Exception e) {
            utilsScreen.takeScreenshot(driver, "clickIngresos_error");
            throw e;
        }
    }

    public void clickEgresos() {
        try {
            safeClick.safeClick(tabEgresos);
        } catch (Exception e) {
            utilsScreen.takeScreenshot(driver, "clickEgresos_error");
            throw e;
        }
    }

    public void clickEmpresas() {
        try {
            safeClick.safeClick(tabEmpresas);
        } catch (Exception e) {
            utilsScreen.takeScreenshot(driver, "clickEmpresas_error");
            throw e;
        }
    }

    public void clickTotales() {
        try {
            safeClick.safeClick(tabTotales);
        } catch (Exception e) {
            utilsScreen.takeScreenshot(driver, "clickTotales_error");
            throw e;
        }
    }

    public void clickAdministracionTab() {
        try {
            safeClick.safeClick(tabAdministracion);
        } catch (Exception e) {
            utilsScreen.takeScreenshot(driver, "clickAdministracionTab_error");
            throw e;
        }
    }

    // por las dudas
    public void clickHeaderAdministracion() {
        try {
            safeClick.safeClick(headerAdministracionButton);
        } catch (Exception e) {
            utilsScreen.takeScreenshot(driver, "clickHeaderAdministracion_error");
            throw e;
        }
    }

    /**
     * Método helper para tomar captura desde el Page Object
     */
    public void takeScreenshot(String name) {
        utilsScreen.takeScreenshot(driver, name);
    }

}