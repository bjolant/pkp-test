package tests;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.ExpandPage;
import pageobject.TicketIntercityPage;
import pageobject.TimetablePage;
import pageobject.TimetableSearchPage;
import static junit.framework.TestCase.*;


/**
 * Created by Jola on 2017-06-06.
 */
public class TimetableSearchPageTest {

    private static final String BASE_URL = "http://rozklad-pkp.pl/";
    private static final String DESTINATION = "Warszawa Centralna";
    public static final String START_STATION = "Kraków Główny";
    private static WebDriver driver;
    private TimetableSearchPage timetableSearchPage;

    @BeforeClass
    public static void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void setUp() {
        driver.get(BASE_URL);
        timetableSearchPage = new TimetableSearchPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Test
    public void shouldOpenDatePicker(){
        timetableSearchPage.openDatePicker();
        assertTrue(driver.findElement(By.xpath(".//*[@class='date-wrapper']")).isDisplayed());
    }

    @Test
    public void newEnquiryShouldClearFields(){
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        timetableSearchPage.newEnquiry();

        assertEquals("Wpisz nazwę stacji", timetableSearchPage.returnFromStation());
        assertEquals("Wpisz nazwę stacji", timetableSearchPage.returnToStation());
    }

    @Test
    public void shouldSearchConnection(){
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        TimetablePage timetablePage = timetableSearchPage.search();

        assertTrue(timetablePage.isResultListEmpty());
        assertTrue(timetablePage.allArrivalsMatch(START_STATION));
        assertTrue(timetablePage.allDeparturesMatch(DESTINATION));

    }

    @Test
    public void shouldSearchDirectConnection() {
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        timetableSearchPage.checkDirectConnections();
        TimetablePage timetablePage = timetableSearchPage.search();

        assertTrue(timetablePage.isResultListEmpty());
        assertTrue(timetablePage.allArrivalsMatch(START_STATION));
        assertTrue(timetablePage.allDeparturesMatch(DESTINATION));
        assertTrue(timetablePage.isDirectConnection());

    }

    @Test
    public void shouldSearchReturnConnection() throws InterruptedException {
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        TimetablePage timetablePage = timetableSearchPage.search();
        Thread.sleep(3000);
        timetablePage.returnConnection();
        assertEquals(DESTINATION, timetableSearchPage.returnFromStation());
        assertEquals(START_STATION, timetableSearchPage.returnToStation());

    }

    @Test
    public void shouldBeAbleToChangeSearchCriteria(){
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        TimetablePage timetablePage = timetableSearchPage.search();

        assertTrue(timetablePage.isResultListEmpty());

        timetablePage.changeCriteria();

        assertEquals(START_STATION, timetableSearchPage.returnFromStation());
        assertEquals(DESTINATION, timetableSearchPage.returnToStation());

    }

    @Test
    public void shouldBuyTicketButtonsExist (){
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        timetableSearchPage.checkDirectConnections();
        TimetablePage timetablePage = timetableSearchPage.search();

        assertTrue(timetablePage.isResultListEmpty());
        assertEquals(timetablePage.returnNumberOfTableRows(), timetablePage.returnNumberOfBuyTicketButtons());
    }

    @Test
    public void buyTicketShouldGoToIntercity() throws InterruptedException {
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        timetableSearchPage.checkDirectConnections();
        TimetablePage timetablePage = timetableSearchPage.search();
        Thread.sleep(5000);
        assertTrue(timetablePage.isResultListEmpty());

        timetablePage.buyTicket();
        for (String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }
        WebDriverWait wait = new WebDriverWait(driver, 5);

        assertTrue(wait.until(webDriver -> webDriver.getCurrentUrl().startsWith("https://bilet.intercity.pl")));
    }

    @Test
    public void expandShouldShowDetails (){
        timetableSearchPage.enterFromStation("Krakow");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Kraków Batowice")).isDisplayed());
        driver.findElement(By.partialLinkText(START_STATION)).click();

        timetableSearchPage.enterToStation("Warszawa");
        new WebDriverWait(driver, 3).until(webDriver -> webDriver.findElement(By.partialLinkText("Warszawa Wschodnia")).isDisplayed());
        driver.findElement(By.partialLinkText(DESTINATION)).click();

        TimetablePage timetablePage = timetableSearchPage.search();

        assertTrue(timetablePage.isResultListEmpty());

        ExpandPage expandPage = timetablePage.expandSearch();
        expandPage.expandResultIsPresent();
    }



}
