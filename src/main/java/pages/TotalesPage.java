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

    public void clickDesgloseIngresos() {
        wait.until(ExpectedConditions.visibilityOf(verIngresos));
        safeClick.safeClick(verIngresos);
    }

    public void clickDesgloseEgresos() {
        wait.until(ExpectedConditions.visibilityOf(verEgresos));
        safeClick.safeClick(verEgresos);
    }

    public double getBalance() {
        try {
            wait.until(ExpectedConditions.visibilityOf(balance));
            String text = balance.getText();
            if (text == null || text.trim().isEmpty()) {
                System.out.println("WARN: Balance vacío, retornando 0");
                return 0.0;
            }
            return pasarADouble(text);
        } catch (Exception e) {
            System.out.println("WARN: Error obteniendo balance: " + e.getMessage());
            return 0.0;
        }
    }

    private double pasarADouble(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(text.replace("$", "").replace(",", "").replace(" ", "").trim());
        } catch (NumberFormatException e) {
            System.out.println("WARN: No se pudo parsear '" + text + "', retornando 0");
            return 0.0;
        }
    }
}
