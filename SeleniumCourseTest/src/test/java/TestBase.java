import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.List;


public class TestBase {


    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver driver;


    public void startAdminka() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/login.php");
    }

    public void startLitecart() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/en/");
    }

    public void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public void clickOnLeftRail(By locator) {
        driver.findElement(locator).click();
    }

    public void checkStickers(String idBox) {
        WebElement box = driver.findElement(By.id(idBox));
        List<WebElement> elements = box.findElements(By.tagName("li"));
        for (int i = 0; i < elements.size(); i++) {
            Assert.assertTrue(isChildDisplayed(elements.get(i), "new")
                    || isChildDisplayed(elements.get(i), "sale"));
        }
    }

    private boolean isChildDisplayed(WebElement element, String cssClass) {
        try {
            return element.findElement(By.className(cssClass)).isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void quit() {
        driver.quit();
    }
}
