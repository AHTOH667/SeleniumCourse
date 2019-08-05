package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends Page {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://litecart.stqa.ru/en/");
    }

    public void selectFirstDuck() {
        WebElement box = driver.findElement(By.id("box-most-popular"));
        box.findElement(By.tagName("li")).click();
    }
}