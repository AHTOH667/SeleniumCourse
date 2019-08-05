package CheckBinTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class CheckBin {


    private Application app = new Application();

    @BeforeMethod
    public void before() {
        app.startLitecartFallback();
    }


    @Test
    public void test() {
        app.addGoodsInBin();
        app.removeAllGoods();
    }


    @AfterMethod
    public void after() {
        app.quit();
    }
}