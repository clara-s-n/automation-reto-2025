package automation;

import org.junit.Assert;
import org.junit.Test;

import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() throws InterruptedException {

        driver.navigate().to("https://reto2025.brazilsouth.cloudapp.azure.com/login");
        Thread.sleep(2000);

        LoginPage page = new LoginPage(driver);

        page.ingresarEmail("lautaro@agraria.com");
        Thread.sleep(1000);
        page.ingresarPassword("Contraseña1");
        Thread.sleep(3000);
        page.clickIngresar();

        Thread.sleep(5000);

        Assert.assertTrue(driver.getCurrentUrl().contains("/ingresos"));
    }
}
