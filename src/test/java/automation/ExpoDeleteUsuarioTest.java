/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-18
 * Descripción: Test para eliminar un usuario existente
 */
package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.utilsScreen;
import pages.ExpoAdministrationPage;
import pages.ExpoLoginPage;
import pages.ExpoUsuariosPage;

public class ExpoDeleteUsuarioTest {

    private WebDriver driver;
    private ExpoUsuariosPage expoUsuariosPage;
    private ExpoAdministrationPage administrationPage;
    private ExpoLoginPage expoLoginPage;

    @Before
    public void setUp() throws InterruptedException {
        driver = DriverFactory.getDriver("edge");
        utilsScreen.maximizeWindow(driver);

        expoUsuariosPage = new ExpoUsuariosPage(driver);
        administrationPage = new ExpoAdministrationPage(driver);
        expoLoginPage = new ExpoLoginPage(driver);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);
        expoLoginPage.ingresarEmail("alejandro@agraria.com");
        expoLoginPage.ingresarPassword("Contraseña1");
        expoLoginPage.clickLogin();
        Thread.sleep(1000);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
        administrationPage.clickUsuarios();
        Thread.sleep(1000);
    }

    @Test
    public void borrarUsuarioTest() throws Exception {
        try {
            String nombre = "usuarioEditadoAutomatico";
            expoUsuariosPage.clickBorrar(nombre);
            Thread.sleep(1000);
            expoUsuariosPage.confirmarBorrado();
            Thread.sleep(2000);
            boolean eliminado = !driver.getPageSource().contains(nombre);
            Assert.assertTrue("El usuario aún aparece en la lista", eliminado);
            expoUsuariosPage.takeScreenshot("borrarUsuario_exito");
        } catch (Exception error) {
            expoUsuariosPage.takeScreenshot("borrarUsuario_error");
            throw error;
        }
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
