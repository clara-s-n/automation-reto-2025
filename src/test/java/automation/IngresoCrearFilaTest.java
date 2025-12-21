package automation;

import org.junit.Test;
import static org.junit.Assert.*;
import pages.IngresoCrearFilaPage;
import utils.utilsScreen;

public class IngresoCrearFilaTest extends BaseTest {

    @Test
    public void ingresoCrearFilaTest() throws InterruptedException {

        utilsScreen.maximizeWindow(driver);

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(2000);

        IngresoCrearFilaPage page = new IngresoCrearFilaPage(driver);
        page.hacerLogin("juan@agraria.com", "Contraseña1");

        Thread.sleep(2000);

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(2000);

        page.abrirPrimerIngreso();
        Thread.sleep(2000);

        page.crearNuevaFila();
        Thread.sleep(2000);

        page.completarFormulario("11111111", "Fila automatizada", 1500);
        Thread.sleep(5000);
        utilsScreen.takeScreenshot(driver, "CrearFilaIngresos");
        assertTrue(
        driver.getPageSource().contains("Fila automatizada")
        );


        page.crearNuevaFila2();
        Thread.sleep(2000);

        page.completarFormulario2("22222222", "Nada que agregar", 2000);
        Thread.sleep(2000);
        assertTrue(
        driver.getPageSource().contains("Nada que agregar")
        );



        
    }
    

}
