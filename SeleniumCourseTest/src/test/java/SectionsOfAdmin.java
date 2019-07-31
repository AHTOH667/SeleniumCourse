import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SectionsOfAdmin {


    private TestBase test = new TestBase();

    @BeforeMethod
    public void before() {
        test.startAdminka();
        test.login();
    }


    @Test
    public void sectionsOfAdmin() {
        test.leftRail();
    }


    @AfterMethod
    public void after() {
        test.quit();
    }
}