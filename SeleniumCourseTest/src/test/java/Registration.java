import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Registration {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startLitecart();
    }


    @Test
    public void registration() {
        TestBase.driver.findElement(By.xpath(".//a[contains(text(), 'New customers click here')]")).click();
        test.typeFields(TestBase.driver, By.name("firstname"), "firstname");
        test.typeFields(TestBase.driver, By.name("lastname"), "lastname");
        test.typeFields(TestBase.driver, By.name("address1"), "address1");
        test.typeFields(TestBase.driver, By.name("postcode"), "12345");
        test.typeFields(TestBase.driver, By.name("city"), "city");
        WebElement country = TestBase.driver.findElement(By.name("country_code"));
        Select select = new Select(country);
        select.selectByVisibleText("United States");
        test.typeFields(TestBase.driver, By.name("phone"), "123456");
        int number = 10;
        test.typeFields(TestBase.driver, By.name("email"), "email" + number + "@edugen.ru");
        test.passwordAndCreate(TestBase.driver);
        while (test.isElementPresent(TestBase.driver, By.className("fa-exclamation-triangle"))) {
            number = number + 1;
            TestBase.driver.findElement(By.name("email")).clear();
            test.typeFields(TestBase.driver, By.name("email"), "email" + number + "@edugen.ru");
            test.passwordAndCreate(TestBase.driver);
        }
        TestBase.driver.findElement(By.xpath(".//a[contains(text(),'Logout')]")).click();
        test.typeFields(TestBase.driver, By.name("email"), "email" + number + "@edugen.ru");
        test.typeFields(TestBase.driver, By.name("password"), "password");
        TestBase.driver.findElement(By.name("login")).click();
        TestBase.driver.findElement(By.xpath(".//a[contains(text(),'Logout')]")).click();
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
