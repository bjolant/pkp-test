package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.IntercityPage;
import pageobject.LoginPage;
import pageobject.MyAccountPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


/**
 * Created by Jola on 2017-06-21.
 */
public class LoginPageTest {
    private static final String BASE_URL = "https://bilet.intercity.pl/";
    private static WebDriver driver;
    private IntercityPage intercityPage;

    @BeforeClass
    public static void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void setUp() {
        driver.get(BASE_URL);
        intercityPage = new IntercityPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Test
    public void userShouldBeAbleToLoginAndLogout(){
        String login = "AGHtest";
        String password = "AGHtest1";

        LoginPage loginPage = intercityPage.openMyAccount();
        for (String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }

        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        MyAccountPage myAccountPage = loginPage.submitToMyAccount();

        myAccountPage.logout();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(myAccountPage.infoTextMaches("Wylogowano"));
        myAccountPage.submitLogout();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().startsWith("https://www.intercity.pl/")));
    }


}
