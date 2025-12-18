package automation;

import org.junit.Test;
import pages.IngresoCrearFilaPage;

public class IngresoCrearFilaTest extends BaseTest {

    @Test
    public void ingresoCrearFilaTest() throws InterruptedException {

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(2000);

        IngresoCrearFilaPage page = new IngresoCrearFilaPage(driver);
        page.hacerLogin("lautaro@agraria.com", "Contraseña1");

        Thread.sleep(2000);

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(2000);

        page.abrirPrimerIngreso();
        Thread.sleep(2000);

        page.crearNuevaFila();
        Thread.sleep(2000);

        page.completarFormulario("11111111", "Fila automatizada", 1500);
        Thread.sleep(10000);
    }
}
