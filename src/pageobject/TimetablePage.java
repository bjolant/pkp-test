package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Jola on 2017-06-12.
 */
public class TimetablePage extends PageObject{

    @FindBy(xpath = ".//*[@class=\"row result-container\"]")
    private WebElement resultTable;

    @FindBy(xpath = ".//tr[contains(@class, 'selected')]")
    private WebElement selectedRow;

    @FindBy(xpath = ".//table//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = ".//table//tr//td[2]")
    private List<WebElement> fromToColumn;

    @FindBy(xpath = ".//table//tr//td[6]")
    private List<WebElement> transferNumbers;

    @FindBy(xpath = ".//table//tr//td[4]")
    private List<WebElement> time;

    @FindBy(xpath = ".//*[contains(@class, \"buy-ticket\")]")
    private List<WebElement> buyTicketButtons;

    @FindBy(xpath = ".//tr[contains(@class, 'selected')]//td[8]")
    private WebElement buyTicketSelectedRow;

    @FindBy(xpath = ".//span[text() = \"Zmień kryteria wyszukiwania\"]")
    private WebElement changeCriteriaButton;

    @FindBy(xpath = ".//*[@class = \"return-connections\"]")
    private WebElement returnConnectionButton;

    @FindBy(xpath = ".//tr[contains(@class, 'selected')]//td[1]")
    private WebElement selectedExpand;

    @FindBy(xpath = ".//*[@class = \"do-print\"]")
    private WebElement DetailsForAllButton;

    public TimetablePage(WebDriver driver) {
        super(driver);
    }

    public boolean isResultListEmpty(){
        return !tableRows.isEmpty();
    }

    public boolean allDeparturesMatch(String departure) {
        for (WebElement fromToCell: fromToColumn){
            String[] fromToDestination = fromToCell.getText().split("\n");
            if (!fromToDestination[0].equals(departure)) return false;
        }
        return true;
    }

    public boolean allArrivalsMatch(String arrival) {
        for (WebElement fromToCell: fromToColumn){
            String[] fromToDestination = fromToCell.getText().split("\n");
            if (!fromToDestination[1].equals(arrival)) return false;
        }
        return true;
    }

    public boolean isDirectConnection () {
        for (WebElement transferNumber: transferNumbers){
            if(!transferNumber.getText().equals("0")) return false;
        }
        return true;
    }

    public TimetableSearchPage returnConnection(){
        returnConnectionButton.click();
        return new TimetableSearchPage(driver);
    }

    public TimetableSearchPage changeCriteria(){
        changeCriteriaButton.click();
        return new TimetableSearchPage(driver);
    }

    public int returnNumberOfTableRows(){
        return tableRows.size();
    }

    public int returnNumberOfBuyTicketButtons(){
        return buyTicketButtons.size();
    }

    public TicketIntercityPage buyTicket(){
        buyTicketSelectedRow.click();
        return new TicketIntercityPage(driver);
    }

    public ExpandPage expandSearch(){
        selectedExpand.click();
        return new ExpandPage(driver);
    }

    public ExpandPage expandAllSearch(){
        DetailsForAllButton.click();
        return new ExpandPage(driver);
    }






}
