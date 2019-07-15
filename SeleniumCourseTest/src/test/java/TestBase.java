import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class TestBase {


    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public void startAdminka() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/login.php");
    }

    public void startLitecart() {
        if (driver != null) {
            return;
        }
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/en/");
    }

    public void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public void leftRail() {
        List<WebElement> elements = driver.findElements(By.id("app-"));
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).click();
            elements = driver.findElements(By.id("app-"));
            List<WebElement> miniBox = elements.get(i).findElements(By.tagName("li"));
            for (int j = 0; j < miniBox.size(); j++) {
                miniBox.get(j).click();
                Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));
                elements = driver.findElements(By.id("app-"));
                miniBox = elements.get(i).findElements(By.tagName("li"));
            }
        }
    }

    public static boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void clickOnLeftRail(By locator) {
        driver.findElement(locator).click();
    }

    public void checkStickers(String idBox) {
        WebElement box = driver.findElement(By.id(idBox));
        List<WebElement> elements = box.findElements(By.tagName("li"));
        for (int i = 0; i < elements.size(); i++) {
            Assert.assertTrue(isChildDisplayed(elements.get(i), "sticker"));
                    //|| isChildDisplayed(elements.get(i), "sale"));
        }
    }

    private boolean isChildDisplayed(WebElement element, String cssClass) {
        try {
            return element.findElement(By.className(cssClass)).isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void countriesList () {
        WebElement table = driver.findElement(By.className("dataTable"));
        List<WebElement> rows = table.findElements(By.className("row"));
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            names1.add(rows.get(i).findElement(By.xpath("./td[5]/a")).getText());
            names2.add(rows.get(i).findElement(By.xpath("./td[5]/a")).getText());
        }
        Collections.sort(names1);
        Assert.assertEquals(names2, names1);
    }

    public void zonesList () {
        WebElement table = driver.findElement(By.className("dataTable"));
        List<WebElement> rows = table.findElements(By.className("row"));
        for (int i = 0; i < rows.size(); i++) {
            String zones = rows.get(i).findElement(By.xpath("./td[6]")).getText();
            if (!zones.equals("0")) {
                rows.get(i).findElement(By.xpath("./td[5]/a")).click();
                WebElement table2 = driver.findElement(By.className("dataTable"));
                List<WebElement> names3 = table2.findElements(By.cssSelector("td[name^='zones']"));
                List<String> namesIn = names3.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
                List<String> namesIn2 = namesIn;
                Collections.sort(namesIn);
                Assert.assertEquals(namesIn2, namesIn);
                clickOnLeftRail(By.xpath(".//span[contains(.,'Countries')]"));
                table = driver.findElement(By.className("dataTable"));
                rows = table.findElements(By.className("row"));
            }
        }
    }

    public void quit() {
        driver.quit();
    }
}
