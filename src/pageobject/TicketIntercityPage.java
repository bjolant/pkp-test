package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

/**
 * Created by Jola on 2017-06-13.
 */
public class TicketIntercityPage extends PageObject {

    @FindBy(xpath = ".//*[@class=\"train_box ramka_eip\"]")
    private List<WebElement> searchResults;

    @FindBy(xpath = ".//*[@class=\"stacje do_prawej\"]")
    private List<WebElement> fromStations;

    @FindBy(xpath = ".//*[@class=\"stacje do_lewej\"]")
    private List<WebElement> toStations;

    @FindBy(xpath = ".//*[@class=\"przycisk_wybierz_relacje choose_button_ralation\"]")
    private List<WebElement> buyTicketButtons;

    @FindBy(xpath = ".//*[@id='zakup_biletu_form']")
    private WebElement buyTicketForm;

    public TicketIntercityPage(WebDriver driver) {
        super(driver);
    }

    public boolean resultsIsNotEmpty(){
        if(searchResults.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean allArrivalsMatch(String arrival) {
        for (WebElement fromStation: fromStations){
            if (!fromStation.getText().equals(arrival)) {
                return false;
            }
        }
        return true;
    }

    public boolean allDestinationsMatch(String destination) {
        for (WebElement toStation : toStations){
            if (!toStation.getText().equals(destination)) {
                return false;
            }
        }
        return true;
    }

    public boolean buyTicketButtonsisNotEmpty(){
        if(buyTicketButtons.isEmpty()){
            return false;
        }
        return true;
    }

    public void buyRandomTicket(){
        buyTicketButtons.get(0).click();
    }

    public boolean checkIsbuyTicketFormAppear(){
        if(!buyTicketForm.isDisplayed()){
            return false;
        }
        return true;
    }
}
