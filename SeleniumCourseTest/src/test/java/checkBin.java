import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.*;


public class checkBin {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startLitecartFallback();
    }


    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            WebElement box = TestBase.driver.findElement(By.id("box-most-popular"));
            box.findElement(By.tagName("li")).click();
            if (test.isElementPresent(TestBase.driver, By.name("options[Size]"))) {
                WebElement size = TestBase.driver.findElement(By.name("options[Size]"));
                Select select = new Select(size);
                select.selectByVisibleText("Small");
            }
            String cartBefore = TestBase.driver.findElement(By.className("quantity")).getText();
            TestBase.driver.findElement(By.name("add_cart_product")).click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String cartAfter = TestBase.driver.findElement(By.className("quantity")).getText();
            assertTrue(cartBefore != cartAfter);
            TestBase.driver.findElement(By.id("logotype-wrapper")).click();
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
                    break;
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
