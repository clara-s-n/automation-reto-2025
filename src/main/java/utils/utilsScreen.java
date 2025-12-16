package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Utilidades para manejo de pantalla y capturas de pantalla
 * Utiliza la configuración del archivo .env para obtener la ruta de screenshots
 */
public class utilsScreen {

    /**
     * Método para redimensionar la pantalla
     * @param driver Instancia del WebDriver
     * @param width Ancho de la ventana
     * @param height Alto de la ventana
     */
    public static void resizeWindow(WebDriver driver, int width, int height) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
    }

    /**
     * Método para maximizar la pantalla
     * @param driver Instancia del WebDriver
     */
    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().maximize();
    }

    /**
     * Método para tomar capturas de pantalla
     * La ruta de guardado se obtiene del archivo .env
     * @param driver Instancia del WebDriver
     * @param testCaseName Nombre del caso de prueba
     */
    public static void takeScreenshot(WebDriver driver, String testCaseName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Obtener la ruta desde el archivo .env
        String screenshotPath = EnvConfig.getScreenshotsPath();

        // Crear el directorio si no existe
        File directory = new File(screenshotPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = screenshotPath + File.separator + testCaseName + "_" + timestamp + ".png";
        try {
            FileHandler.copy(screenshot, new File(filePath));
            System.out.println("Screenshot guardado en: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al guardar screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para manejar los resultados de búsqueda
    public static void handleSearchResults(List<WebElement> resultTitles) {
        for (WebElement title : resultTitles) {
            System.out.println(title.getText());
        }
    }

    // Método para realizar la búsqueda en Google
    /* 
    public static void searchInGoogle(WebDriver driver, String query) {
        paraBankHomePage homePage = new paraBankHomePage(driver);
        homePage.searchFor(query);
    }
    */
    // Método para obtener los resultados de búsqueda en Google
    public static List<WebElement> getGoogleSearchResults(WebDriver driver) {
        List<WebElement> resultTitles = driver.findElements(By.cssSelector("h3"));
        handleSearchResults(resultTitles);
        return resultTitles;
    }

    // Método para cerrar el navegador
    public static void quitBrowser(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}

