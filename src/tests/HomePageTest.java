package tests;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.HomePage;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Jolanta Burek on 2017-06-04.
 */
public class HomePageTest {

    private static WebDriver driver;
    private HomePage homePage;
    private static final String BASE_URL = "http://pkp.pl/";

    @BeforeClass
    public static void setUpClass(){
        System.setProperty("webdriver.gecko.driver", "C:/chromedriver.exe");
        driver =  new ChromeDriver();
    }

    @Before
    public void setUp(){
        driver.get(BASE_URL);
        homePage = new HomePage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Test
    public void shouldRedirectToPkpsa() {
        homePage.goToPkp();
        WebDriverWait wait = new WebDriverWait(driver, 2);

        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().startsWith("http://pkpsa.pl")));
    }

    @Test
    public void shouldRedirectToRozkladPkp(){
        homePage.goToRozkladPkp();
        WebDriverWait wait = new WebDriverWait(driver, 2);

        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().startsWith("http://rozklad-pkp.pl")));
    }

    @Test
    public void shouldRedirectToIntercity(){
        homePage.goToIntercity();
        WebDriverWait wait = new WebDriverWait(driver, 2);

        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().startsWith("https://www.intercity.pl")));
    }

    @Test
    public void shouldRedirectToNaszeDworce(){
        homePage.goToNaszeDworce();
        WebDriverWait wait = new WebDriverWait(driver, 2);

        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().startsWith("http://pkpsa.pl/pkpsa/nasze-dworce")));
    }

    @Test
    public void shouldRedirectToNieruchomosci(){
        homePage.goToNieruchomosci();
        WebDriverWait wait = new WebDriverWait(driver, 2);

        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().startsWith("http://pkpsa.pl/nieruchomosci")));
    }

}

