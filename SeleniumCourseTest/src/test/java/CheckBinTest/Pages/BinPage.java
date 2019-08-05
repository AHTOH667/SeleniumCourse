package CheckBinTest.Pages;
import CheckBinTest.Tests.TestBaseForBin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;


public class BinPage extends Page {


    public BinPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    private TestBaseForBin test = new TestBaseForBin();


    @FindBy(id = "cart-wrapper")
    public WebElement binIcon;

    @FindBy(xpath = ".//li[contains(@class, 'shortcut')]")
    public List<WebElement> goodsIcons;

    @FindBy(name = "remove_cart_item")
    public List<WebElement> removeButton;

    @FindBy(className = "dataTable")
    public WebElement order;


    public BinPage followToBinPage() {
        binIcon.click();
        return this;
    }

    public void removeAllButton() {
        while (! test.isElementPresent(
                driver, By.xpath(".//em[contains(text(),'There are no items in your cart.')]"))) {
            stopCarousel().removeAll();
        }
    }

    public BinPage stopCarousel() {
        System.out.println("goods = " + goodsIcons.size());
        if (goodsIcons.size() > 0) {
            goodsIcons.get(0).click();
        }
        return this;
    }

    public void removeAll() {
        System.out.println("Button = " + removeButton.size());
        for (WebElement webElement : removeButton) {
            if (webElement.isDisplayed()) {
                webElement.click();
                List<WebElement> rows = order.findElements(By.className("item"));
                int numberOfRows = rows.size();
                wait.until(numberOfElementsToBeLessThan(By.cssSelector(".dataTable .item"), numberOfRows));
                break;
            }
        }
    }
}