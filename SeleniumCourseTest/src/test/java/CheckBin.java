import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class CheckBin {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startLitecartFallback();
    }


    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(TestBase.driver, 30);
        for (int i = 0; i < 3; i++) {
            WebElement box = TestBase.driver.findElement(By.id("box-most-popular"));
            box.findElement(By.tagName("li")).click();
            if (test.isElementPresent(TestBase.driver, By.name("options[Size]"))) {
                WebElement size = TestBase.driver.findElement(By.name("options[Size]"));
                Select select = new Select(size);
                select.selectByVisibleText("Small");
            }
            TestBase.driver.findElement(By.name("add_cart_product")).click();
            int finalI = i;
            wait.until(d -> Integer.parseInt(d.findElement(By.className("quantity")).getText()) != finalI);
            if (i < 2) {
                TestBase.driver.findElement(By.id("logotype-wrapper")).click();
            }
        }
        TestBase.driver.findElement(By.id("cart-wrapper")).click();
        List<WebElement> goods;
        List<WebElement> removeButton;
        while (! test.isElementPresent(TestBase.driver, By.xpath(".//em[contains(text(),'There are no items in your cart.')]"))) {
            goods = TestBase.driver.findElements(By.xpath(".//li[contains(@class, 'shortcut')]"));
            System.out.println("goods = " + goods.size());
            if (goods.size() > 0) {
                goods.get(0).click();
            }
            removeButton = TestBase.driver.findElements(By.name("remove_cart_item"));
            System.out.println("Button = " + removeButton.size());
            for (int i = 0; i < removeButton.size(); i++) {
                if (removeButton.get(i).isDisplayed()) {
                    removeButton.get(i).click();
                    WebElement order = TestBase.driver.findElement(By.className("dataTable"));
                    List<WebElement> rows = order.findElements(By.className("item"));
                    int numberOfRows = rows.size();
                    wait.until(numberOfElementsToBeLessThan(By.cssSelector(".dataTable .item"), numberOfRows));
                    break;
                }
            }
        }
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
