package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jola on 2017-06-19.
 */
public class LoginPage extends PageObject {

    @FindBy(xpath = ".//*[@class=\"logowanie_module\"]/h1")
    private WebElement header;

    @FindBy(xpath = ".//*[@name=\"login\"]")
    private WebElement loginInput;

    @FindBy(xpath = ".//*[@name=\"password\"]")
    private WebElement passwordInput;

    @FindBy(xpath = ".//*[@type=\"submit\"]")
    private WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean headerMatches(String header){
        if(!this.header.getText().equals(header)) return false;
        return true;
    }

    public void enterLogin(String login){
        loginInput.clear();
        loginInput.sendKeys(login);
    }

    public void enterPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public MyAccountPage submitToMyAccount(){
        submitButton.click();
        return new MyAccountPage(driver);
    }

}
