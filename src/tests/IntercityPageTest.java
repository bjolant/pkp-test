package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.*;

import static junit.framework.TestCase.*;

/**
 * Created by Jolanta on 16.06.2017.
 */
public class IntercityPageTest {

    private static final String BASE_URL = "https://intercity.pl/";
    private static final String DESTINATION = "Warszawa Centralna";
    public static final String START_STATION = "Kraków Główny";
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
    public void shouldOpenCalendar(){
        intercityPage.openCalendar();
        assertTrue(driver.findElement(By.xpath(".//*[contains(@class, \"datepicker-dropdown\")]")).isDisplayed());
    }

    @Test
    public void shouldSearchConnection(){
        intercityPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Płaszów")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        intercityPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Praga")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        intercityPage.enterDate("20170711");

        TicketIntercityPage ticketIntercityPage = intercityPage.searchConnection();
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.getCurrentUrl().startsWith("https://bilet.intercity.pl"));

        assertTrue(ticketIntercityPage.resultsIsNotEmpty());
        assertTrue(ticketIntercityPage.allArrivalsMatch("KRAKOW GL"));
        assertTrue(ticketIntercityPage.allDestinationsMatch("WARSZAWA CEN"));

    }

    @Test
    public void shouldBePossibleBuyTicket() throws InterruptedException {
        intercityPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Płaszów")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        intercityPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Praga")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        intercityPage.enterDate("20170711");
        TicketIntercityPage ticketIntercityPage = intercityPage.searchConnection();
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.getCurrentUrl().startsWith("https://bilet.intercity.pl"));

        assertTrue(ticketIntercityPage.buyTicketButtonsisNotEmpty());

        ticketIntercityPage.buyRandomTicket();
        Thread.sleep(2000);
        assertTrue(ticketIntercityPage.checkIsbuyTicketFormAppear());

    }

    @Test
    public void myAccountShouldGoToLoginPage(){
        LoginPage loginPage = intercityPage.openMyAccount();
        for (String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }

        WebDriverWait wait = new WebDriverWait(driver, 5);
        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().contains("logowanie")));

        assertTrue(loginPage.headerMatches("Logowanie"));
    }

    @Test
    public void unregisteredUsersShouldGoToGuestPage(){
        GuestPage guestPage = intercityPage.openUnregisteredUsers();
        for (String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }
        WebDriverWait wait = new WebDriverWait(driver, 5);
        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().contains("konto_gosc_logowanie")));

        assertTrue(guestPage.headerMatches("Obsługa użytkowników niezarejestrowanych"));
    }

    @Test
    public void shouldSwitchToPeriodTicketPage(){
        PeriodTicketPage periodTicketPage = intercityPage.switchToPeriodTicket();
        for(String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }
        WebDriverWait wait = new WebDriverWait(driver, 5);
        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().contains("bilet_dodatkowy_menu")));

        assertTrue(periodTicketPage.headerMatches("Bilety okresowe"));
    }
}

