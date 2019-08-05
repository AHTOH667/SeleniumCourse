package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
public class TestBaseForBin {


    static EventFiringWebDriver driver;
    static WebDriverWait wait;

    public static EventFiringWebDriver getDriver() {
        return driver;
    }

    public WebDriverWait wait (EventFiringWebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        return wait;
    }

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
