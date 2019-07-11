import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.start();
    }


    @Test
    public void login() {
        test.login();
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}
