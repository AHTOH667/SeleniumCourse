package CheckBinTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Application {


    private TestData data;
    private TestBaseForBin test;
    private HomePage homePage;
    private GoodsPage goodsPage;
    private BinPage bin;
    private WebDriverWait wait;
    private WebDriver driver;

    public Application() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        data = new TestData();
        test = new TestBaseForBin();
        homePage = new HomePage(driver);
        goodsPage = new GoodsPage(driver);
        bin = new BinPage(driver);
    }

    public void startLitecartFallback() {
        if (driver != null) {
            driver.get("http://litecart.stqa.ru/en/");
            return;
        }
        driver = new EventFiringWebDriver(new ChromeDriver());
        wait = new WebDriverWait(driver, 30);
        //driver = new EventFiringWebDriver(new FirefoxDriver());
        //driver = new EventFiringWebDriver(new InternetExplorerDriver());
        //driver.register(new MyListener());
        driver.get("http://litecart.stqa.ru/en/");
    }

    public void addGoodsInBin() {
        for (int i = 0; i < data.numberOfGoods; i++) {
            homePage.selectFirstDuck();
            goodsPage.selectSmallSize();
            goodsPage.addToCart(i);
            if (i < data.numberOfGoods - 1) {
                homePage.open();
            }
        }
    }

    public void removeAllGoods() {
        bin.followToBinPage();
        while (! test.isElementPresent(
                driver, By.xpath(".//em[contains(text(),'There are no items in your cart.')]"))) {
            bin.stopCarousel();
            bin.removeAll();
        }
    }

    public void quit() {
        driver.quit();

        //It is used for get Version for the WebDriver
        //System.out.println(driver.getCapabilities().getVersion());
    }
}