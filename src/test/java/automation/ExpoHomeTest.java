/**
 * Autor: [Alejandro Hernandez]
 * Fecha: 2025-12-16
 * Descripción: Test de la funcionalidad ExpoHome, navegación entre pestañas principales
 */
package automation;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import utils.utilsScreen;
import pages.ExpoHomePage;
import pages.ExpoLoginPage;

public class ExpoHomeTest extends BaseTest {

    private ExpoHomePage expoHomePage;
    private ExpoLoginPage expoLoginPage;
    /*
     * Test de navegación entre las pestañas la página principal
     * 1. Ingresos
     * 2. Egresos
     * 3. Empresas
     * 4. Totales
     * 5. Administración
     * Cada test navega a la URL base, espera que cargue, hace click en la pestaña
     * correspondiente,
     * verifica que el encabezado de la vista sea el esperado y toma una captura de
     * pantalla.
     * 
     * Son para asegurar que se navega entre las paginas
     */

    /*
     * @Before hace la configuración inicial del WebDriver antes de cada test.
     */
    @Before
    public void setUpHome() {
        expoHomePage = new ExpoHomePage(driver);
        expoLoginPage = new ExpoLoginPage(driver);
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        expoLoginPage = new ExpoLoginPage(driver);
        // Login antes de cada test
        expoLoginPage.ingresarEmail("alejandro@agraria.com");
        expoLoginPage.ingresarPassword("Contraseña1");
        expoLoginPage.clickLogin();
        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
    }

    /*
     * @test
     * ingresosNavigationTest verifica la navegación a la pestaña Ingresos.
     */
    @Test
    public void ingresosNavigationTest() throws InterruptedException {
        // driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(2000);
        expoHomePage.clickIngresos();
        Thread.sleep(1000);
        boolean ingresosVisible = driver.findElements(By.xpath("//ion-title[normalize-space()='Ingresos']")).size() > 0;
        Assert.assertTrue("Debería mostrarse la vista de Ingresos", ingresosVisible);
        utilsScreen.takeScreenshot(driver, "ExpoHomeTest_ingresosNavigation");
    }

    /*
     * @test
     * egresosNavigationTest verifica la navegación a la pestaña Egresos.
     */
    @Test
    public void egresosNavigationTest() throws InterruptedException {
        // driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(2000);
        expoHomePage.clickEgresos();
        Thread.sleep(1000);
        boolean egresosVisible = driver.findElements(By.xpath("//ion-title[normalize-space()='Egresos']")).size() > 0;
        Assert.assertTrue("Debería mostrarse la vista de Egresos", egresosVisible);
        utilsScreen.takeScreenshot(driver, "ExpoHomeTest_egresosNavigation");
    }

    /*
     * @test
     * empresasNavigationTest verifica la navegación a la pestaña Empresas.
     */
    @Test
    public void empresasNavigationTest() throws InterruptedException {
        // driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(2000);
        expoHomePage.clickEmpresas();
        Thread.sleep(1000);
        boolean empresasVisible = driver.findElements(By.xpath("//ion-title[normalize-space()='Empresas']")).size() > 0;
        Assert.assertTrue("Debería mostrarse la vista de Empresas", empresasVisible);
        utilsScreen.takeScreenshot(driver, "ExpoHomeTest_empresasNavigation");
    }

    /*
     * @test
     * totalesNavigationTest verifica la navegación a la pestaña Totales.
     */
    @Test
    public void totalesNavigationTest() throws InterruptedException {
        // driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(4000);
        expoHomePage.clickTotales();
        Thread.sleep(1000);
        boolean totalesVisible = driver.findElements(By.xpath("//ion-title[normalize-space()='Totales']")).size() > 0;
        Assert.assertTrue("Debería mostrarse la vista de Totales", totalesVisible);
        utilsScreen.takeScreenshot(driver, "ExpoHomeTest_totalesNavigation");
    }

    /*
     * @test
     * administracionNavigationTest verifica la navegación a la pestaña
     * Administración.
     */
    @Test
    public void administracionNavigationTest() {
        // driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        try {
            Thread.sleep(3000);
            Assert.assertTrue("Debe estar en la página de Ingresos", expoHomePage.isAtHomePage());

            expoHomePage.clickAdministracionTab();
            Thread.sleep(1000);

            boolean adminHeaderPresent = driver
                    .findElements(By.xpath("//ion-title[normalize-space()='Administración']")).size() > 0;
            Assert.assertTrue("Debería mostrarse la vista de Administración", adminHeaderPresent);

            Thread.sleep(1000);
        } catch (InterruptedException error) {
            error.printStackTrace();
        } finally {
            // Captura de pantalla al final del test
            utilsScreen.takeScreenshot(driver, "ExpoHomeTest_administracionNavigation");
        }
    }
}
