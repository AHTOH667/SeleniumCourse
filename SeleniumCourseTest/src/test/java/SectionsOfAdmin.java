import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;


public class SectionsOfAdmin {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startAdminka();
        test.login();
    }

    @Test
    public void sectionsOfAdmin() {
        //Appearence
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Appearence')]"));
        test.clickOnLeftRail(By.id("doc-template"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Template')]")));

        test.clickOnLeftRail(By.id("doc-logotype"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Logotype')]")));


        //Catalog
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Catalog')]"));
        test.clickOnLeftRail(By.id("doc-catalog"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Catalog')]")));

        test.clickOnLeftRail(By.id("doc-product_groups"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Product Groups')]")));

        test.clickOnLeftRail(By.id("doc-option_groups"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Option Groups')]")));

        test.clickOnLeftRail(By.id("doc-manufacturers"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Manufacturers')]")));

        test.clickOnLeftRail(By.id("doc-suppliers"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Suppliers')]")));

        test.clickOnLeftRail(By.id("doc-delivery_statuses"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Delivery Statuses')]")));

        test.clickOnLeftRail(By.id("doc-sold_out_statuses"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Sold Out Statuses')]")));

        test.clickOnLeftRail(By.id("doc-quantity_units"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Quantity Units')]")));

        test.clickOnLeftRail(By.id("doc-csv"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' CSV Import/Export')]")));


        //Countries
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Countries')]"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Countries')]")));


        //Currencies
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Currencies')]"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Currencies')]")));


        //Customers
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Customers')]"));
        test.clickOnLeftRail(By.id("doc-customers"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Customers')]")));

        test.clickOnLeftRail(By.id("doc-csv"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' CSV Import/Export')]")));

        test.clickOnLeftRail(By.id("doc-newsletter"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Newsletter')]")));


        //Geo Zones
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Geo Zones')]"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Geo Zones')]")));


        //Languages
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Languages')]"));
        test.clickOnLeftRail(By.id("doc-languages"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Languages')]")));

        test.clickOnLeftRail(By.id("doc-storage_encoding"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Storage Encoding')]")));


        //Modules
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Modules')]"));
        test.clickOnLeftRail(By.id("doc-jobs"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Job Modules')]")));

        test.clickOnLeftRail(By.id("doc-customer"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Customer Modules')]")));

        test.clickOnLeftRail(By.id("doc-shipping"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Shipping Modules')]")));

        test.clickOnLeftRail(By.id("doc-payment"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Payment Modules')]")));

        test.clickOnLeftRail(By.id("doc-order_total"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Order Total Modules')]")));

        test.clickOnLeftRail(By.id("doc-order_success"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Order Success Modules')]")));

        test.clickOnLeftRail(By.id("doc-order_action"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Order Action Modules')]")));


        //Orders
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Orders')]"));
        test.clickOnLeftRail(By.id("doc-orders"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Orders')]")));

        test.clickOnLeftRail(By.id("doc-order_statuses"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Order Statuses')]")));


        //Pages
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Pages')]"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Pages')]")));


        //Reports
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Reports')]"));
        test.clickOnLeftRail(By.id("doc-monthly_sales"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Monthly Sales')]")));

        test.clickOnLeftRail(By.id("doc-most_sold_products"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Most Sold Products')]")));

        test.clickOnLeftRail(By.id("doc-most_shopping_customers"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Most Shopping Customers')]")));


        //Settings
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Settings')]"));
        test.clickOnLeftRail(By.id("doc-store_info"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));

        test.clickOnLeftRail(By.id("doc-defaults"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));

        test.clickOnLeftRail(By.id("doc-general"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));

        test.clickOnLeftRail(By.id("doc-listings"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));

        test.clickOnLeftRail(By.id("doc-images"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));

        test.clickOnLeftRail(By.id("doc-checkout"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));

        test.clickOnLeftRail(By.id("doc-advanced"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));

        test.clickOnLeftRail(By.id("doc-security"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Settings')]")));


        //Slides
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Slides')]"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Slides')]")));


        //Tax
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Tax')]"));
        test.clickOnLeftRail(By.id("doc-tax_classes"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Tax Classes')]")));

        test.clickOnLeftRail(By.id("doc-tax_rates"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Tax Rates')]")));


        //Translations
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Translations')]"));
        test.clickOnLeftRail(By.id("doc-search"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Search Translations')]")));

        test.clickOnLeftRail(By.id("doc-scan"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Scan Files For Translations')]")));

        test.clickOnLeftRail(By.id("doc-csv"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' CSV Import/Export')]")));


        //Users
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'Users')]"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' Users')]")));


        //vQmods
        test.clickOnLeftRail(By.xpath(".//span[contains(.,'vQmods')]"));
        test.clickOnLeftRail(By.id("doc-vqmods"));
        Assert.assertTrue(isElementPresent(TestBase.driver, By.xpath(".//h1[contains(.,' vQmods')]")));
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @AfterMethod
    public void after() {
        test.quit();
    }

}
