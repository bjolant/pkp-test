package tests;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.LoginPage;
import pageobject.PeriodTicketPage;

import static org.junit.Assert.*;


/**
 * Created by Jola on 2017-06-20.
 */
public class PeriodTicketPageTest {

    private static final String BASE_URL = "https://bilet.intercity.pl/bilet_dodatkowy_menu";
    private static WebDriver driver;
    private PeriodTicketPage periodTicketPage;

    @BeforeClass
    public static void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void setUp() {
        driver.get(BASE_URL);
        periodTicketPage = new PeriodTicketPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Test
    public void shouldOpenInfoTip() throws InterruptedException {
        periodTicketPage.hoverInfoIcon();
        Thread.sleep(1000);
        assertTrue(periodTicketPage.checkIsInfoTipExist());
    }

    @Test
    public void shouldBePossibleToBuyPeriodTicket() throws InterruptedException {
        assertTrue(periodTicketPage.checkIsTicketOptionsIsNotEmpty());

        assertFalse(periodTicketPage.checkIsButtonIsEnabled());
        periodTicketPage.selectTicketOption(0);
        //assertTrue(periodTicketPage.checkTicketOptionRadioIsSelected(0));
        assertTrue(periodTicketPage.checkIsButtonIsEnabled());

        periodTicketPage.clickNext();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().contains("bilet_rodzaj")));

        assertFalse(periodTicketPage.checkIsButton2IsEnabled());
        periodTicketPage.selectFirstClass();
        assertTrue(periodTicketPage.checkIsButton2IsEnabled());

        LoginPage loginPage = periodTicketPage.clickNext2();

        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().contains("logowanie")));

        assertTrue(loginPage.headerMatches("Logowanie"));

    }

}
