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

public class IngresoCrearFilaPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public IngresoCrearFilaPage(WebDriver driver) {
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
            // Intentar selector alternativo
            cards = driver.findElements(By.cssSelector("ion-card"));
        }
        if (cards.isEmpty()) {
            throw new RuntimeException("No se encontraron planillas de ingreso");
        }
        wait.until(ExpectedConditions.elementToBeClickable(cards.get(0)));
        safeClick.safeClick(cards.get(0));
    }

    public void crearNuevaFila() {
        // Esperar y hacer click en el FAB button
        WebElement fab = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("ion-fab-button")));
        safeClick.safeClick(fab);
    }

    public void completarFormulario(String fila, String desc, Integer monto) throws InterruptedException {
        // Esperar a que el formulario cargue
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ion-input")));
        Thread.sleep(500);

        java.util.List<WebElement> inputs = driver.findElements(By.cssSelector("ion-input input"));

        if (inputs.size() >= 1) {
            inputs.get(0).clear();
            inputs.get(0).sendKeys(fila);
        }

        if (inputs.size() >= 2) {
            inputs.get(1).clear();
            inputs.get(1).sendKeys(desc);
        }

        // Buscar campo precio (puede ser el 4to input)
        if (inputs.size() >= 4) {
            inputs.get(3).click();
            inputs.get(3).clear();
            inputs.get(3).sendKeys(monto.toString());
        }

        // Seleccionar empresa
        try {
            WebElement selectEmpresa = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("ion-select")));
            safeClick.safeClick(selectEmpresa);
            Thread.sleep(1000);

            // Seleccionar primera empresa disponible
            WebElement empresaOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("ion-select-popover ion-item, ion-popover ion-item")));
            safeClick.safeClick(empresaOption);
        } catch (Exception e) {
            System.out.println("WARN: No se pudo seleccionar empresa: " + e.getMessage());
        }

        Thread.sleep(500);

        // Guardar
        try {
            WebElement guardar = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//ion-button[contains(translate(., 'GUARDAR', 'guardar'), 'guardar')]")));
            safeClick.safeClick(guardar);
        } catch (Exception e) {
            // Fallback
            WebElement guardar = driver.findElement(By.cssSelector("ion-footer ion-button"));
            safeClick.safeClick(guardar);
        }
    }
}
