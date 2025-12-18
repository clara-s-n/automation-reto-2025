/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
 * Descripción: Test para editar un usuario existente
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

public class ExpoEditionUsuarioTest {

    // AVISO, TEST SIN TERMINAR, NO FUE TESTEADO EN LA PAGINA

    private WebDriver driver;
    private ExpoUsuariosPage expoUsuariosPage;
    private ExpoAdministrationPage administrationPage;

    /*
     * test editarUsuarioTest verifica la edición de un usuario existente.
     */

    @Before
    public void setUp() throws InterruptedException {
        driver = DriverFactory.getDriver("edge");
        utilsScreen.maximizeWindow(driver);
        expoUsuariosPage = new ExpoUsuariosPage(driver);
        administrationPage = new ExpoAdministrationPage(driver);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");

        administrationPage.clickUsuarios();
        Thread.sleep(1000);
    }

    /*
     * 
     * @test
     * editarUsuarioTest verifica la edición de un usuario existente.
     * QUE HACE:
     * 1. Localiza el usuario por su nombre original.
     * 2. Hace clic en el botón de editar.
     * 3. Modifica el nombre del usuario.
     * 4. Confirma la edición.
     * 5. Verifica que el nombre del usuario se haya actualizado en la lista.
     * 
     */
    @Test
    public void editarUsuarioTest() throws Exception {
        try {
            String nombreOriginal = "usuarioTestAutomatico";
            String nuevoNombre = "usuarioEditadoAutomatico";
            Thread.sleep(1000);
            expoUsuariosPage.clickEditar(nombreOriginal);
            Thread.sleep(1000);

            expoUsuariosPage.ingresarNombre(nuevoNombre);
            expoUsuariosPage.confirmarEdicion();
            Thread.sleep(1000);

            boolean editado = driver.getPageSource().contains(nuevoNombre);
            Assert.assertTrue("El usuario no fue editado", editado);

            expoUsuariosPage.takeScreenshot("editarUsuario_exito");

        } catch (Exception error) {
            expoUsuariosPage.takeScreenshot("editarUsuario_error");
            throw error;
        }
    }

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
