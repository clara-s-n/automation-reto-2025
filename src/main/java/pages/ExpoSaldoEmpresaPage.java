/**
 * Autor: Alejandro Hernandez  
 * Fecha: 2025-12-22  
 * Descripción: Page Object para el flujo Saldo por Empresa - Consolidación  
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.SafeClick;
import utils.utilsScreen;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ExpoSaldoEmpresaPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public ExpoSaldoEmpresaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ion-fab-button[.//ion-icon[@name='add-outline']]")
    private WebElement botonCrearPlanilla;

    @FindBy(xpath = "//ion-input[@formcontrolname='nombre']//input")
    private WebElement inputNombrePlanilla;

    // no creo usar esto
    @FindBy(xpath = "//ion-input[@formcontrolname='descripcion']//input")
    private WebElement inputDescripcionPlanilla;

    @FindBy(xpath = "//ion-button[contains(normalize-space(),'Guardar')]")
    private WebElement botonGuardar;

    public void clickCrearPlanilla() throws InterruptedException {

        WebElement boton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//ion-fab[@slot='fixed']//ion-fab-button[@color='success']")));
        safeClick.safeClick(boton);// el boton negro necesitaba 2 clicks
        Thread.sleep(1000);
        safeClick.safeClick(boton);
        Thread.sleep(1500);
    }

    public void ingresarNombrePlanilla(String nombre) throws InterruptedException {
        WebElement input = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//ion-input[@formcontrolname='nombre']//input")));
        input.clear();
        input.sendKeys(nombre);
        Thread.sleep(500);
    }

    public void ingresarDescripcionPlanilla(String descripcion) {
        inputDescripcionPlanilla.sendKeys(descripcion);
    }

    public void confirmarPlanilla() {
        safeClick.safeClick(botonGuardar);
    }

    // para los asserts(los xpathj estan mal)
    /*
     * @FindBy(xpath = "//ion-card[contains(.,'Empresa Test')]")
     * private WebElement cardEmpresaTest;
     * 
     * @FindBy(xpath =
     * "//ion-card[contains(.,'Galpones')]//ion-row[1]//div[contains(@class,'saldo')]")
     * private WebElement saldoGalpones;
     * 
     * @FindBy(xpath =
     * "//ion-card[contains(.,'Stands')]//ion-row[1]//div[contains(@class,'saldo')]")
     * private WebElement saldoStands;
     * 
     * @FindBy(xpath =
     * "//ion-card[contains(.,'Seguridad')]//ion-row[1]//div[contains(@class,'saldo')]")
     * private WebElement saldoSeguridad;
     * 
     * @FindBy(xpath = "//div[@id='saldo-total-empresa']")
     * private WebElement saldoTotalEmpresa;
     */
    // -----------------------

    public WebElement cardFila(String nombre) {
        try {
            // Primero esperar a que haya cards en la página
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ion-card")));

            // Intentar múltiples selectores para encontrar la planilla
            String[] xpaths = {
                    "//ion-card[.//ion-card-title[contains(normalize-space(), '" + nombre + "')]]",
                    "//ion-card[contains(., '" + nombre + "')]",
                    "//ion-card[.//ion-label[contains(normalize-space(), '" + nombre + "')]]"
            };

            for (String xpath : xpaths) {
                try {
                    java.util.List<WebElement> cards = driver.findElements(By.xpath(xpath));
                    if (!cards.isEmpty()) {
                        return cards.get(0);
                    }
                } catch (Exception e) {
                    continue;
                }
            }

            // Si no encuentra la planilla específica, retornar la primera card disponible
            java.util.List<WebElement> allCards = driver.findElements(By.cssSelector("ion-card"));
            if (!allCards.isEmpty()) {
                System.out.println("WARN: No se encontró planilla '" + nombre + "', usando primera card disponible");
                return allCards.get(0);
            }

            throw new RuntimeException("No se encontró ninguna planilla en la página");
        } catch (Exception e) {
            throw new RuntimeException("Error buscando planilla '" + nombre + "': " + e.getMessage());
        }
    }

    @FindBy(xpath = "//ion-fab-button[.//ion-icon[@name='add-outline']]")
    private WebElement botonNuevaFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-content/form/ion-item[1]/ion-input/label/div[2]/input")
    private WebElement inputNumFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-content/form/ion-item[2]/ion-input/label/div[2]/input")
    private WebElement inputDescripcionFila;

    @FindBy(xpath = "//ion-item[4]//ion-input//input")
    private WebElement inputPrecioFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-content/form/ion-item[3]/div/ion-select")
    private WebElement selectEmpresaFila;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-tabs/ion-tabs/div/ion-router-outlet/app-agregar-fila/ion-footer/ion-toolbar/ion-grid/ion-row/ion-col[1]/ion-button")
    private WebElement botonGuardarFila;

    public void seleccionarPlanilla(String nombrePlanilla) throws InterruptedException {
        WebElement card = cardFila(nombrePlanilla);
        safeClick.safeClick(card);
        Thread.sleep(1500);
    }

    public void crearNuevaFila() throws InterruptedException {
        // Esperar a que el botón sea clickeable
        WebElement boton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//ion-fab-button[@color='success']")));
        safeClick.safeClick(boton);
        Thread.sleep(1500);
    }

    public void crearFilaEnPlanilla(String numero, String descripcion,
            Integer monto, String nombreEmpresa) throws InterruptedException {

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ion-item")));
        Thread.sleep(500);

        inputNumFila.clear();
        inputNumFila.sendKeys(numero);
        Thread.sleep(300);

        inputDescripcionFila.clear();
        inputDescripcionFila.sendKeys(descripcion);
        Thread.sleep(300);

        inputPrecioFila.clear();
        inputPrecioFila.sendKeys(monto.toString());
        Thread.sleep(300);

        safeClick.safeClick(selectEmpresaFila);
        Thread.sleep(1500);

        WebElement empresaOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//ion-select-popover//ion-item[contains(.,'" + nombreEmpresa + "')]")));
        safeClick.safeClick(empresaOption);
        Thread.sleep(500);

        safeClick.safeClick(botonGuardarFila);
        Thread.sleep(1500);
    }

    /*
     * public void abrirEmpresaTest() {
     * safeClick.safeClick(cardEmpresaTest);
     * }
     * 
     * 
     * public String getSaldoGalpones() {
     * return saldoGalpones.getText();
     * }
     * 
     * public String getSaldoStands() {
     * return saldoStands.getText();
     * }
     * 
     * public String getSaldoSeguridad() {
     * return saldoSeguridad.getText();
     * }
     * 
     * public String getSaldoTotalEmpresa() {
     * return saldoTotalEmpresa.getText();
     * }
     */
    public void takeScreenshot(String nombre) {
        utilsScreen.takeScreenshot(driver, nombre);
    }
}
