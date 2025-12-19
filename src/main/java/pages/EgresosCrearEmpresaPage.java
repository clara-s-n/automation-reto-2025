package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EgresosCrearEmpresaPage {

    private WebDriver driver;

    public EgresosCrearEmpresaPage(WebDriver driver) {
        this.driver = driver;
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

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-empresas/ion-content/ion-fab/ion-fab-button")
    private WebElement botonCrear;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[1]/ion-col[1]/ion-input/label/div[3]/input")
    private WebElement inputNombre;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[1]/ion-col[2]/ion-input/label/div[3]/input")
    private WebElement inputEmail;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[1]/ion-col[3]/ion-input/label/div[3]/input")
    private WebElement inputRazonSocial;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[1]/ion-col[4]/ion-input/label/div[3]/input")
    private WebElement inputRUT;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[1]/ion-col[5]/ion-input/label/div[3]/input")
    private WebElement inputCI;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[1]/ion-col[7]/ion-input/label/div[3]/input")
    private WebElement inputNombreContacto;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[1]/ion-col[8]/ion-input/label/div[3]/input")
    private WebElement inputTelefonoContacto;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-crear-empresa/ion-content/app-empresa-form/form/ion-grid/ion-row[2]/ion-col[1]/ion-button")
    private WebElement botonGuardar;

    public void login(String email, String password) {
        inputEmailLogin.sendKeys(email);
        inputPasswordLogin.sendKeys(password);
        botonLogin.click();
    }

    public void crearEmpresa() throws InterruptedException {
        tabEmpresas.click();
        Thread.sleep(4000);

        botonCrear.click();
        Thread.sleep(4000);

        inputNombre.sendKeys("Empresa Test");
        inputEmail.sendKeys("empresa@test.com");
        inputRazonSocial.sendKeys("Empresa Test SA");
        inputRUT.sendKeys("123456780019");
        inputCI.sendKeys("45678912");
        inputNombreContacto.sendKeys("Juan Pérez");
        inputTelefonoContacto.sendKeys("099123456");

        botonGuardar.click();
    }
}
