/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
 * Descripción: Page Object para la sección Usuarios
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;
import utils.utilsScreen;

import java.time.Duration;

public class ExpoUsuariosPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    // Botón flotante para crear usuario
    @FindBy(xpath = "//ion-fab-button[.//ion-icon[@name='person-add-outline']]")
    private WebElement botonCrearUsuario;
    // Toggle de Administrador
    @FindBy(xpath = "//ion-toggle[@formcontrolname='isAdmin']")
    private WebElement toggleAdministrador;

    // Inputs del formulario
    @FindBy(xpath = "//*[@id=\"ion-input-0\"]")
    private WebElement inputNombre;

    @FindBy(xpath = "//*[@id=\"ion-input-1\"]")
    private WebElement inputEmail;

    @FindBy(xpath = "//*[@id=\"ion-input-2\"]")
    private WebElement inputPassword;

    @FindBy(xpath = "//ion-input[@formcontrolname='password']//input")
    private WebElement inputNuevaPassword;

    @FindBy(xpath = "//ion-button[contains(normalize-space(), 'Crear')]\r\n" + //
            "")
    private WebElement botonConfirmar;

    @FindBy(xpath = "//ion-button[contains(normalize-space(), 'Guardar')]\r\n" + //
            "")
    private WebElement botonConfirmarEdicion;

    @FindBy(xpath = "//ion-button[contains(normalize-space(), 'Eliminar') or contains(normalize-space(), 'Confirmar')]")
    private WebElement botonConfirmarBorrado;

    @FindBy(xpath = "//ion-fab-button[.//ion-icon[@name='person-remove-outline']]")
    private WebElement botonBorrarUsuario;

    public ExpoUsuariosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void clickCrearUsuario() {
        safeClick.safeClick(botonCrearUsuario);
    }

    public void ingresarNombre(String nombre) {
        inputNombre.clear();
        inputNombre.sendKeys(nombre);
    }

    public void ingresarEmail(String email) {
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void ingresarPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void confirmarCreacion() {
        safeClick.safeClick(botonConfirmar);
    }

    public void takeScreenshot(String nombre) {
        utilsScreen.takeScreenshot(driver, nombre);
    }

    public void activarAdministrador() {
        safeClick.safeClick(toggleAdministrador);
    }

    // Obtener tarjeta por nombre
    private WebElement tarjetaUsuario(String nombre) {
        return driver
                .findElement(By.xpath("//ion-card[.//ion-card-title[contains(normalize-space(), '" + nombre + "')]]"));
    }

    // Editar usuario
    // Toca la card del usuario para abrir el detalle y luego clic en Editar
    public void clickEditar(String nombre) {
        WebElement card = tarjetaUsuario(nombre);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        safeClick.safeClick(card);
        WebElement botonEditar = driver.findElement(
                By.xpath("//ion-button[contains(normalize-space(), 'Editar')]"));

        safeClick.safeClick(botonEditar);
    }

    public void confirmarEdicion() {
        safeClick.safeClick(botonConfirmarEdicion);
    }

    public void clickBorrar(String nombre) {
        WebElement card = tarjetaUsuario(nombre);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        safeClick.safeClick(card);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        safeClick.safeClick(botonBorrarUsuario);
    }

    public void confirmarBorrado() {
        WebElement botonEliminar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(@class,'alert-button-role-destructive')]")));
        safeClick.safeClick(botonEliminar);
    }

    // Borrar usuario

    /*
     * public void clickBorrar(String nombre) {
     * WebElement card = tarjetaUsuario(nombre);
     * ((JavascriptExecutor)
     * driver).executeScript("arguments[0].scrollIntoView(true);", card);
     * safeClick.safeClick(card);
     * WebElement botonBorrar = wait.until(
     * ExpectedConditions.visibilityOfElementLocated(By.
     * xpath("//button[@aria-label='Eliminar usuario']")));
     * safeClick.safeClick(botonBorrar);
     * }
     */

    public void ingresarNuevaPassword(String nuevaPassword) {
        inputNuevaPassword.clear();
        inputNuevaPassword.sendKeys(nuevaPassword);
    }

    /*
     * public void confirmarBorrado() {
     * WebElement botonEliminar = driver.findElement(By.xpath(
     * "//button[.//span[normalize-space()='Eliminar']]"));
     * safeClick.safeClick(botonEliminar);
     * }
     */
}