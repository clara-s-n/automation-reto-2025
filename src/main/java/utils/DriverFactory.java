package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Factory para crear instancias de WebDriver
 * Utiliza la configuración del archivo .env para obtener las rutas de los drivers
 * Soporta múltiples navegadores: edge, chrome, firefox
 */
public class DriverFactory {

    /**
     * Obtiene una instancia de WebDriver según el navegador especificado
     * @param browser Nombre del navegador ("edge", "chrome", "firefox")
     * @return Instancia de WebDriver configurada
     */
    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        
        String browserType = browser == null ? "edge" : browser.toLowerCase();

        switch (browserType) {
            case "chrome":
                String chromePath = System.getProperty("webdriver.chrome.driver", 
                    EnvConfig.get("WEBDRIVER_CHROME_PATH", "chromedriver.exe"));
                System.setProperty("webdriver.chrome.driver", chromePath);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                String firefoxPath = System.getProperty("webdriver.gecko.driver", 
                    EnvConfig.get("WEBDRIVER_FIREFOX_PATH", "geckodriver.exe"));
                System.setProperty("webdriver.gecko.driver", firefoxPath);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
            default:
                // Obtener la ruta del driver desde el archivo .env
                String edgePath = System.getProperty("webdriver.edge.driver",
                        EnvConfig.getEdgeDriverPath());
                System.setProperty("webdriver.edge.driver", edgePath);
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                driver = new EdgeDriver(edgeOptions);
                break;
        }

        return driver;
    }

    /**
     * Obtiene el navegador configurado desde las propiedades del sistema
     * Si no está configurado, retorna "edge" por defecto
     * @return Nombre del navegador configurado
     */
    public static String getConfiguredBrowser() {
        return System.getProperty("browser", "chrome");
    }
}