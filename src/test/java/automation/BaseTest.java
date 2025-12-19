package automation;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.utilsScreen;

/**
 * Clase base para todos los tests
 * Utiliza DriverFactory para inicializar el WebDriver
 * El navegador se puede configurar con -Dbrowser=chrome|firefox|edge (por defecto: edge)
 */
public class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        // Obtener el navegador configurado (por defecto "edge")
        String browser = DriverFactory.getConfiguredBrowser();

        // Inicializar el driver usando DriverFactory
        driver = DriverFactory.getDriver(browser);

        // Maximizar la ventana del navegador
        utilsScreen.maximizeWindow(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
