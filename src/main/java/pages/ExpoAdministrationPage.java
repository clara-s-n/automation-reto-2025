/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
 * Descripción: Page Object para la ventana Administración
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

public class ExpoAdministrationPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;
    /*
     * Page Object para la ventana Administración
     * Contiene métodos para navegar entre las secciones principales:
     * - Usuarios
     * - Categorías
     * - Años
     * - Egresos
     * - Planillas de ingreso
     */

    // Botones principales
    @FindBy(xpath = "//ion-button[.//text()[normalize-space()='Usuarios']]")
    private WebElement botonUsuarios;

    @FindBy(xpath = "//ion-button[.//text()[normalize-space()='Categorías']]")
    private WebElement botonCategorias;

    @FindBy(xpath = "//ion-button[.//text()[normalize-space()='Años']]")
    private WebElement botonAnios;

    @FindBy(xpath = "//ion-button[.//text()[normalize-space()='Egresos']]")
    private WebElement botonEgresos;

    @FindBy(xpath = "//ion-button[contains(normalize-space(), 'Planillas de Ingreso')]\r\n" + //
            "")
    private WebElement botonPlanillas;

    @FindBy(xpath = "//ion-button[.//text()[normalize-space()='Acerca de']]")
    private WebElement botonAboutUs;

    public ExpoAdministrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    // Métodos de navegación
    public void clickUsuarios() {
        clickSeccion(botonUsuarios, "Usuarios");
    }

    public void clickCategorias() {
        clickSeccion(botonCategorias, "Categorías");
    }

    public void clickAnios() {
        clickSeccion(botonAnios, "Años");
    }

    public void clickEgresos() {
        clickSeccion(botonEgresos, "Egresos");
    }

    public void clickPlanillas() {
        clickSeccion(botonPlanillas, "Planillas de ingreso");
    }

    public void clickAboutUs() {
        clickSeccion(botonAboutUs, "Acerca de");
    }

    // Método para clic seguro
    private void clickSeccion(WebElement boton, String nombre) {
        try {
            if (boton != null) {
                safeClick.safeClick(boton);
                return;
            }
        } catch (Exception ignored) {
        }

        try {
            WebElement fallback = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[normalize-space()='" + nombre + "']")));
            safeClick.safeClick(fallback);
        } catch (Exception error) {
            utilsScreen.takeScreenshot(driver, "administration_click_" + nombre.replaceAll("\\s+", "_") + "_error");
            throw error;
        }
    }
}
