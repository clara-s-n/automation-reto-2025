package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SafeClick {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SafeClick(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // =========================
    // Helpers
    // =========================
    public void safeClick(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        for (int i = 0; i < 5; i++) {
            scrollToCenter(element);
            // Si lo tapa un iframe de anuncios, lo ocultamos y reintentamos
            if (isCovered(element)) {
                WebElement blocker = topElementAtCenter(element);
                if (blocker != null && "iframe".equalsIgnoreCase(blocker.getTagName())
                        && isAdIframe(blocker)) {
                    hideElement(blocker);
                    // por si hay contenedor padre que sigue “ocupando” el lugar visual
                    hideParentsUpTo(bodyOrHtml(), blocker, 3);
                    continue;
                }
                // si no es iframe ad, intentá reubicar el botón con un scroll pequeño
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -160);");
                continue;
            }
            try {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                return;
            } catch (ElementClickInterceptedException e) {
                // último reintento: intentar limpiar ads y reubicar
                hideCommonAdIframes();
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -160);");
            } catch (StaleElementReferenceException e) {
                // si el DOM cambió, reintenta el loop (tu POM normalmente vuelve a resolverlo)
            }
        }

        // Fallback FINAL (no ideal, pero evita que el test muera si el sitio mete ads agresivos)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void scrollToCenter (WebElement element){
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
    }

    private boolean isCovered (WebElement element){
        Object result = ((JavascriptExecutor) driver).executeScript(
                "const el = arguments[0];" +
                        "const r = el.getBoundingClientRect();" +
                        "const x = r.left + r.width/2;" +
                        "const y = r.top + r.height/2;" +
                        "const top = document.elementFromPoint(x, y);" +
                        "return !(top === el || el.contains(top));",
                element);
        return result instanceof Boolean && (Boolean) result;
    }

    private WebElement topElementAtCenter (WebElement element){
        Object el = ((JavascriptExecutor) driver).executeScript(
                "const target = arguments[0];" +
                        "const r = target.getBoundingClientRect();" +
                        "const x = r.left + r.width/2;" +
                        "const y = r.top + r.height/2;" +
                        "return document.elementFromPoint(x, y);",
                element);
        return (el instanceof WebElement) ? (WebElement) el : null;
    }

    private boolean isAdIframe (WebElement iframe){
        String id = safeLower(iframe.getAttribute("id"));
        String name = safeLower(iframe.getAttribute("name"));
        String title = safeLower(iframe.getAttribute("title"));
        String src = safeLower(iframe.getAttribute("src"));

        return id.startsWith("aswift")
                || name.startsWith("aswift")
                || id.contains("google_ads_iframe")
                || title.contains("advertisement")
                || src.contains("doubleclick")
                || src.contains("googleads");
    }

    private void hideCommonAdIframes () {
        List<WebElement> frames = driver.findElements(By.cssSelector(
                "iframe[id^='aswift'], iframe[name^='aswift'], iframe[id*='google_ads_iframe'], iframe[title*='Advertisement'], iframe[src*='doubleclick'], iframe[src*='googleads']"
        ));
        for (WebElement f : frames) {
            try {
                hideElement(f);
            } catch (Exception ignored) {
            }
        }
    }

    private void hideElement (WebElement el){
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.setProperty('display','none','important');" +
                        "arguments[0].style.setProperty('visibility','hidden','important');" +
                        "arguments[0].style.setProperty('pointer-events','none','important');",
                el);
    }

    private WebElement bodyOrHtml () {
        return driver.findElement(By.cssSelector("body"));
    }

    private void hideParentsUpTo (WebElement stopAt, WebElement child,int levels){
        ((JavascriptExecutor) driver).executeScript(
                "let el = arguments[0];" +
                        "let stop = arguments[1];" +
                        "let n = arguments[2];" +
                        "while (el && n-- > 0 && el !== stop && el.parentElement) {" +
                        "  el = el.parentElement;" +
                        "  el.style.setProperty('display','none','important');" +
                        "}",
                child, stopAt, levels
        );
    }

    private String safeLower (String s){
        return s == null ? "" : s.toLowerCase();
    }

}
