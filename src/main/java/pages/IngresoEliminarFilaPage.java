package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IngresoEliminarFilaPage {

    private WebDriver driver;

    public IngresoEliminarFilaPage(WebDriver driver) {
        this.driver = driver;
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

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-detalle-ingreso/ion-content/ion-grid/ion-row[2]/ion-col[2]/ion-card")
    private WebElement primeraFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-editar-fila-ingresos/ion-content/ion-fab/ion-fab-button")
    private WebElement botonEliminarFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-action-sheet/div[2]/div/div[1]/button")
    private WebElement botonEliminarFinal;

    public void hacerLogin(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        botonLogin.click();
    }

    public void abrirPrimerIngreso() {
        primerIngreso.click();
    }

    public void abrirPrimeraFila() {
        primeraFila.click();
    }

    public void eliminarFila() {
        botonEliminarFila.click();
    }

    public void eliminarFinal(){
        botonEliminarFinal.click();
    }
}
