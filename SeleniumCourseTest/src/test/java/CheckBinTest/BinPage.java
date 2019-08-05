package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;


public class BinPage extends Page {

    public BinPage(WebDriver driver) {
        super(driver);
    }

    public void followToBinPage() {
        driver.findElement(By.id("cart-wrapper")).click();
    }

    public BinPage stopCarousel() {
        List<WebElement> goods;
        goods = driver.findElements(By.xpath(".//li[contains(@class, 'shortcut')]"));
        System.out.println("goods = " + goods.size());
        if (goods.size() > 0) {
            goods.get(0).click();
        }
        return this;
    }

    public void removeAll() {
        List<WebElement> removeButton;
        removeButton = driver.findElements(By.name("remove_cart_item"));
        System.out.println("Button = " + removeButton.size());
        for (WebElement webElement : removeButton) {
            if (webElement.isDisplayed()) {
                webElement.click();
                WebElement order = driver.findElement(By.className("dataTable"));
                List<WebElement> rows = order.findElements(By.className("item"));
                int numberOfRows = rows.size();
                wait.until(numberOfElementsToBeLessThan(By.cssSelector(".dataTable .item"), numberOfRows));
                break;
            }
        }
    }
}