package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jola on 2017-06-21.
 */
public class MyAccountPage extends PageObject {

    @FindBy(xpath = ".//*[@title=\"Wyloguj\"]")
    private WebElement logoutButton;

    @FindBy(xpath = ".//table/tbody/tr[1]/td")
    private WebElement info;

    @FindBy(xpath = ".//*[@type=\"submit\"]")
    private WebElement submitLogoutButton;

    @FindBy(partialLinkText = "Oczekujące na płatność")
    private WebElement awaitingPayment;

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public void logout(){
        logoutButton.click();
    }

    public boolean infoTextMaches(String infoText){
        if(!info.getText().equals(infoText)){
            return false;
        }
        return true;
    }

    public IntercityPage submitLogout(){
        submitLogoutButton.click();
        return new IntercityPage(driver);
    }
}
