package automation;

import org.junit.Test;
import pages.EgresosCrearEmpresaPage;

public class EgresosCrearEmpresaTest extends BaseTest {

    @Test
    public void crearEmpresaTest() throws InterruptedException {

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);

        EgresosCrearEmpresaPage page = new EgresosCrearEmpresaPage(driver);
        page.login("ana@agraria.com", "Contraseña1");

        Thread.sleep(1000);

        page.crearEmpresa();

        Thread.sleep(3000);
    }
}
