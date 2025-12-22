/**
 * Autor: Alejandro Hernandez
 * Fecha: 2025-12-22
 * Descripción: Test del flujo Saldo por Empresa - Consolidación
 */
package automation.funcionalidades;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import pages.ExpoSaldoEmpresaPage;
import pages.ExpoLoginPage;
import pages.ExpoAdministrationPage;

public class ExpoSaldoEmpresaTest extends BaseTest {

    private ExpoSaldoEmpresaPage saldoPage;
    private ExpoLoginPage loginPage;
    private ExpoAdministrationPage adminPage;

    @Before
    public void setUpSaldoEmpresa() throws InterruptedException {
        saldoPage = new ExpoSaldoEmpresaPage(driver);
        loginPage = new ExpoLoginPage(driver);
        adminPage = new ExpoAdministrationPage(driver);

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);
        loginPage.ingresarEmail("juan@agraria.com");
        loginPage.ingresarPassword("Contraseña1");
        loginPage.clickLogin();
        Thread.sleep(1000);

    }

    @Test
    public void consolidacionEmpresaTest() throws Exception {
        try {

            Thread.sleep(1500);
            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
            Thread.sleep(1000);
            adminPage.clickPlanillas();
            Thread.sleep(1500);
            saldoPage.clickCrearPlanilla();
            Thread.sleep(1500);
            saldoPage.ingresarNombrePlanilla("Galpones");
            Thread.sleep(1000);
            saldoPage.confirmarPlanilla();
            Thread.sleep(2000);
            //////////////////////////////////////////////////////////////////////////
            // llega hasta aca, estuve viendo y necestian un año, que se le asigne un año
            // de ahi poder asignarle las planillas, de ahi las filas con las empresass
            /////////////////////////////////////////////////77777
            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
            Thread.sleep(1500);
            saldoPage.seleccionarPlanilla("Galpones");
            Thread.sleep(1500);
            saldoPage.crearNuevaFila();
            Thread.sleep(1000);
            saldoPage.crearFilaEnPlanilla("1", "Fila 1 Galpones", 5000, "Empresa Test");
            Thread.sleep(1500);

            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
            Thread.sleep(1000);
            adminPage.clickPlanillas();
            Thread.sleep(1500);
            saldoPage.clickCrearPlanilla();
            Thread.sleep(1500);
            saldoPage.ingresarNombrePlanilla("Stands");
            Thread.sleep(1000);
            saldoPage.confirmarPlanilla();
            Thread.sleep(2000);

            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
            Thread.sleep(1500);
            saldoPage.seleccionarPlanilla("Stands");
            saldoPage.crearNuevaFila();
            Thread.sleep(1000);
            saldoPage.crearFilaEnPlanilla("1", "Fila 1 Stands", 3000, "Empresa Test");
            Thread.sleep(1500);

            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/administracion");
            Thread.sleep(1000);
            adminPage.clickEgresos();
            Thread.sleep(1500);
            saldoPage.clickCrearPlanilla();
            Thread.sleep(1500);
            saldoPage.ingresarNombrePlanilla("Seguridad");
            Thread.sleep(1000);
            saldoPage.confirmarPlanilla();
            Thread.sleep(2000);

            driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/egresos");
            Thread.sleep(1500);
            saldoPage.seleccionarPlanilla("Seguridad");
            saldoPage.crearNuevaFila();
            Thread.sleep(1000);
            saldoPage.crearFilaEnPlanilla("1", "Fila 1 Seguridad", 2000, "Empresa Test");
            Thread.sleep(1500);

            // aserts para los saldos

            // driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/empresas");
            // Thread.sleep(1500);
            // saldoPage.abrirEmpresaTest();
            // Thread.sleep(2000);
            //
            // Assert.assertEquals("$4000", saldoPage.getSaldoGalpones());
            // Assert.assertEquals("$2500", saldoPage.getSaldoStands());
            // Assert.assertEquals("$0", saldoPage.getSaldoSeguridad());
            // Assert.assertEquals("$6500", saldoPage.getSaldoTotalEmpresa());

            saldoPage.takeScreenshot("consolidacion_exito");

        } catch (Exception e) {
            saldoPage.takeScreenshot("consolidacion_error");
            throw e;
        }
    }
}
