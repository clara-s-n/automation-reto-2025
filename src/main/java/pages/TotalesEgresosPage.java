package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TotalesEgresosPage {

    private WebDriver driver;

    public TotalesEgresosPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ion-row[.//*[normalize-space()='TOTAL']]//ion-col[4]//strong")
    private WebElement saldoEgresos;

    @FindBy(xpath = "//ion-row[.//*[normalize-space()='TOTAL']]//ion-col[3]//strong")
    private WebElement pagosEgresos;

    @FindBy(xpath = "//ion-row[.//*[normalize-space()='TOTAL']]//ion-col[2]//strong")
    private WebElement totalEgresos;

    @FindBy(css = "button-native")
    private WebElement atrasButton;

    public void clickAtrasButton() {
        atrasButton.click();
    }

    public double getPagosEgresos() {
        return pasarADouble(pagosEgresos.getText());
    }

    public double getSaldoEgresos() {
        return pasarADouble(saldoEgresos.getText());
    }

    public double getTotalEgresos() {
        return pasarADouble(totalEgresos.getText());
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

    public void scrollHastaTotal() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                totalEgresos);
    }

}
