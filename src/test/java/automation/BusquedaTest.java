/**
 * Autor: [Tu Nombre Aquí]
 * Fecha: 2025-12-15
 * Descripción: Ejemplo de test de búsqueda en Google para demostrar las convenciones del proyecto
 */
package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;
import utils.utilsScreen;

public class BusquedaTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Inicializar el driver usando DriverFactory
        // El DriverFactory obtiene la ruta del driver desde el archivo .env
        driver = DriverFactory.getDriver("edge");

        // Maximizar la ventana del navegador
        utilsScreen.maximizeWindow(driver);
    }

    @Test
    public void busqueda() {
        // Navegar a Google
        driver.get("https://www.google.com");

        try {
            // Localizar el campo de búsqueda usando XPath
            WebElement searchBox = driver.findElement(By.xpath("//textarea[@name='q']"));

            // Ingresar término de búsqueda
            searchBox.sendKeys("Selenium WebDriver");

            // Enviar el formulario
            searchBox.submit();

            // Esperar a que se carguen los resultados
            Thread.sleep(2000);

            // Verificar que se muestran resultados (assertion obligatoria)
            String pageTitle = driver.getTitle();
            Assert.assertTrue("El título debe contener el término de búsqueda",
                            pageTitle.contains("Selenium WebDriver"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Captura de pantalla al final del test (OBLIGATORIO)
            utilsScreen.takeScreenshot(driver, "BusquedaTest_busqueda");
        }
    }

    @After
    public void tearDown() {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}

