/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-17
 * Descripción: Tests de la funcionalidad ExpoAdministration, navegación entre secciones
 */
package automation;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import utils.utilsScreen;
import pages.ExpoAdministrationPage;
import pages.ExpoLoginPage;

public class ExpoAdministrationTest extends BaseTest {

    private ExpoAdministrationPage administrationPage;
    private ExpoLoginPage expoLoginPage;

    /*
     * @before
     * setUp inicializa el WebDriver y navega a la página de administración antes de
     * cada test.
     */
    @Before
    public void setUpAdmin() {
        administrationPage = new ExpoAdministrationPage(driver);
        expoLoginPage = new ExpoLoginPage(driver);
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        expoLoginPage = new ExpoLoginPage(driver);
        // Login antes de cada test
        expoLoginPage.ingresarEmail("alejandro@agraria.com");
        expoLoginPage.ingresarPassword("Contraseña1");
        expoLoginPage.clickLogin();
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
    }

    /*
     * @test
     * navegarAUsuariosTest verifica la navegación a la sección Usuarios.
     */
    @Test
    public void navegarAUsuariosTest() throws Exception {
        Thread.sleep(2000);
        administrationPage.clickUsuarios();
        Thread.sleep(2000);
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Usuarios')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Usuarios", visible);
        Thread.sleep(2000);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_usuarios");
    }

    /*
     * navegaraACategoriasTest verifica la navegación a la sección Categorías.
     */
    @Test
    public void navegarACategoriasTest() throws Exception {
        Thread.sleep(2000);
        administrationPage.clickCategorias();
        Thread.sleep(2000);
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Categorías')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Categorías", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_categorias");
    }
    /*
     * navergarAAniosTest verifica la navegación a la sección Años.
     */

    @Test
    public void navegarAAniosTest() throws Exception {
        Thread.sleep(2000);
        administrationPage.clickAnios();
        Thread.sleep(2000);
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Años')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Años", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_anios");
    }

    /*
     * navegarAEgresosTest verifica la navegación a la sección Egresos.
     */
    @Test
    public void navegarAEgresosTest() throws Exception {
        Thread.sleep(2000);
        administrationPage.clickEgresos();
        Thread.sleep(2000);
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Egresos')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Egresos", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_egresos");
    }

    /*
     * navegarAPlanillasTest verifica la navegación a la sección Planillas de
     * ingreso.
     */
    @Test
    public void navegarAPlanillasTest() throws Exception {
        Thread.sleep(2000);
        administrationPage.clickPlanillas();
        Thread.sleep(2000);
        boolean visible = driver.findElements(By.xpath("//ion-title[contains(text(),'Planillas')]")).size() > 0;
        Assert.assertTrue("No se cargó la sección Planillas de ingreso", visible);
        utilsScreen.takeScreenshot(driver, "AdministrationTest_planillas");
    }
}
