/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
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
import pages.ExpoUsuariosPage;

public class ExpoDeleteUsuarioTest {

    // AVISO, TEST SIN TERMINAR, NO FUE TESTEADO EN LA PAGINA

    private WebDriver driver;
    private ExpoUsuariosPage usuariosPage;
    private ExpoAdministrationPage administrationPage;

    @Before
    public void setUp() throws InterruptedException {
        driver = DriverFactory.getDriver("edge");
        utilsScreen.maximizeWindow(driver);
        administrationPage = new ExpoAdministrationPage(driver);
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");

        administrationPage.clickUsuarios();
        Thread.sleep(1500);

        usuariosPage = new ExpoUsuariosPage(driver);
    }

    /*
     * @test
     * eliminarUsuarioTest verifica la eliminación de un usuario existente.
     * QUE HACE:
     * 1. Localiza el usuario por su nombre.
     * 2. Hace clic en el botón de eliminar.
     * 3. Confirma la eliminación.
     * 4. Verifica que el usuario ya no aparezca en la lista.
     */
    @Test
    public void eliminarUsuarioTest() throws Exception {
        try {
            String nombre = "usuarioEditado";

            usuariosPage.clickBorrar(nombre);
            Thread.sleep(1500);

            usuariosPage.confirmarBorrado();
            Thread.sleep(1500);

            boolean eliminado = !driver.getPageSource().contains(nombre);
            Assert.assertTrue("El usuario no fue eliminado", eliminado);

            usuariosPage.takeScreenshot("borrarUsuario_exito");

        } catch (Exception error) {
            usuariosPage.takeScreenshot("borrarUsuario_error");
            throw error;
        }
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
