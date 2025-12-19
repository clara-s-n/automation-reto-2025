package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;
import utils.utilsScreen;

import java.time.Duration;

public class ExpoCategoryPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public ExpoCategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    // Botón flotante para crear categoría
    @FindBy(xpath = "//ion-fab-button[.//ion-icon[@name='add']]")
    private WebElement botonCrearCategoria;

    @FindBy(xpath = "//ion-input[@formcontrolname='nombre']//input")
    private WebElement inputNombre;

    @FindBy(xpath = "//ion-textarea[@formcontrolname='descripcion']//textarea")
    private WebElement inputDescripcion;

    @FindBy(xpath = "//ion-button[normalize-space()='Guardar']")
    private WebElement botonGuardar;

    // Crear categoría
    public void clickCrearCategoria() {
        safeClick.safeClick(botonCrearCategoria);
    }

    /*
     * public void ingresarNombre(String nombre) {
     * inputNombre.click();
     * inputNombre.clear();
     * inputNombre.sendKeys(nombre);
     * }
     * 
     * public void ingresarDescripcion(String descripcion) {
     * inputDescripcion.click();
     * inputDescripcion.clear();
     * inputDescripcion.sendKeys(descripcion);
     * }
     */
    private WebElement tarjetaCategoria(String nombre) {
        return driver.findElement(By.xpath(
                "//ion-card[.//ion-card-title[contains(normalize-space(), '" + nombre + "')]]"));
    }

    public void ingresarNombre(String nombre) {
        safeClick.labelHideInput(inputNombre, nombre);
    }

    public void ingresarDescripcion(String descripcion) {
        safeClick.labelHideInput(inputDescripcion, descripcion);
    }

    public void confirmarCreacion() {
        safeClick.safeClick(botonGuardar);
    }

    public void clickEditar(String nombreCategoria) {
        WebElement card = tarjetaCategoria(nombreCategoria);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        safeClick.safeClick(card);

        WebElement botonEditar = driver.findElement(By.xpath("//ion-button[normalize-space()='EDITAR']"));
        safeClick.safeClick(botonEditar);
    }

    public void confirmarEdicion() {
        WebElement botonGuardar = driver.findElement(By.xpath("//ion-button[normalize-space()='Guardar']"));
        safeClick.safeClick(botonGuardar);
    }

    public void takeScreenshot(String nombre) {
        utilsScreen.takeScreenshot(driver, nombre);
    }
}
