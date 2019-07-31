import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.nio.file.Paths;


public class AddNewProduct {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startAdminka();
        test.login();
    }


    @Test
    public void checkAddProduct() {
        //General tab
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Catalog')]"));
        TestBase.driver.findElement(By.xpath(".//a[contains(text(),'Add New Product')]")).click();
        test.typeFields(TestBase.driver, By.name("name[en]"), "name");
        test.typeFields(TestBase.driver, By.name("code"), "code");
        TestBase.driver.findElement(By.xpath(".//div[contains(@class, 'input-wrapper')]//tr[4]/td")).click();
        TestBase.driver.findElement(By.name("quantity")).clear();
        test.typeFields(TestBase.driver, By.name("quantity"), "5");
        TestBase.driver.findElement(By.name("new_images[]"))
                .sendKeys(Paths.get("src", "test", "resources", "J.jpg").toAbsolutePath().toString());

        //Не получается использовать функцию setDatepicker

        //TestBase.driver.switchTo().frame(
        //        TestBase.driver.findElement(By.cssSelector("[name=date_valid_from]")));
        //test.setDatepicker(TestBase.driver, "[name=date_valid_from]", "10/06/2019");

        test.typeFields(TestBase.driver, By.name("date_valid_from"), "01011999");
        test.typeFields(TestBase.driver, By.name("date_valid_to"), "01012022");
        TestBase.driver.findElement(By.xpath(".//a[contains(text(),'Information')]")).click();

        //Information tab
        WebElement Manufacturer = TestBase.driver.findElement(By.name("manufacturer_id"));
        Select select = new Select(Manufacturer);
        select.selectByVisibleText("ACME Corp.");
        test.typeFields(TestBase.driver, By.name("keywords"), "keywords");
        test.typeFields(TestBase.driver, By.name("short_description[en]"), "short");
        test.typeFields(TestBase.driver, By.className("trumbowyg-editor"), "description");
        test.typeFields(TestBase.driver, By.name("head_title[en]"), "title");
        test.typeFields(TestBase.driver, By.name("meta_description[en]"), "meta");
        TestBase.driver.findElement(By.xpath(".//a[contains(text(),'Prices')]")).click();

        //Prices tab
        WebElement Price = TestBase.driver.findElement(By.name("purchase_price_currency_code"));
        Select select2 = new Select(Price);
        select2.selectByVisibleText("US Dollars");
        TestBase.driver.findElement(By.name("purchase_price")).clear();
        test.typeFields(TestBase.driver, By.name("purchase_price"), "5");
        TestBase.driver.findElement(By.name("gross_prices[USD]")).clear();
        test.typeFields(TestBase.driver, By.name("gross_prices[USD]"), "12");
        TestBase.driver.findElement(By.name("gross_prices[EUR]")).clear();
        test.typeFields(TestBase.driver, By.name("gross_prices[EUR]"), "10");
        TestBase.driver.findElement(By.name("save")).click();
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}