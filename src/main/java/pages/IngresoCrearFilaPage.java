package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IngresoCrearFilaPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public IngresoCrearFilaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-login/ion-content/div/ion-card/ion-card-content/form/ion-button")
    private WebElement botonLogin;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-ingresos/ion-content/ion-grid/ion-row[2]/ion-col[1]/ion-card")
    private WebElement primerIngreso;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-detalle-ingreso/ion-content/ion-fab/ion-fab-button")
    private WebElement botonNuevaFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-content/form/ion-item[1]/ion-input/label/div[2]/input")
    private WebElement numFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-content/form/ion-item[2]/ion-input/label/div[2]/input")
    private WebElement descripcion;

    @FindBy(xpath = "//ion-item[4]//ion-input//input")
    private WebElement precio;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-content/form/ion-item[3]/div/ion-select")
    private WebElement selectEmpresa;

    @FindBy(xpath = "//*[@id='ion-overlay-8']//ion-item[1]")
    private WebElement empresaPrimera;

    @FindBy (xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-footer/ion-toolbar/ion-grid/ion-row/ion-col[1]/ion-button")
    private WebElement guardarFila;

    public void hacerLogin(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        botonLogin.click();
    }

    public void abrirPrimerIngreso() {
        primerIngreso.click();
    }

    public void crearNuevaFila() {
        botonNuevaFila.click();
    }

    public void completarFormulario(String fila, String desc, Integer monto) throws InterruptedException {
        numFila.clear();
        numFila.sendKeys(fila);

        descripcion.sendKeys(desc);
        
        precio.click();
        precio.sendKeys(monto.toString());

        selectEmpresa.click();
        Thread.sleep(2000);

        WebElement empresaOption = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//ion-select-popover//ion-item[contains(.,'Baiz')]")));
        empresaOption.click();


        guardarFila.click();
    }
}
