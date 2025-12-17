/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
 * Descripción: Tests de la funcionalidad ExpoAdministration, navegación entre secciones
 */
package automation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.utilsScreen;
import pages.ExpoAdministrationPage;

public class ExpoAdministrationTest {

    private WebDriver driver;
    private ExpoAdministrationPage administrationPage;

    /*
     * @before
     * setUp inicializa el WebDriver y navega a la página de administración antes de
     * cada test.
     */
    @Before
    public void setUp() {
        driver = DriverFactory.getDriver("edge");
        utilsScreen.maximizeWindow(driver);
        administrationPage = new ExpoAdministrationPage(driver);
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
    }

    /*
     * @test
     * navegarAUsuariosTest verifica la navegación a la sección Usuarios.
     */
    @Test
    public void navegarAUsuariosTest() {
        administrationPage.clickUsuarios();
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Usuarios')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Usuarios", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_usuarios");
    }

    /*
     * navegaraACategoriasTest verifica la navegación a la sección Categorías.
     */
    @Test
    public void navegarACategoriasTest() {
        administrationPage.clickCategorias();
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Categorías')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Categorías", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_categorias");
    }
    /*
     * navergarAAniosTest verifica la navegación a la sección Años.
     */

    @Test
    public void navegarAAniosTest() {
        administrationPage.clickAnios();
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Años')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Años", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_anios");
    }

    /*
     * navegarAEgresosTest verifica la navegación a la sección Egresos.
     */
    @Test
    public void navegarAEgresosTest() {
        administrationPage.clickEgresos();
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Egresos')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Egresos", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_egresos");
    }
    /*
    navegarAPlanillasTest verifica la navegación a la sección Planillas de ingreso.
    */
    @Test
    public void navegarAPlanillasTest() {
        administrationPage.clickPlanillas();
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Planillas')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Planillas de ingreso", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_planillas");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
