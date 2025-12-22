package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;

import java.time.Duration;

public class TotalesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public TotalesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ion-tab-button[@tab='totales']")
    private WebElement tabTotales;

    @FindBy(xpath = "//ion-button[.//text()[contains(normalize-space(.),'Ver Desglose Ingresos')]]")
    private WebElement verIngresos;

    @FindBy(xpath = "//ion-button[contains(normalize-space(.), 'Ver Desglose Egresos')]")
    private WebElement verEgresos;

    @FindBy(xpath = "//*[normalize-space()='BALANCE GENERAL']/following::strong[1]")
    private WebElement balance;


    public void clickTotalesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(tabTotales));
        safeClick.safeClick(tabTotales);
    }

    public void clickDesgloseIngresos(){
        wait.until(ExpectedConditions.visibilityOf(verIngresos));
        safeClick.safeClick(verIngresos);
    }


    public void clickDesgloseEgresos(){
        wait.until(ExpectedConditions.visibilityOf(verEgresos));
        safeClick.safeClick(verEgresos);
    }

    public double getBalance() {
        return pasarADouble(balance.getText());
    }

    private double pasarADouble(String text) {
        return Double.parseDouble(text.replace("$", "").replace(",", "").trim());
    }
}
