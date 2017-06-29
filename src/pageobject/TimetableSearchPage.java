package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jola on 2017-06-10.
 */
public class TimetableSearchPage extends PageObject{

    @FindBy(xpath = ".//*[@id='from-station']")
    private WebElement fromStation;

    @FindBy(xpath = ".//*[@id='to-station']")
    private WebElement toStation;

    @FindBy(xpath = ".//*[@class = \"data-filed\"]")
    private WebElement datePicker;

    @FindBy(xpath = ".//*[contains(@class, \"next\")]")
    private WebElement nextMonth;

    @FindBy(xpath = ".//*[contains(@class, \"datepicker-month\")]")
    private WebElement month;

    @FindBy(xpath = ".//button[text()='Wybierz dzień']")
    private WebElement selectDate;

    @FindBy(id = "hour")
    private WebElement timePicker;

    @FindBy(xpath = ".//span[text()=\"przyjazdu\"]")
    private WebElement byArrivalTime;

    @FindBy(xpath = ".//span[text()=\"odjazdu\"]")
    private WebElement byDepartureTime;

    @FindBy(xpath = ".//span[text()=\"Połączenia bezpośrednie\"]")
    private WebElement checkboxDirectConnections;

    @FindBy(partialLinkText = "Wyczyść formularz")
    private WebElement newEnquiryButton;

    @FindBy(xpath = ".//*[@id='singlebutton']")
    private WebElement searchButton;

    public TimetableSearchPage(WebDriver driver) {
        super(driver);
    }


    public void enterFromStation(String fromStation){
        this.fromStation.clear();
        this.fromStation.sendKeys(fromStation);
    }

    public String returnFromStation(){
        return this.fromStation.getAttribute("value");
    }

    public void enterToStation(String toStation){
        this.toStation.clear();
        this.toStation.sendKeys(toStation);
    }

    public String returnToStation(){
        return toStation.getAttribute("value");
    }

    public void openDatePicker(){
        datePicker.click();
    }

    public void pickDate(String month, String day) {
        this.datePicker.click();
        while (this.month.getText().equals(month)){
            this.nextMonth.click();
        }
        datePicker.findElement(By.partialLinkText(day)).click();
        this.selectDate.click();
    }

    public void pickHour(String hour){
        this.timePicker.click();
        this.timePicker.sendKeys(hour);
    }

    public void checkDirectConnections(){
        checkboxDirectConnections.click();
    }

    public void newEnquiry() {
        newEnquiryButton.click();
    }

    public TimetablePage search(){
        searchButton.click();
        return new TimetablePage(driver);
    }


}
