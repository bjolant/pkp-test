package tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.RealEstatePage;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Jola on 2017-06-21.
 */
public class RealEstatePageTest {

    private static final String BASE_URL = "http://pkpsa.pl/nieruchomosci/";
    private static WebDriver driver;
    private RealEstatePage realEstatePage;

    @BeforeClass
    public static void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void setUp() {
        driver.get(BASE_URL);
        realEstatePage = new RealEstatePage(driver);
    }

    //@AfterClass
    //public static void tearDown() {
    //    driver.close();
    //}

    @Test
    public void shouldGoToSaleOffers(){
        realEstatePage.goToSaleOffers();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().contains("sprzedaz/oferty")));
    }

    @Test
    public void shouldGoToRentOffers(){
        realEstatePage.goToRentOffers();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().contains("wynajem/oferty")));
    }

}
