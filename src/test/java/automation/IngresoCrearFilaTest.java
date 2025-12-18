package automation;

import org.junit.Test;

import pages.IngresoCrearFilaPage;

public class IngresoCrearFilaTest extends BaseTest {

    @Test
    public void ingresoCrearFilaTest() throws InterruptedException {

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(5000);

        IngresoCrearFilaPage page = new IngresoCrearFilaPage(driver);
        Thread.sleep(2000);
        page.abrirPrimerIngreso();

        Thread.sleep(4000);

        page.crearNuevaFila();
    }
}
