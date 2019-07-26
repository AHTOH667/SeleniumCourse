import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class CheckGeoZones {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startAdminka();
        test.login();
    }


    @Test
    public void checkGeoZonesList() {
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Geo Zones')]"));
        List<WebElement> table = TestBase.driver.findElements(By.className("row"));
        for (int i = 0; i < table.size(); i++) {
            table.get(i).findElement(By.xpath(".//a")).click();
            List<String> zones1 = TestBase.driver.findElements(By.cssSelector("select[name $= '[zone_code]'] > option[selected=selected]"))
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
            List<String> zones2 = zones1;
            Collections.sort(zones2);
            Assert.assertEquals(zones2, zones1);
            test.clickOnLeftRail(By.xpath(".//span[contains(.,'Geo Zones')]"));
            table = TestBase.driver.findElements(By.className("row"));
        }
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}