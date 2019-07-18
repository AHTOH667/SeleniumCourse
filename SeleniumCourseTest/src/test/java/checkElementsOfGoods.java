import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class checkElementsOfGoods {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startLitecart();
    }


    @Test
    public void checkElements() {
        test.elementsOfGoods();
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
