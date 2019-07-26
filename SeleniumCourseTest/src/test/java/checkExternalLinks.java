import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Set;


public class checkExternalLinks {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startAdminka();
        test.login();
    }


    @Test
    public void checkLinks() {
        WebDriverWait wait = new WebDriverWait(TestBase.driver, 30);
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Countries')]"));
        TestBase.driver.findElement(By.className("fa-pencil")).click();
        WebElement element = TestBase.driver.findElement(By.cssSelector("[method=post]"));
        List <WebElement> links = element.findElements(By.cssSelector("[target=_blank]"));
        for (int i = 0; i < links.size(); i++) {
            String oldWindow = TestBase.driver.getWindowHandle();
            Set<String> allOldWindows = TestBase.driver.getWindowHandles();
            links.get(i).click();
            String newWindow = wait.until(test.anyWindowOtherThan(allOldWindows));
            TestBase.driver.switchTo().window(newWindow);
            TestBase.driver.close();
            TestBase.driver.switchTo().window(oldWindow);
        }
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
