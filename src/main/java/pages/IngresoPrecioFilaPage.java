package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IngresoPrecioFilaPage {

    private WebDriver driver;

    public IngresoPrecioFilaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-detalle-ingreso/ion-content/ion-grid/ion-row[2]/ion-col[2]/ion-card")
    private WebElement entrarFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-editar-fila-ingresos/ion-content/form/ion-card")
    private WebElement detallesPrecio;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-listar-pagos/ion-content/ion-fab/ion-fab-button")
    private WebElement crearPrecio;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-pago/ion-content/form/ion-item[1]/ion-select")
    private WebElement selectEmpresa;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-pago/ion-content/form/ion-item[2]//input")
    private WebElement inputPrecio;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-pago/ion-content/form/ion-item[4]//textarea")
    private WebElement metodoDePago;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-pago/ion-content/form/ion-item[5]//textarea")
    private WebElement observacionesPago;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-pago/ion-footer/ion-toolbar/ion-grid/ion-row/ion-col[1]/ion-button")
    private WebElement botonGuardar;

    public void entrarEnFila() {
        entrarFila.click();
    }

    public void entrarDetalle(){
        detallesPrecio.click();
    }

    public void entrarEnPrecio(){
        crearPrecio.click();
    }

    public void seleccionarEmpresa(String nombreEmpresa) throws InterruptedException {
        selectEmpresa.click();
        Thread.sleep(1000);

        driver.findElement(
            By.xpath("//ion-popover//ion-item[contains(.,'" + nombreEmpresa + "')]")
        ).click();

        Thread.sleep(1000);
    }

    public void ingresarPrecio(String monto) {
        inputPrecio.clear();
        inputPrecio.sendKeys(monto);
    }

    public void ingresarMetodoDePago(String metodo) {
        metodoDePago.clear();
        metodoDePago.sendKeys(metodo);
    }

    public void ingresarObservaciones(String observaciones) {
        observacionesPago.clear();
        observacionesPago.sendKeys(observaciones);
    }

    public void guardar() {
        botonGuardar.click();
    }
}
