package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class CheckBin {


    private TestBaseForBin test = new TestBaseForBin();
    private TestData data = new TestData();

    @BeforeMethod
    public void before() {
        test.startLitecartFallback();
    }


    @Test
    public void test() {
        addGoodsInBin();
        followToBinPage();
        removeAllGoods();
    }

    public void addGoodsInBin() {
        for (int i = 0; i < data.numberOfGoods; i++) {
            selectFirstDuck();
            selectSmallSize();
            addToCart(i);
            if (i < data.numberOfGoods - 1) {
                followToHomepage();
            }
        }
    }

    public void selectFirstDuck() {
        WebElement box = TestBaseForBin.driver.findElement(By.id("box-most-popular"));
        box.findElement(By.tagName("li")).click();
    }

    public void selectSmallSize() {
        if (test.isElementPresent(TestBaseForBin.driver, By.name("options[Size]"))) {
            WebElement size = TestBaseForBin.driver.findElement(By.name("options[Size]"));
            Select select = new Select(size);
            select.selectByVisibleText("Small");
        }
    }

    public void addToCart(int finalI) {
        TestBaseForBin.driver.findElement(By.name("add_cart_product")).click();
        TestBaseForBin.wait.until(d -> Integer.parseInt(d.findElement(By.className("quantity")).getText()) != finalI);
    }

    public void followToHomepage() {
        TestBaseForBin.driver.findElement(By.id("logotype-wrapper")).click();
    }

    public void followToBinPage() {
        TestBaseForBin.driver.findElement(By.id("cart-wrapper")).click();
    }

    public void removeAllGoods() {
        while (! test.isElementPresent(
                TestBaseForBin.driver, By.xpath(".//em[contains(text(),'There are no items in your cart.')]"))) {
            stopCarousel();
            removeAll();
        }
    }

    public void stopCarousel() {
        List<WebElement> goods;
        goods = TestBaseForBin.driver.findElements(By.xpath(".//li[contains(@class, 'shortcut')]"));
        System.out.println("goods = " + goods.size());
        if (goods.size() > 0) {
            goods.get(0).click();
        }
    }

    public void removeAll() {
        List<WebElement> removeButton;
        removeButton = TestBaseForBin.driver.findElements(By.name("remove_cart_item"));
        System.out.println("Button = " + removeButton.size());
        for (WebElement webElement : removeButton) {
            if (webElement.isDisplayed()) {
                webElement.click();
                WebElement order = TestBaseForBin.driver.findElement(By.className("dataTable"));
                List<WebElement> rows = order.findElements(By.className("item"));
                int numberOfRows = rows.size();
                TestBaseForBin.wait.until(numberOfElementsToBeLessThan(By.cssSelector(".dataTable .item"), numberOfRows));
                break;
            }
        }
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}