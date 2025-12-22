/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
 * Descripción: Test para editar un usuario existente
 */
package automation;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import pages.ExpoAdministrationPage;
import pages.ExpoLoginPage;
import pages.ExpoUsuariosPage;

public class ExpoEditionUsuarioTest extends BaseTest {

    // AVISO, TEST SIN TERMINAR, NO FUE TESTEADO EN LA PAGINA

    private ExpoUsuariosPage expoUsuariosPage;
    private ExpoAdministrationPage administrationPage;
    private ExpoLoginPage expoLoginPage;
    /*
     * test editarUsuarioTest verifica la edición de un usuario existente.
     */

    @Before
    public void setUpEdition() throws InterruptedException {
        expoUsuariosPage = new ExpoUsuariosPage(driver);
        administrationPage = new ExpoAdministrationPage(driver);

        expoLoginPage = new ExpoLoginPage(driver);
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        expoLoginPage = new ExpoLoginPage(driver);
        // Login antes de cada test
        Thread.sleep(1000);
        expoLoginPage.ingresarEmail("ana@agraria.com");
        expoLoginPage.ingresarPassword("Contraseña1");
        Thread.sleep(1000);
        expoLoginPage.clickLogin();
        Thread.sleep(1000);
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
            String nuevaContraseña = "pruebacontraseña";
            Thread.sleep(1000);
            expoUsuariosPage.clickEditar(nombreOriginal);
            Thread.sleep(1000);

            expoUsuariosPage.ingresarNombre(nuevoNombre);
            expoUsuariosPage.ingresarNuevaPassword(nuevaContraseña);
            Thread.sleep(1000);
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
}
