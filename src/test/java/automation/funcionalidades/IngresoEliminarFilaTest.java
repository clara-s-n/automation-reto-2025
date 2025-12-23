package automation.funcionalidades;

import org.junit.Test;
import pages.IngresoEliminarFilaPage;

public class IngresoEliminarFilaTest extends BaseTest {

    @Test
    public void eliminarFilaTest() throws InterruptedException {

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(2000);

        IngresoEliminarFilaPage page = new IngresoEliminarFilaPage(driver);
        page.hacerLogin("ana@agraria.com", "Contraseña1");

        Thread.sleep(1000);

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
        Thread.sleep(2000);

        page.abrirPrimerIngreso();
        Thread.sleep(2000);

        page.abrirPrimeraFila();
        Thread.sleep(2000);

        page.eliminarFila();
        Thread.sleep(2000);

        page.eliminarFinal();
        Thread.sleep(1000);
        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);
    }
}
