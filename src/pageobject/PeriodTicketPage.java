package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Jola on 2017-06-20.
 */
public class PeriodTicketPage extends PageObject {

    @FindBy(xpath = ".//*[@class = \"content\"]/h1")
    private WebElement header;

    @FindBy(xpath = ".//*[@class=\"info_icon\"]")
    private List<WebElement> infoIcons;

    @FindBy(xpath = ".//*[@id='powerTip']")
    private WebElement infoTip;

    @FindBy(xpath = ".//*[@class=\"opcje_etykiety\"]")
    private List<WebElement> ticketOptions;

    @FindBy(xpath = ".//*[@class=\"input_accept_fix_rwd\"]")
    private List<WebElement> ticketOptionRadios;

    @FindBy(xpath = ".//*[@id='submitMenu']")
    private WebElement nextButton;

    @FindBy(xpath = ".//*[@id='cena_1']")
    private WebElement firstClass;

    @FindBy(xpath = ".//*[@id='submit_cena']")
    private WebElement nextButton2;

    public PeriodTicketPage(WebDriver driver) {
        super(driver);
    }

    public boolean headerMatches(String header){
        if(!this.header.getText().equals(header)) {
            return false;
        }
        return true;
    }

    public void hoverInfoIcon(){
        Actions builder = new Actions(driver);
        builder.moveToElement(infoIcons.get(0)).build().perform();
    }

    public boolean checkIsInfoTipExist(){
        if(!infoTip.isDisplayed()){
            return false;
        }
        return true;
    }

    public boolean checkIsTicketOptionsIsNotEmpty(){
        if(ticketOptions.isEmpty()){
            return false;
        }
        return true;
    }

    public void selectTicketOption(int option){
        ticketOptions.get(option).click();
    }

    public boolean checkTicketOptionRadioIsSelected(int option){
        if(!ticketOptionRadios.get(option).isSelected()){
            return false;
        }
        return true;
    }
    public boolean checkIsButtonIsEnabled(){
        return nextButton.isEnabled();
    }

    public void clickNext(){
        nextButton.click();
    }

    public void selectFirstClass(){
        firstClass.click();
    }

    public LoginPage clickNext2(){
        nextButton2.click();
        return new LoginPage(driver);
    }

    public boolean checkIsButton2IsEnabled(){
        return nextButton2.isEnabled();
    }

}
