import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CheckStickers {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startLitecart();
    }

    @Test
    public void test() {
        test.checkStickers("box-most-popular");
        test.checkStickers("box-campaigns");
        test.checkStickers("box-latest-products");
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
