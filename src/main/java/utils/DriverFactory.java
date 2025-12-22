package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;

/**
 * Factory para crear instancias de WebDriver
 * Utiliza WebDriverManager para gestionar automáticamente los drivers
 * Si falla, usa la ruta del driver desde EnvConfig como fallback
 * Soporta múltiples navegadores: edge, chrome, firefox
 * Soporta modo headless con -Dheadless=true
 */
public class DriverFactory {

    /**
     * Verifica si el modo headless está habilitado
     * @return true si -Dheadless=true está configurado
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    /**
     * Obtiene una instancia de WebDriver según el navegador especificado
     * 
     * @param browser Nombre del navegador ("edge", "chrome", "firefox")
     * @return Instancia de WebDriver configurada
     */
    public static WebDriver getDriver(String browser) {
        WebDriver driver;

        String browserType = browser == null ? "edge" : browser.toLowerCase();

        switch (browserType) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "firefox":
                driver = createFirefoxDriver();
                break;
            case "edge":
            default:
                driver = createEdgeDriver();
                break;
        }

        return driver;
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        
        // Modo headless si está configurado
        if (isHeadless()) {
            chromeOptions.addArguments("--headless=new");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--disable-gpu");
            System.out.println("Chrome ejecutándose en modo HEADLESS");
        }

        try {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(chromeOptions);
        } catch (Exception e) {
            System.out.println("WebDriverManager falló, usando driver local: " + e.getMessage());
            String chromePath = EnvConfig.get("WEBDRIVER_CHROME_PATH", "chromedriver.exe");
            File driverFile = new File(chromePath);
            if (driverFile.exists()) {
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(driverFile)
                        .build();
                return new ChromeDriver(service, chromeOptions);
            }
            throw new RuntimeException("No se pudo configurar ChromeDriver. Driver no encontrado en: " + chromePath);
        }
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-dev-shm-usage");
        
        // Modo headless si está configurado
        if (isHeadless()) {
            firefoxOptions.addArguments("-headless");
            firefoxOptions.addArguments("--width=1920");
            firefoxOptions.addArguments("--height=1080");
            System.out.println("Firefox ejecutándose en modo HEADLESS");
        }

        try {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(firefoxOptions);
        } catch (Exception e) {
            System.out.println("WebDriverManager falló, usando driver local: " + e.getMessage());
            String firefoxPath = EnvConfig.get("WEBDRIVER_FIREFOX_PATH", "geckodriver.exe");
            File driverFile = new File(firefoxPath);
            if (driverFile.exists()) {
                GeckoDriverService service = new GeckoDriverService.Builder()
                        .usingDriverExecutable(driverFile)
                        .build();
                return new FirefoxDriver(service, firefoxOptions);
            }
            throw new RuntimeException("No se pudo configurar FirefoxDriver. Driver no encontrado en: " + firefoxPath);
        }
    }

    private static WebDriver createEdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--remote-allow-origins=*");
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--disable-dev-shm-usage");
        
        // Modo headless si está configurado
        if (isHeadless()) {
            edgeOptions.addArguments("--headless=new");
            edgeOptions.addArguments("--window-size=1920,1080");
            edgeOptions.addArguments("--disable-gpu");
            System.out.println("Edge ejecutándose en modo HEADLESS");
        }

        try {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver(edgeOptions);
        } catch (Exception e) {
            System.out.println("WebDriverManager falló, usando driver local: " + e.getMessage());
            String edgePath = EnvConfig.getEdgeDriverPath();
            File driverFile = new File(edgePath);
            if (driverFile.exists()) {
                EdgeDriverService service = new EdgeDriverService.Builder()
                        .usingDriverExecutable(driverFile)
                        .build();
                return new EdgeDriver(service, edgeOptions);
            }
            throw new RuntimeException("No se pudo configurar EdgeDriver. Driver no encontrado en: " + edgePath);
        }
    }

    /**
     * Obtiene el navegador configurado desde las propiedades del sistema
     * Si no está configurado, retorna "chrome" por defecto
     * 
     * @return Nombre del navegador configurado
     */
    public static String getConfiguredBrowser() {
        return System.getProperty("browser", "chrome");
    }
}