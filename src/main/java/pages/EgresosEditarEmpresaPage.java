package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SafeClick;

import java.time.Duration;

public class EgresosEditarEmpresaPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private SafeClick safeClick;

    public EgresosEditarEmpresaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.safeClick = new SafeClick(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement inputPassword;

    @FindBy(xpath = "/html/body/app-root/ion-app/ion-router-outlet/app-login/ion-content/div/ion-card/ion-card-content/form/ion-button")
    private WebElement botonLogin;

    @FindBy(xpath = "//ion-tab-button[@tab='empresas']")
    private WebElement tabEmpresas;

    @FindBy(css = "ion-content ion-card")
    private WebElement primeraEmpresa;

    @FindBy(xpath = "//ion-button[contains(.,'Editar') or contains(.,'editar')]")
    private WebElement botonEditar;

    @FindBy(xpath = "//ion-input[@formcontrolname='ci']//input | //ion-input[contains(@placeholder,'CI') or contains(@placeholder,'ci') or contains(@placeholder,'Cédula')]//input")
    private WebElement inputCI;

    @FindBy(xpath = "//ion-button[contains(.,'Guardar') or contains(.,'guardar')]")
    private WebElement botonGuardar;

    public void login(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        botonLogin.click();
    }

    public void editarCI(String ci) throws InterruptedException {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;

        wait.until(ExpectedConditions.elementToBeClickable(tabEmpresas));
        safeClick.safeClick(tabEmpresas);
        Thread.sleep(3000);

        // Esperar a que carguen las empresas
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-content ion-card")));
        Thread.sleep(1000);

        // Obtener la primera card de empresa
        java.util.List<WebElement> cards = driver.findElements(
                org.openqa.selenium.By.cssSelector("ion-content ion-card"));

        if (cards.isEmpty()) {
            throw new RuntimeException("No se encontraron empresas para editar");
        }

        js.executeScript("arguments[0].click();", cards.get(0));
        Thread.sleep(3000);

        // Buscar el botón editar - puede estar en diferentes lugares
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-content")));
        Thread.sleep(1000);

        // Intentar encontrar el botón editar con diferentes selectores
        WebElement editButton = null;
        String[] editSelectors = {
                "ion-button[color='primary']",
                "ion-button:contains('Editar')",
                "button[aria-label*='edit']",
                "ion-fab-button",
                ".edit-button",
                "[routerlink*='editar']"
        };

        for (String selector : editSelectors) {
            try {
                java.util.List<WebElement> buttons = driver.findElements(
                        org.openqa.selenium.By.cssSelector(selector));
                if (!buttons.isEmpty()) {
                    editButton = buttons.get(0);
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }

        // Si no encuentra con CSS, intentar con XPath
        if (editButton == null) {
            try {
                editButton = driver.findElement(
                        org.openqa.selenium.By
                                .xpath("//ion-button[contains(translate(., 'EDITAR', 'editar'), 'editar')]"));
            } catch (Exception e) {
                // Intentar con ion-fab-button
                try {
                    editButton = driver.findElement(
                            org.openqa.selenium.By.cssSelector("ion-fab-button"));
                } catch (Exception ex) {
                    throw new RuntimeException("No se encontró el botón de editar. Selectores probados: " +
                            String.join(", ", editSelectors));
                }
            }
        }

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editButton);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", editButton);
        Thread.sleep(3000);

        // Esperar a que aparezca el formulario
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-input")));
        Thread.sleep(1000);

        // Buscar el campo CI - generalmente es el 5to input (índice 4)
        java.util.List<WebElement> ionInputs = driver.findElements(
                org.openqa.selenium.By.cssSelector("ion-input"));

        if (ionInputs.size() >= 5) {
            WebElement ciInput = ionInputs.get(4);
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ciInput);
            Thread.sleep(200);

            // Limpiar y establecer nuevo valor
            try {
                WebElement nativeInput = ciInput.findElement(org.openqa.selenium.By.cssSelector("input"));
                js.executeScript("arguments[0].value = '';", nativeInput);
                js.executeScript("arguments[0].value = arguments[1];", nativeInput, ci);
                js.executeScript("arguments[0].dispatchEvent(new Event('input', {bubbles: true}));", nativeInput);
            } catch (Exception e) {
                js.executeScript("arguments[0].value = arguments[1];", ciInput, ci);
            }
            js.executeScript("arguments[0].dispatchEvent(new Event('ionChange', {bubbles: true}));", ciInput);
        }

        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
        js.executeScript("arguments[0].click();", botonGuardar);
    }

    public void editarCI(String ci) throws InterruptedException {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;

        wait.until(ExpectedConditions.elementToBeClickable(tabEmpresas));
        safeClick.safeClick(tabEmpresas);
        Thread.sleep(3000);

        // Esperar a que carguen las empresas
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-content ion-card")));
        Thread.sleep(1000);

        // Obtener la primera card de empresa
        java.util.List<WebElement> cards = driver.findElements(
                org.openqa.selenium.By.cssSelector("ion-content ion-card"));

        if (cards.isEmpty()) {
            throw new RuntimeException("No se encontraron empresas para editar");
        }

        js.executeScript("arguments[0].click();", cards.get(0));
        Thread.sleep(3000);

        // Buscar el botón editar - puede estar en diferentes lugares
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-content")));
        Thread.sleep(1000);

        // Intentar encontrar el botón editar con diferentes selectores
        WebElement editButton = null;
        String[] editSelectors = {
                "ion-button[color='primary']",
                "ion-button:contains('Editar')",
                "button[aria-label*='edit']",
                "ion-fab-button",
                ".edit-button",
                "[routerlink*='editar']"
        };

        for (String selector : editSelectors) {
            try {
                java.util.List<WebElement> buttons = driver.findElements(
                        org.openqa.selenium.By.cssSelector(selector));
                if (!buttons.isEmpty()) {
                    editButton = buttons.get(0);
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }

        // Si no encuentra con CSS, intentar con XPath
        if (editButton == null) {
            try {
                editButton = driver.findElement(
                        org.openqa.selenium.By
                                .xpath("//ion-button[contains(translate(., 'EDITAR', 'editar'), 'editar')]"));
            } catch (Exception e) {
                // Intentar con ion-fab-button
                try {
                    editButton = driver.findElement(
                            org.openqa.selenium.By.cssSelector("ion-fab-button"));
                } catch (Exception ex) {
                    throw new RuntimeException("No se encontró el botón de editar. Selectores probados: " +
                            String.join(", ", editSelectors));
                }
            }
        }

        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", editButton);
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", editButton);
        Thread.sleep(3000);

        // Esperar a que aparezca el formulario
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("ion-input")));
        Thread.sleep(1000);

        // Buscar el campo CI - generalmente es el 5to input (índice 4)
        java.util.List<WebElement> ionInputs = driver.findElements(
                org.openqa.selenium.By.cssSelector("ion-input"));

        if (ionInputs.size() >= 5) {
            WebElement ciInput = ionInputs.get(4);
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ciInput);
            Thread.sleep(200);

            // Limpiar y establecer nuevo valor
            try {
                WebElement nativeInput = ciInput.findElement(org.openqa.selenium.By.cssSelector("input"));
                js.executeScript("arguments[0].value = '';", nativeInput);
                js.executeScript("arguments[0].value = arguments[1];", nativeInput, ci);
                js.executeScript("arguments[0].dispatchEvent(new Event('input', {bubbles: true}));", nativeInput);
            } catch (Exception e) {
                js.executeScript("arguments[0].value = arguments[1];", ciInput, ci);
            }
            js.executeScript("arguments[0].dispatchEvent(new Event('ionChange', {bubbles: true}));", ciInput);
        }

        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
        js.executeScript("arguments[0].click();", botonGuardar);
    }
}
