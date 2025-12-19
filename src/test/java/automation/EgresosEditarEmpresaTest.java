package automation;

import org.junit.Test;
import pages.EgresosEditarEmpresaPage;

public class EgresosEditarEmpresaTest extends BaseTest {

    @Test
    public void editarCITest() throws InterruptedException {

        driver.get("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(1000);

        EgresosEditarEmpresaPage page = new EgresosEditarEmpresaPage(driver);
        page.login("lautaro@agraria.com", "Contraseña1");

        Thread.sleep(2000);

        page.editarCI("45678912");

        Thread.sleep(2000);
    }
}
