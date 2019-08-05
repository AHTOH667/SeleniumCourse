package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class GoodsPage extends Page {


    public GoodsPage(WebDriver driver) {
        super(driver);
    }

    private TestBaseForBin test = new TestBaseForBin();

    public void selectSmallSize() {
        if (test.isElementPresent(driver, By.name("options[Size]"))) {
            WebElement size = driver.findElement(By.name("options[Size]"));
            Select select = new Select(size);
            select.selectByVisibleText("Small");
        }
    }

    public void addToCart(int finalI) {
        driver.findElement(By.name("add_cart_product")).click();
        wait.until(d -> Integer.parseInt(d.findElement(By.className("quantity")).getText()) != finalI);
    }
}
