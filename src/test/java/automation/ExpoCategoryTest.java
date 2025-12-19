package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import pages.ExpoCategoryPage;
import pages.ExpoLoginPage;
import pages.ExpoAdministrationPage;
import utils.DriverFactory;
import utils.utilsScreen;

public class ExpoCategoryTest {

    private WebDriver driver;
    private ExpoLoginPage loginPage;
    private ExpoAdministrationPage administrationPage;
    private ExpoCategoryPage categoryPage;

    @Before
    public void setUp() throws InterruptedException {
        driver = DriverFactory.getDriver("edge");
        utilsScreen.maximizeWindow(driver);

        loginPage = new ExpoLoginPage(driver);
        administrationPage = new ExpoAdministrationPage(driver);
        categoryPage = new ExpoCategoryPage(driver);
        Thread.sleep(1000);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);
        loginPage.ingresarEmail("alejandro@agraria.com");
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

    @After
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
