package CheckBinTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class TestBaseForBin {


    static EventFiringWebDriver driver;

    public static EventFiringWebDriver getDriver() {
        return driver;
    }

    public void startLitecartFallback() {
        if (driver != null) {
            return;
        }
        driver = new EventFiringWebDriver(new ChromeDriver());
        //driver = new EventFiringWebDriver(new FirefoxDriver());
        //driver = new EventFiringWebDriver(new InternetExplorerDriver());
        //driver.register(new MyListener());
        driver.get("http://litecart.stqa.ru/en/");
    }

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void quit() {
        driver.quit();

        //It is used for get Version for the WebDriver
        //System.out.println(driver.getCapabilities().getVersion());
    }

}
