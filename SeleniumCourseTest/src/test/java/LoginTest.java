import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class LoginTest {


    public static WebDriver driver;

    @BeforeMethod
    public void start() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/login.php");
    }


    @Test
    public void login() {

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.quit();
    }
}
