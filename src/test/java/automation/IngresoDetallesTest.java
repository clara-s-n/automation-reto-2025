package automation;

import org.junit.Assert;
import org.junit.Test;
import pages.IngresoDetallesPage;

public class IngresoDetallesTest extends BaseTest {

    @Test
    public void ingresoDetallesTest() throws InterruptedException {

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(2000);

        IngresoDetallesPage page = new IngresoDetallesPage(driver);
        page.hacerLogin("lautaro@agraria.com", "Contraseña1");

        Thread.sleep(2000);

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(2000);

        page.abrirPrimerIngreso();

        Thread.sleep(2000);

        Assert.assertTrue(page.estaEnDetalle());
    }
}
