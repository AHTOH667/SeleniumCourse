import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;


public class CheckMassages {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startAdminka();
        test.login();
    }


    @Test
    public void massages() {
        List<WebElement> rows = followToCatalog();
        for (int i = 0; i < rows.size(); i++) {
            rows.get(i).click();
            //System.out.println(TestBase.driver.manage().logs().getAvailableLogTypes());
            System.out.println(TestBase.driver.manage().logs().get("browser").getAll());
            System.out.println(TestBase.driver.manage().logs().get("performance").getAll());
            if (i < rows.size() - 1) {
                rows = followToCatalog();
            }
        }
    }

    private List<WebElement> followToCatalog() {
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Catalog')]"));
        TestBase.driver.findElement(By.xpath(".//a[contains(text(),'Rubber Ducks')]")).click();
        TestBase.driver.findElement(By.xpath(".//a[contains(text(),'Subcategory')]")).click();
        WebElement table = TestBase.driver.findElement(By.className("dataTable"));
        List<WebElement> rows = table.findElements(By.xpath(
                ".//a[contains(text(),'Duck') and starts-with(@href, 'http://localhost/litecart/admin/?app=catalog&doc=edit_product&category')]"));
        System.out.println(rows.size());
        return rows;
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}