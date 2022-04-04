import org.junit.Assert;
import org.junit.Test;

public class ProjectAutomationTests {
    SignInAutomation auto = new SignInAutomation("C:\\Users\\Omer\\Desktop\\chromedriver.exe");

    @Test
    public void getUrlTest() throws InterruptedException {
        //String realAns = auto.url;
        String realAns = "http://automationpractice.com/index.php";
        Assert.assertEquals(realAns, auto.getURL());
    }

    @Test
    public void signInTest() throws InterruptedException{
        String realAns = "My account - My Store";
        Assert.assertEquals(realAns, auto.signIn());
    }
}
