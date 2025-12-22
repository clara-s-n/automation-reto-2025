package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;

import java.time.Duration;

public class EgresosCrearEmpresaPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public EgresosCrearEmpresaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmailLogin;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPasswordLogin;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-login/ion-content/div/ion-card/ion-card-content/form/ion-button")
    private WebElement botonLogin;

    @FindBy(xpath = "//ion-tab-button[@tab='empresas']")
    private WebElement tabEmpresas;

    @FindBy(xpath = "//ion-fab-button")
    private WebElement botonCrear;

    // Selectores más robustos usando formControlName o placeholder
    @FindBy(xpath = "//ion-input[@formcontrolname='nombre']//input | //ion-input[contains(@placeholder,'Nombre') or contains(@placeholder,'nombre')]//input")
    private WebElement inputNombre;

    @FindBy(xpath = "//ion-input[@formcontrolname='email']//input | //ion-input[contains(@placeholder,'Email') or contains(@placeholder,'email')]//input")
    private WebElement inputEmail;

    @FindBy(xpath = "//ion-input[@formcontrolname='razon_social']//input | //ion-input[contains(@placeholder,'Razón') or contains(@placeholder,'razon')]//input")
    private WebElement inputRazonSocial;

    @FindBy(xpath = "//ion-input[@formcontrolname='rut']//input | //ion-input[contains(@placeholder,'RUT') or contains(@placeholder,'rut')]//input")
    private WebElement inputRUT;

    @FindBy(xpath = "//ion-input[@formcontrolname='ci']//input | //ion-input[contains(@placeholder,'CI') or contains(@placeholder,'ci') or contains(@placeholder,'Cédula')]//input")
    private WebElement inputCI;

    @FindBy(xpath = "//ion-input[@formcontrolname='nombre_contacto']//input | //ion-input[contains(@placeholder,'contacto') or contains(@placeholder,'Contacto')]//input")
    private WebElement inputNombreContacto;

    @FindBy(xpath = "//ion-input[@formcontrolname='telefono_contacto']//input | //ion-input[contains(@placeholder,'Teléfono') or contains(@placeholder,'telefono')]//input")
    private WebElement inputTelefonoContacto;

    @FindBy(xpath = "//ion-button[contains(.,'Guardar') or contains(.,'guardar')]")
    private WebElement botonGuardar;

    public void login(String email, String password) {
        inputEmailLogin.sendKeys(email);
        inputPasswordLogin.sendKeys(password);
        botonLogin.click();
    }

    public void crearEmpresa() throws InterruptedException {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        
        // Navegar a empresas
        wait.until(ExpectedConditions.elementToBeClickable(tabEmpresas));
        safeClick.safeClick(tabEmpresas);
        Thread.sleep(3000);

        // Buscar y hacer clic en el botón crear (FAB button)
        wait.until(ExpectedConditions.elementToBeClickable(botonCrear));
        js.executeScript("arguments[0].click();", botonCrear);
        Thread.sleep(3000);

        // Esperar a que aparezca cualquier input en el formulario
        wait.until(ExpectedConditions.presenceOfElementLocated(
            org.openqa.selenium.By.cssSelector("ion-input")));
        Thread.sleep(1000);

        // En Ionic, necesitamos usar JavaScript para interactuar con los inputs
        java.util.List<WebElement> ionInputs = driver.findElements(
            org.openqa.selenium.By.cssSelector("ion-input"));
        
        String[] valores = {"Empresa Test", "empresa@test.com", "Empresa Test SA", 
                           "123456780019", "45678912", "Juan Pérez", "099123456"};
        
        for (int i = 0; i < Math.min(ionInputs.size(), valores.length); i++) {
            WebElement ionInput = ionInputs.get(i);
            // Hacer scroll al elemento si es necesario
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ionInput);
            Thread.sleep(200);
            
            // Usar JavaScript para establecer el valor en ion-input
            js.executeScript("arguments[0].value = arguments[1];", ionInput, valores[i]);
            
            // También intentar establecer en el input nativo si existe
            try {
                WebElement nativeInput = ionInput.findElement(org.openqa.selenium.By.cssSelector("input"));
                js.executeScript("arguments[0].value = arguments[1];", nativeInput, valores[i]);
                // Disparar evento de input para que Angular/Ionic detecte el cambio
                js.executeScript("arguments[0].dispatchEvent(new Event('input', {bubbles: true}));", nativeInput);
                js.executeScript("arguments[0].dispatchEvent(new Event('ionChange', {bubbles: true}));", ionInput);
            } catch (Exception e) {
                // Si no encuentra input nativo, intentar solo con ionInput
                js.executeScript("arguments[0].dispatchEvent(new Event('ionChange', {bubbles: true}));", ionInput);
            }
            Thread.sleep(100);
        }

        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
        js.executeScript("arguments[0].click();", botonGuardar);
    }
}

