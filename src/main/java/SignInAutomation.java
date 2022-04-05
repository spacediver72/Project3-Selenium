import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class SignInAutomation {
    WebDriver driver;
    String url = "http://automationpractice.com/index.php";
    Random rand = new Random();

    public SignInAutomation(String driverLocation) {
        System.setProperty("webdriver.chrome.driver",driverLocation);
        this.driver = new ChromeDriver();
    }

    public String getURL() throws InterruptedException {
        driver.get(url);
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("\n\n\nURL is\n" + currentUrl +"\n\n\n");
        driver.close();
        return currentUrl;
    }

    public String signIn() throws InterruptedException {
        int pause = 1000 * 5;

        // required fields
        String nameF = "Dean";// 1 char
        String nameL = "Amit";// 1 char
        //String email = "deantnt@gmail.com";// d@e.f
        String email = emailGen();
        String phone = "0526671454";// 10 digits
        String password = "12345";// 5 char
        String city = "Dallas";// 1 char
        String zip = "54321";// 5 digits

        // to Home page
        driver.get(url);
        Thread.sleep(pause/2);// wait for it...
        //driver.manage().window().maximize();
        click("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");// Sign in
        Thread.sleep(pause);// loading...

        // Login
        type1("//*[@id=\"email_create\"]",email);// email address
        click("//*[@id=\"SubmitCreate\"]/span");// Create an account
        Thread.sleep(pause);// loading...

        // Create an Account

        //// YOUR PERSONAL INFORMATION

        // Gender
        click("//*[@id=\"id_gender1\"]");// Title #Mr
        //click("//*[@id=\"id_gender2\"]");// Title #Mrs

        // fields
        type1("//*[@id=\"customer_firstname\"]",nameF);// First name
        type1("//*[@id=\"customer_lastname\"]",nameL);// Last name
        //type1("//*[@id=\"email\"]",email);// Email, fill from Login page
        type1("//*[@id=\"passwd\"]",password);// Password

        // Date of Birth
        click("//*[@id=\"days\"]");// Date of Birth #Day
        click("//*[@id=\"days\"]/option[7]");// Date of Birth #Day6
        click("//*[@id=\"months\"]");// Date of Birth #Month
        click("//*[@id=\"months\"]/option[6]");// Date of Birth #Month04
        click("//*[@id=\"years\"]");// Date of Birth #Year
        click("//*[@id=\"years\"]/option[34]");// Date of Birth #Year1990

        // Additional service
        click("//*[@id=\"newsletter\"]");// Sign up for our newsletter!
        click("//*[@id=\"optin\"]");// Receive special offers from our partners!

        //// YOUR ADDRESS

        // fields
        //type1("//*[@id=\"firstname\"]",nameF);// First name, fill from Your Info
        //type1("//*[@id=\"lastname\"]",nameL);// Last name, fill from Your Info
        type1("//*[@id=\"company\"]","NBA");// Company
        type1("//*[@id=\"address1\"]","address");// Address
        type1("//*[@id=\"address2\"]","address2");// Address 2
        type1("//*[@id=\"city\"]",city);// City

        // State
        click("//*[@id=\"id_state\"]");// State
        click("//*[@id=\"id_state\"]/option[46]");// State #Texas

        type1("//*[@id=\"postcode\"]",zip);// Zip/Postal Code

        // Country
        click("//*[@id=\"id_country\"]");// Country
        click("//*[@id=\"id_country\"]/option[2]");// Country #USA Only

        // Fields
        type1("//*[@id=\"other\"]","info");// Additional information
        type1("//*[@id=\"phone\"]",phone);// Home phone
        type1("//*[@id=\"phone_mobile\"]","972"+ phone);// Mobile phone
        type1("//*[@id=\"alias\"]"," is secret");// Assign an address alias for future reference, append to placeholder
        Thread.sleep(pause*2);// wait for it...

        // Register B
        click("//*[@id=\"submitAccount\"]/span");// Register

        Thread.sleep(pause);// loading...
        String title = driver.getTitle();
        System.out.println("\n\n\nTitle is\n" + title +"\n\n\n");
        driver.close();
        return title;
    }

    private void click(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }

    private void type1(String xpath, String str){
        driver.findElement(By.xpath(xpath)).sendKeys(str);
    }

    private String emailGen(){
        String username = "";
        String domain = "gmail.com";

        for (int i = 0; i < 8; i++) {
            if (i == 0) username += nextChar();
            else
            if (rand.nextBoolean()) username += nextChar();
            else username += rand.nextInt(10);
        }
        return username +"@"+ domain;
    }

    private String nextChar(){
        String res = "";
        String abc = "abcdefghijklmnopqrstuvwxyz";
        res += abc.charAt(rand.nextInt(abc.length()));
        return res;
    }
}