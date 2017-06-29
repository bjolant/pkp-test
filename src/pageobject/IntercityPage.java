package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jola on 2017-06-15.
 */
public class IntercityPage extends PageObject {

    @FindBy(xpath = ".//*[@id='exampleInputEmail1']")
    private WebElement fromStation;

    @FindBy(xpath = ".//*[@id='exampleInputPassword1']")
    private WebElement toStation;

    @FindBy(xpath = ".//*[@id='ic-seek-date']")
    private WebElement dataPicker;

    @FindBy(xpath = ".//*[@id='ic-seek-time']")
    private WebElement timePicker;

    @FindBy(xpath = ".//*[@type = \"submit\"]")
    private WebElement searchButton;

    @FindBy(xpath = ".//*[@title = \"Kliknięcie powoduję uruchomienie kalendarza\"]")
    private WebElement calendar;

    @FindBy(xpath = ".//a[.='Moje konto']")
    private WebElement myAccount;

    @FindBy(xpath = ".//a[.='Użytkownicy niezarejestrowani']")
    private WebElement unregisteredUsers;

    @FindBy(xpath = ".//*[@class = \"panel-heading clearfix aside-period-ticket\"]/h2")
    private WebElement periodTicket;

    public IntercityPage(WebDriver driver) {
        super(driver);
    }

    public void enterToStation(String toStation){
        this.toStation.clear();
        this.toStation.sendKeys(toStation);
    }

    public void enterFromStation(String fromStation){
        this.fromStation.clear();
        this.fromStation.sendKeys(fromStation);
    }

    public void enterDate(String date){
        this.dataPicker.clear();
        this.dataPicker.sendKeys(date);
    }

    public void enterTime(String time){
        this.timePicker.clear();
        this.timePicker.sendKeys(time);
    }

    public void openCalendar(){
        calendar.click();
    }

    public TicketIntercityPage searchConnection(){
        searchButton.click();
        return new TicketIntercityPage(driver);
    }

    public LoginPage openMyAccount(){
        myAccount.click();
        return new LoginPage(driver);
    }

    public GuestPage openUnregisteredUsers(){
        unregisteredUsers.click();
        return new GuestPage(driver);
    }

    public PeriodTicketPage switchToPeriodTicket(){
        periodTicket.click();
        return new PeriodTicketPage(driver);

    }
}
