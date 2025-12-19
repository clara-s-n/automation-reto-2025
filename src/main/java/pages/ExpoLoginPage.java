/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-18
 * Descripción: Page Object para la pantalla de Login
 */
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;
import utils.utilsScreen;

import java.time.Duration;

public class ExpoLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public ExpoLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ion-button[.//ion-icon[@name='log-out']]")
    private WebElement botonLogout;

    @FindBy(xpath = "//ion-input[@formcontrolname='email']//input")
    private WebElement inputEmail;

    @FindBy(xpath = "//ion-input[@formcontrolname='password']//input")
    private WebElement inputPassword;

    @FindBy(xpath = "//ion-button[contains(normalize-space(), 'Iniciar Sesión')]")
    private WebElement botonLogin;

    public void ingresarEmail(String email) {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.clear();
        inputEmail.sendKeys(email);
    }

    public void ingresarPassword(String password) {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void clickLogin() {
        safeClick.safeClick(botonLogin);
    }

    public void clickLogout() {
        safeClick.safeClick(botonLogout);
    }
}
