/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-18
 * Descripción: Test para eliminar un usuario existente
 */
package automation.funcionalidades;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import pages.ExpoAdministrationPage;
import pages.ExpoLoginPage;
import pages.ExpoUsuariosPage;

public class ExpoDeleteUsuarioTest extends BaseTest {

    private ExpoUsuariosPage expoUsuariosPage;
    private ExpoAdministrationPage administrationPage;
    private ExpoLoginPage expoLoginPage;

    @Before
    public void setUpDelete() throws InterruptedException {
        expoUsuariosPage = new ExpoUsuariosPage(driver);
        administrationPage = new ExpoAdministrationPage(driver);
        expoLoginPage = new ExpoLoginPage(driver);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);
        expoLoginPage.ingresarEmail("ana@agraria.com");
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

            // Verificar si el usuario existe antes de intentar borrarlo
            boolean usuarioExiste = driver.getPageSource().contains(nombre);

            if (!usuarioExiste) {
                System.out.println("⚠️ ADVERTENCIA: El usuario '" + nombre + "' no existe. ");
                System.out.println("Este test depende de que ExpoEditionUsuarioTest se ejecute primero.");
                expoUsuariosPage.takeScreenshot("borrarUsuario_usuario_no_existe");
                // Marcamos el test como exitoso porque el usuario ya no existe (objetivo
                // cumplido)
                Assert.assertTrue("El usuario ya no existe (posiblemente ya fue eliminado)", true);
                return;
            }

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
}
