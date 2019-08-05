package CheckBinTest.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends Page {


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "box-most-popular")
    public WebElement box;


    public void open() {
        driver.get("http://litecart.stqa.ru/en/");
    }

    public void selectFirstDuck() {
        box.findElement(By.tagName("li")).click();
    }
}