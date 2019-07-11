import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestBase {


    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver driver;
    

    public void start() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/login.php");

    }

    public void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }



    public void clickOnLeftRail(By locator) {
        driver.findElement(locator).click();
    }

    public void quit() {
        driver.quit();
    }
}
