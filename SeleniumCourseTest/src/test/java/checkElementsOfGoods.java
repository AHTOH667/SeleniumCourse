import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class checkElementsOfGoods {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startLitecart();
    }


    @Test
    public void checkElements() {
        WebElement box = TestBase.driver.findElement(By.id("box-campaigns"));
        List<WebElement> goods = box.findElements(By.tagName("li"));
        test.elementsOfGoods(TestBase.driver, goods, box);
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
