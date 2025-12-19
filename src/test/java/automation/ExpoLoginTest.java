package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.utilsScreen;
import pages.ExpoLoginPage;

import static org.junit.Assert.assertTrue;

public class ExpoLoginTest {

    private WebDriver driver;
    private ExpoLoginPage loginPage;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver("edge");
        utilsScreen.maximizeWindow(driver);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        loginPage = new ExpoLoginPage(driver);

        // Login
        loginPage.ingresarEmail("alejandro@agraria.com");
        loginPage.ingresarPassword("Contraseña1");
        loginPage.clickLogin();
    }

    @Test
    public void loginTest() throws InterruptedException {
        Thread.sleep(2000); // esperar carga

        // Validar que entramos a ingresos
        boolean enIngresos = driver.getPageSource().contains("Ingresos");
        assertTrue("No se accedió a Ingresos", enIngresos);

        utilsScreen.takeScreenshot(driver, "login_admin_exito");
    }

    @Test
    public void logoutTest() throws InterruptedException {
        /*
         * // Validar que entramos a ingresos
         * boolean enIngresos = driver.getPageSource().contains("Ingresos");
         * assertTrue("No se accedió a Ingresos", enIngresos);
         */
        Thread.sleep(2000);
        loginPage.clickLogout();

        Thread.sleep(2000);
        assertTrue("No se volvió al login", driver.getCurrentUrl().contains("/login"));

        utilsScreen.takeScreenshot(driver, "logout_exito");
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
