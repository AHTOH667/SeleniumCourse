package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class TestBaseForBin {


    /*static EventFiringWebDriver driver;

    public static EventFiringWebDriver getDriver() {
        return driver;
    }*/

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}