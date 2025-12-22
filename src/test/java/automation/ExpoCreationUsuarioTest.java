/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
 * Descripción: Test de la funcionalidad Crear Usuario
 */
package automation;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import utils.utilsScreen;
import pages.ExpoUsuariosPage;
import pages.ExpoAdministrationPage;
import pages.ExpoLoginPage;

public class ExpoCreationUsuarioTest extends BaseTest {

    private ExpoUsuariosPage expoCreationUsuariosPage;
    private ExpoAdministrationPage administrationPage;
    private ExpoLoginPage expoLoginPage;

    /*
     * @before
     * setUp inicializa el WebDriver y navega a la página de administración antes de
     * cada test.
     */
    @Before
    public void setUpUsuario() throws InterruptedException {
        expoCreationUsuariosPage = new ExpoUsuariosPage(driver);
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
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
    }

    /*
     * @test
     * crearUsuarioTest verifica la creación de un nuevo usuario.
     * 1. Navega a la sección de administración y luego a usuarios.
     * 2. Hace clic en el botón para crear un nuevo usuario.
     * 3. Rellena el formulario con nombre, email y contraseña.
     * 4. Confirma la creación del usuario.
     * 5. Verifica que el nuevo usuario aparezca en la lista.
     * 6. Toma capturas de pantalla para documentar el éxito o fallo del test.
     */
    @Test
    public void crearUsuarioTest() throws Exception {
        try {
            Thread.sleep(1500);
            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
            Thread.sleep(1500);
            administrationPage.clickUsuarios();
            Thread.sleep(1500);

            expoCreationUsuariosPage.clickCrearUsuario();
            Thread.sleep(1000);

            expoCreationUsuariosPage.ingresarNombre("usuarioTestAutomatico");
            expoCreationUsuariosPage.ingresarEmail("test.user@peru.com");
            expoCreationUsuariosPage.ingresarPassword("contraseñaSegura123");
            expoCreationUsuariosPage.confirmarCreacion();

            Thread.sleep(2000);

            boolean usuarioCreado = driver.findElements(By.xpath("//*[contains(text(),'test.user@peru.com')]"))
                    .size() > 0;
            Assert.assertTrue("El usuario no aparece en la lista", usuarioCreado);

            expoCreationUsuariosPage.takeScreenshot("CrearUsuarioTest_exito");
        } catch (Exception error) {
            expoCreationUsuariosPage.takeScreenshot("CrearUsuarioTest_error");
            throw error;
        }
    }

    /*
     * @test
     * crearUsuarioConAdministradorActivoTest verifica la creación de un nuevo
     * usuario con el rol de administrador activado.
     * 1. Navega a la sección de administración y luego a usuarios.
     * 2. Hace clic en el botón para crear un nuevo usuario.
     * 3. Activa el toggle de administrador.
     * 4. Rellena el formulario con nombre, email y contraseña.
     * 5. Confirma la creación del usuario.
     * 6. Verifica que el nuevo usuario aparezca en la lista.
     * 7. Toma capturas de pantalla para documentar el éxito o fallo del test
     */

    @Test
    public void crearUsuarioConAdministradorActivoTest() throws Exception {
        try {
            Thread.sleep(1500);
            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
            Thread.sleep(1500);
            administrationPage.clickUsuarios();
            Thread.sleep(1500);

            expoCreationUsuariosPage.clickCrearUsuario();
            Thread.sleep(1000);

            expoCreationUsuariosPage.activarAdministrador();
            expoCreationUsuariosPage.ingresarNombre("AdminTestAutomatico");
            expoCreationUsuariosPage.ingresarEmail("adminUser@peru.com");
            expoCreationUsuariosPage.ingresarPassword("contraseñaSegura123");
            expoCreationUsuariosPage.confirmarCreacion();
            Thread.sleep(2000);
            boolean usuarioCreado = driver.findElements(By.xpath("//*[contains(text(),'adminUser@peru.com')]"))
                    .size() > 0;
            Assert.assertTrue("El usuario administrador no aparece en la lista", usuarioCreado);
            expoCreationUsuariosPage.takeScreenshot("CrearUsuarioAdministradorToggleTest_exito");
        } catch (Exception error) {
            expoCreationUsuariosPage.takeScreenshot("CrearUsuarioAdministradorToggleTest_error");
            throw error;
        }
    }
}
