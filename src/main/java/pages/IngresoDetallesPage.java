package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;

import java.time.Duration;

public class IngresoDetallesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public IngresoDetallesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//ion-button[@type='submit'] | //form//ion-button")
    private WebElement botonLogin;

    public void hacerLogin(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        safeClick.safeClick(botonLogin);
    }

    public void abrirPrimerIngreso() {
        // Esperar a que haya cards y seleccionar la primera
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ion-card")));
        java.util.List<WebElement> cards = driver.findElements(By.cssSelector("ion-content ion-card"));
        if (cards.isEmpty()) {
            cards = driver.findElements(By.cssSelector("ion-card"));
        }
        if (cards.isEmpty()) {
            throw new RuntimeException("No se encontraron planillas de ingreso");
        }
        wait.until(ExpectedConditions.elementToBeClickable(cards.get(0)));
        safeClick.safeClick(cards.get(0));
    }

    public boolean estaEnDetalle() {
        try {
            // Verificar múltiples indicadores de estar en detalle
            // 1. URL contiene detalle o cambió
            String url = driver.getCurrentUrl();
            if (url.contains("detalle") || url.contains("fila")) {
                return true;
            }

            // 2. Hay un título en el header
            try {
                WebElement titulo = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("ion-header ion-title, ion-toolbar ion-title")));
                return titulo.isDisplayed();
            } catch (Exception e) {
                // Continue
            }

            // 3. Hay contenido de detalle (FAB button para agregar fila)
            try {
                WebElement fab = driver.findElement(By.cssSelector("ion-fab-button"));
                return fab.isDisplayed();
            } catch (Exception e) {
                // Continue
            }

            // 4. Hay ion-content con contenido
            WebElement content = driver.findElement(By.cssSelector("ion-content"));
            return content.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}
