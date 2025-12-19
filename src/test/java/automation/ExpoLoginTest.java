package automation;

import org.junit.Before;
import org.junit.Test;
import pages.ExpoLoginPage;
import utils.utilsScreen;

import static org.junit.Assert.assertTrue;

public class ExpoLoginTest extends BaseTest {

    private ExpoLoginPage loginPage;

    @Before
    public void setUpLogin() {
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
}
