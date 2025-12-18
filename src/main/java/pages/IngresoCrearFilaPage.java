package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IngresoCrearFilaPage {

    private WebDriver driver;

    public IngresoCrearFilaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//ion-card)[1]//div")
    private WebElement primerIngreso;

    @FindBy(xpath = "//ion-fab-button")
    private WebElement botonNuevaFila;

    public void abrirPrimerIngreso() {
        primerIngreso.click();
    }

    public void crearNuevaFila() {
        botonNuevaFila.click();
    }

    public boolean terminaEn19() {
        return driver.getCurrentUrl().endsWith("/19");
    }
}
