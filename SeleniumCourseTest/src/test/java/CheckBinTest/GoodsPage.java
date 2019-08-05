package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class GoodsPage extends Page {


    public GoodsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private TestBaseForBin test = new TestBaseForBin();


    @FindBy(name = "options[Size]")
    public WebElement size;

    @FindBy(name = "add_cart_product")
    public WebElement element;


    public GoodsPage selectSmallSize() {
        if (test.isElementPresent(driver, By.name("options[Size]"))) {
            Select select = new Select(size);
            select.selectByVisibleText("Small");
        }
        return this;
    }

    public void addToCart(int finalI) {
        element.click();
        wait.until(d -> Integer.parseInt(d.findElement(By.className("quantity")).getText()) != finalI);
    }
}