package automation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import pages.IngresoCrearFilaPage;
import pages.IngresoPrecioFilaPage;
import pages.LoginPage;
import utils.utilsScreen;

public class IngresoPrecioFilaTest extends BaseTest {

    @Test
public void agregarPagoTest() throws InterruptedException {

    utilsScreen.maximizeWindow(driver);

    driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
    Thread.sleep(2000);

    LoginPage loginPage = new LoginPage(driver);
    loginPage.ingresarEmail("juan@agraria.com");
    Thread.sleep(500);
    loginPage.ingresarPassword("Contraseña1");
    Thread.sleep(500);
    loginPage.clickIngresar();

    Thread.sleep(3000);

    driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/ingresos");
    Thread.sleep(3000);

    IngresoCrearFilaPage crearFilaPage = new IngresoCrearFilaPage(driver);

    crearFilaPage.abrirPrimerIngreso();
    Thread.sleep(2000);

    crearFilaPage.crearNuevaFila();
    Thread.sleep(2000);

    crearFilaPage.completarFormulario("11111111", "Fila automatizada", 1500);
    Thread.sleep(3000);

    IngresoPrecioFilaPage precioPage = new IngresoPrecioFilaPage(driver);

    precioPage.entrarEnFila();
    precioPage.entrarDetalle();
    Thread.sleep(2000);
    precioPage.entrarEnPrecio();
    Thread.sleep(500);
    precioPage.ingresarPrecio("1500");
    Thread.sleep(500);
    precioPage.ingresarMetodoDePago("Efectivo");
    Thread.sleep(500);
    precioPage.ingresarObservaciones("Sin Observaciones");
    Thread.sleep(500);
    precioPage.seleccionarEmpresa("LAUTARO ARRIGONI GOLDMANN");

    precioPage.guardar();
    Thread.sleep(2000);

    utilsScreen.takeScreenshot(driver, "PagoCreado");
}
}
