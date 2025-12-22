package automation;

import org.junit.Test;
import pages.*;

import static org.junit.Assert.assertEquals;

public class TotalesTest extends BaseTest {

    @Test
    public void desgloseIngresos_Egresos_Balance_totalesTest() throws InterruptedException{

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(2000);

        LoginPage page = new LoginPage(driver);

        page.ingresarEmail("juan@agraria.com");
        Thread.sleep(1000);
        page.ingresarPassword("Contraseña1");
        Thread.sleep(2000);
        page.clickIngresar();
        Thread.sleep(2000);

        TotalesPage totales = new TotalesPage(driver);

        totales.clickTotalesTab();
        Thread.sleep(2000);

        double balance = totales.getBalance();

        totales.clickDesgloseIngresos();

        TotalesIngresosPage ingresos = new TotalesIngresosPage(driver);
        Thread.sleep(1000);

        double totalI = ingresos.getSaldoIngresos() + ingresos.getPagosIngresos();

        assertEquals(totalI, ingresos.getTotalIngresos(), 0.01);

        ingresos.clickAtrasButton();

        totales.clickDesgloseEgresos();

        TotalesEgresosPage egresos = new TotalesEgresosPage(driver);
        Thread.sleep(1000);

        egresos.scrollHastaTotal();
        Thread.sleep(1000);

        double totalE = egresos.getSaldoEgresos() + egresos.getPagosEgresos();

        assertEquals(totalE, egresos.getTotalEgresos(), 0.01);

        Thread.sleep(1000);

        double totalGeneral = ingresos.getTotalIngresos() - egresos.getTotalEgresos();

        assertEquals(totalGeneral, balance, 0.01);
    }
}
