package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;

import java.time.Duration;

public class EgresosEditarEmpresaPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public EgresosEditarEmpresaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
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
        wait.until(ExpectedConditions.elementToBeClickable(tabEmpresas));
        safeClick.safeClick(tabEmpresas);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(primeraEmpresa));
        safeClick.safeClick(primeraEmpresa);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.elementToBeClickable(botonEditar));
        safeClick.safeClick(botonEditar);
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOf(inputCI));
        inputCI.clear();
        inputCI.sendKeys(ci);

        wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
        safeClick.safeClick(botonGuardar);
    }
}

