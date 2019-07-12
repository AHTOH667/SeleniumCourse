import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class FirstTest {


    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.quit();

        WebDriver driver2 = new FirefoxDriver();
        driver2.get("https://www.google.com/");
        driver2.quit();

        WebDriver driver3 = new InternetExplorerDriver();
        driver3.get("https://www.google.com/");
        driver3.quit();
    }
}