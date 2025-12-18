package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IngresoDetallesPage {

    private WebDriver driver;

    public IngresoDetallesPage(WebDriver driver) {
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

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-header/ion-toolbar/ion-title")
    private WebElement tituloDetalle;

    public void hacerLogin(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        botonLogin.click();
    }

    public void abrirPrimerIngreso() {
        primerIngreso.click();
    }

    public boolean estaEnDetalle() {
        return tituloDetalle.isDisplayed();
    }
}
