import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.stream.Collectors;


public class TestBase {


    static EventFiringWebDriver driver;

    public static EventFiringWebDriver getDriver() {
        return driver;
    }

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
        }
    }

    public void startAdminka() {
        if (driver != null) {
            return;
        }
        //Capabilities for the logs
        DesiredCapabilities cap = new DesiredCapabilities();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


        driver = new EventFiringWebDriver(new ChromeDriver(cap));
        //driver = new EventFiringWebDriver(new FirefoxDriver());
        //driver = new EventFiringWebDriver(new InternetExplorerDriver());
        driver.register(new MyListener());
        driver.get("http://localhost/litecart/admin/login.php");
    }

    public void startLitecart() {
        if (driver != null) {
            return;
        }
        driver = new EventFiringWebDriver(new ChromeDriver());
        //driver = new EventFiringWebDriver(new FirefoxDriver());
        //driver = new EventFiringWebDriver(new InternetExplorerDriver());
        //driver.register(new MyListener());
        driver.get("http://localhost/litecart/en/");
    }

    public void startLitecartFallback() {
        if (driver != null) {
            return;
        }
        driver = new EventFiringWebDriver(new ChromeDriver());
        //driver = new EventFiringWebDriver(new FirefoxDriver());
        //driver = new EventFiringWebDriver(new InternetExplorerDriver());
        //driver.register(new MyListener());
        driver.get("http://litecart.stqa.ru/en/");
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

    public boolean isElementPresent(WebDriver driver, By locator) {
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
        for (WebElement element : elements) {
            Assert.assertTrue(isChildDisplayed(element));
            //|| isChildDisplayed(elements.get(i), "sale"));
        }
    }

    private boolean isChildDisplayed(WebElement element) {
        try {
            return element.findElement(By.className("sticker")).isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void countriesList () {
        WebElement table = driver.findElement(By.className("dataTable"));
        List<WebElement> rows = table.findElements(By.className("row"));
        List<String> names1 = new ArrayList<>();
        List<String> names2 = new ArrayList<>();
        for (WebElement row : rows) {
            names1.add(row.findElement(By.xpath("./td[5]/a")).getText());
            names2.add(row.findElement(By.xpath("./td[5]/a")).getText());
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

    public void elementsOfGoods(WebDriver driver, List<WebElement> goods) {
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

            driver.get("http://localhost/litecart/en/");
            WebElement box = driver.findElement(By.id("box-campaigns"));
            goods = box.findElements(By.tagName("li"));
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

    public void typeFields(WebDriver driver, By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

    public void passwordAndCreate(WebDriver driver) {
        typeFields(driver, By.name("password"), "password");
        typeFields(driver, By.name("confirmed_password"), "password");
        driver.findElement(By.name("create_account")).click();
    }

    /*public void setDatepicker(WebDriver driver, String cssSelector, String date) {
        new WebDriverWait(driver, 30000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
        JavascriptExecutor.class.cast(driver).executeScript(
                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
    }*/

    //It is used on the waiting for new window
    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    public void quit() {
        driver.quit();
    }
}