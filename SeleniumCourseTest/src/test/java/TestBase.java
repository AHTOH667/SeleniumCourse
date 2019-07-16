import org.openqa.selenium.*;
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
                List<String> namesZone1 = names3.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
                List<String> namesZone2 = namesZone1;
                Collections.sort(namesZone1);
                Assert.assertEquals(namesZone2, namesZone1);
                clickOnLeftRail(By.xpath(".//span[contains(.,'Countries')]"));
                table = driver.findElement(By.className("dataTable"));
                rows = table.findElements(By.className("row"));
            }
        }
    }

    public void elementsOfGoods() {
        WebElement box = driver.findElement(By.id("box-campaigns"));
        List<WebElement> goods = box.findElements(By.tagName("li"));
        for (int i = 0; i < goods.size(); i++) {
            String title = goods.get(i).findElement(By.className("name")).getText();
            String fullPrice = goods.get(i).findElement(By.className("regular-price")).getText();
            String salePrice = goods.get(i).findElement(By.className("campaign-price")).getText();
            checkGrayColors(goods.get(i), By.className("regular-price"));
            checkRedColors(goods.get(i), By.className("campaign-price"));
            checkStylePrice(goods.get(i), By.className("regular-price"), "S");
            checkStylePrice(goods.get(i), By.className("campaign-price"), "STRONG");
            checkSizeOfElements(goods.get(i), By.className("regular-price"), By.className("campaign-price"));

            goods.get(i).click();
            Assert.assertEquals(title, driver.findElement(By.tagName("h1")).getText());
            WebElement info = driver.findElement(By.className("information"));
            Assert.assertEquals(fullPrice, info.findElement(By.cssSelector(".regular-price")).getText());
            Assert.assertEquals(salePrice, info.findElement(By.cssSelector(".campaign-price")).getText());
            checkGrayColors(info, By.cssSelector(".regular-price"));
            checkRedColors(info, By.cssSelector(".campaign-price"));
            checkStylePrice(info, By.className("regular-price"), "S");
            checkStylePrice(info, By.className("campaign-price"), "STRONG");
            checkSizeOfElements(info, By.className("regular-price"), By.className("campaign-price"));
        }
    }

    public void checkGrayColors(WebElement element, By locator) {
        String colorValues[] = checkColors(element, locator);
        Assert.assertEquals(colorValues[1], colorValues[2], colorValues[0]);
    }

    public void checkRedColors (WebElement element, By locator) {
        String colorValues[] = checkColors(element, locator);
        Assert.assertEquals(colorValues[1],colorValues[2], "0");
    }

    public String[] checkColors(WebElement element, By locator) {
        String color = element.findElement(locator).getCssValue("color");
        String[] colorValues = color.replace("rgba(", "")
                .replace(")", "")
                .split(", ");
        return colorValues;
    }

    public void checkStylePrice(WebElement element, By locator, String value) {
        Assert.assertEquals(element.findElement(locator).getAttribute("tagName"), value);
    }

    public void checkSizeOfElements(WebElement element, By locator, By locator2) {
        Dimension sizeFull = element.findElement(locator).getSize();
        Dimension sizeSale = element.findElement(locator2).getSize();
        Assert.assertTrue(sizeSale.getHeight() > sizeFull.getHeight() && sizeSale.getWidth() > sizeFull.getWidth());
    }

    public void quit() {
        driver.quit();
    }
}
