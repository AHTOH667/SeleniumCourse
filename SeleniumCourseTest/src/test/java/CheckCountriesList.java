import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CheckCountriesList {


    private TestBase test = new TestBase();


    @BeforeMethod
    public void before() {
        test.startAdminka();
        test.login();
    }


    @Test
    public void checkCountriesList() {
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Countries')]"));
        test.countriesList();
        test.zonesList();
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
