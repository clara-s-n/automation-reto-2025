package automation.funcionalidades;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Assert;
import pages.ExpoCategoryPage;
import pages.ExpoLoginPage;
import pages.ExpoAdministrationPage;

public class ExpoCategoryTest extends BaseTest {

    private ExpoLoginPage loginPage;
    private ExpoAdministrationPage administrationPage;
    private ExpoCategoryPage categoryPage;

    @Before
    public void setUpCategory() throws InterruptedException {
        loginPage = new ExpoLoginPage(driver);
        administrationPage = new ExpoAdministrationPage(driver);
        categoryPage = new ExpoCategoryPage(driver);
        Thread.sleep(1000);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);
        loginPage.ingresarEmail("ana@agraria.com");
        loginPage.ingresarPassword("Contraseña1");
        loginPage.clickLogin();
        Thread.sleep(1000);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
        administrationPage.clickCategorias();
        Thread.sleep(1000);
    }

    @Test
    public void crearCategoriaTest() throws Exception {
        try {
            String nombre = "CategoriaTestAutomatico";
            String descripcion = "Descripción prueba";

            categoryPage.clickCrearCategoria();
            categoryPage.ingresarNombre(nombre);
            categoryPage.ingresarDescripcion(descripcion);
            categoryPage.confirmarCreacion();
            Thread.sleep(1000);

            Assert.assertTrue(driver.getPageSource().contains(nombre));
            categoryPage.takeScreenshot("crearCategoria_exito");
        } catch (Exception e) {
            categoryPage.takeScreenshot("crearCategoria_error");
            throw e;
        }
    }

    // SIN TESTEAR, NO FUNCIONA
    @Ignore("Test deshabilitado - Funcionalidad no implementada correctamente en la aplicación")
    @Test
    public void editarCategoriaTest() throws Exception {
        try {
            String nombreOriginal = "CategoriaTestAutomatico";
            String nombreEditado = "CategoriaEditadaAutomatico";
            String descripcionEditada = "Descripción modificada";

            categoryPage.clickEditar(nombreOriginal);

            categoryPage.ingresarNombre(nombreEditado);
            categoryPage.ingresarDescripcion(descripcionEditada);
            categoryPage.confirmarEdicion();
            Thread.sleep(1000);
            Assert.assertTrue(driver.getPageSource().contains(nombreEditado));
            categoryPage.takeScreenshot("editarCategoria_exito");
        } catch (Exception e) {
            categoryPage.takeScreenshot("editarCategoria_error");
            throw e;
        }
    }
}
