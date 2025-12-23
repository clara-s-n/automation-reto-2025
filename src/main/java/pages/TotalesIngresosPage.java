package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TotalesIngresosPage {

    private WebDriver driver;

    public TotalesIngresosPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ion-row[.//*[normalize-space()='TOTAL']]//ion-col[4]//strong")
    private WebElement saldoIngresos;

    @FindBy(xpath = "//ion-row[.//*[normalize-space()='TOTAL']]//ion-col[3]//strong")
    private WebElement pagosIngresos;

    @FindBy(xpath = "//ion-row[.//*[normalize-space()='TOTAL']]//ion-col[2]//strong")
    private WebElement totalIngresos;

    @FindBy(css = "ion-back-button")
    private WebElement atrasButton;

    public void clickAtrasButton() {
        atrasButton.click();
    }

    public double getPagosIngresos() {
        return pasarADouble(pagosIngresos.getText());
    }

    public double getSaldoIngresos() {
        return pasarADouble(saldoIngresos.getText());
    }

    public double getTotalIngresos() {
        return pasarADouble(totalIngresos.getText());
    }

    private double pasarADouble(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0.0;
        }
        try {
            String dou = text.replace("$", "").replace(",", "").replace(" ", "").trim();
            if (dou.isEmpty()) {
                return 0.0;
            }
            return Double.parseDouble(dou);
        } catch (NumberFormatException e) {
            System.out.println("WARN: No se pudo parsear '" + text + "', retornando 0");
            return 0.0;
        }
    }
}
