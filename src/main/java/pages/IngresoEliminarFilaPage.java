package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;

import java.time.Duration;

public class IngresoEliminarFilaPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public IngresoEliminarFilaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-login/ion-content/div/ion-card/ion-card-content/form/ion-button")
    private WebElement botonLogin;

    @FindBy(xpath = "//ion-card")
    private WebElement primerIngreso;

    @FindBy(xpath = "(//ion-card)[1]")
    private WebElement primeraFila;

    @FindBy(xpath = "//ion-fab-button")
    private WebElement botonEliminarFila;

    @FindBy(xpath = "//ion-action-sheet//button[contains(.,'Eliminar') or contains(.,'eliminar') or contains(@class,'destructive')]")
    private WebElement botonEliminarFinal;

    public void hacerLogin(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        botonLogin.click();
    }

    public void abrirPrimerIngreso() {
        // Esperar a que haya cards, con timeout más largo y mejor manejo
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    org.openqa.selenium.By.cssSelector("ion-content")));
            Thread.sleep(1000); // Esperar carga de datos

            java.util.List<WebElement> cards = driver.findElements(
                    org.openqa.selenium.By.cssSelector("ion-content ion-card"));
            if (cards.isEmpty()) {
                cards = driver.findElements(
                        org.openqa.selenium.By.cssSelector("ion-card"));
            }
            if (!cards.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(cards.get(0)));
                safeClick.safeClick(cards.get(0));
            } else {
                System.out.println("WARN: No hay planillas disponibles para abrir");
            }
        } catch (Exception e) {
            System.out.println("WARN: Error abriendo primer ingreso: " + e.getMessage());
        }
    }

    public void abrirPrimeraFila() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-card")));
        java.util.List<WebElement> cards = driver.findElements(
                org.openqa.selenium.By.cssSelector("ion-card"));
        if (!cards.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(cards.get(0)));
            safeClick.safeClick(cards.get(0));
        }
    }

    public void eliminarFila() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        // Buscar el FAB button para eliminar
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-fab-button")));
        WebElement fabButton = driver.findElement(
                org.openqa.selenium.By.cssSelector("ion-fab-button"));
        wait.until(ExpectedConditions.elementToBeClickable(fabButton));
        safeClick.safeClick(fabButton);
    }

    public void eliminarFinal() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        // Buscar el botón de eliminar en el action sheet
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-action-sheet button")));
        java.util.List<WebElement> buttons = driver.findElements(
                org.openqa.selenium.By.cssSelector("ion-action-sheet button"));
        // El primer botón suele ser "Eliminar"
        if (!buttons.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(buttons.get(0)));
            safeClick.safeClick(buttons.get(0));
        }
    }
}
