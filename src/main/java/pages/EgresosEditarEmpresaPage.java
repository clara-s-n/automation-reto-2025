package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EgresosEditarEmpresaPage {

    private WebDriver driver;

    public EgresosEditarEmpresaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-login/ion-content/div/ion-card/ion-card-content/form/ion-button")
    private WebElement botonLogin;

    @FindBy(xpath = "//ion-tab-button[@tab='empresas']")
    private WebElement tabEmpresas;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-empresas/ion-content/ion-grid/ion-row/ion-col[1]/ion-card")
    private WebElement primeraEmpresa;

    @FindBy(xpath = "//ion-button[.//text()[normalize-space()='Editar']]")
    private WebElement botonEditar;

    @FindBy(xpath = "//*[@id=\"ion-input-6\"]")
    private WebElement inputCI;

    @FindBy(xpath = "//ion-button[.//text()[normalize-space()='Guardar']]")
    private WebElement botonGuardar;

    public void login(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        botonLogin.click();
    }

    public void editarCI(String ci) throws InterruptedException {
        tabEmpresas.click();
        Thread.sleep(4000);

        primeraEmpresa.click();
        Thread.sleep(4000);

        botonEditar.click();
        Thread.sleep(3000);

        inputCI.clear();
        inputCI.sendKeys(ci);
        botonGuardar.click();
    }
}
