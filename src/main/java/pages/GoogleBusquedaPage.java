
/**
 * Autor: [Tu Nombre Aquí]
 * Fecha: 2025-12-15
 * Descripción: Page Object para la página de búsqueda de Google
 */
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleBusquedaPage {

    private WebDriver driver;

    // Localizar elementos usando XPath (según las convenciones del proyecto)
    @FindBy(xpath = "//textarea[@name='q']")
    private WebElement campoBusqueda;

    @FindBy(xpath = "//input[@name='btnK']")
    private WebElement botonBuscar;

    /**
     * Constructor
     * @param driver Instancia del WebDriver
     */
    public GoogleBusquedaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Ingresa texto en el campo de búsqueda
     * @param texto Texto a buscar
     */
    public void ingresarTextoBusqueda(String texto) {
        campoBusqueda.clear();
        campoBusqueda.sendKeys(texto);
    }

    /**
     * Hace clic en el botón de búsqueda
     */
    public void clickBotonBuscar() {
        botonBuscar.click();
    }

    /**
     * Realiza una búsqueda completa (ingresa texto y hace clic)
     * @param texto Texto a buscar
     */
    public void buscar(String texto) {
        ingresarTextoBusqueda(texto);
        campoBusqueda.submit();
    }

    /**
     * Obtiene el título de la página
     * @return Título de la página
     */
    public String obtenerTitulo() {
        return driver.getTitle();
    }
}

