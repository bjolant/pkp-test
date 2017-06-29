package pageobject;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jola on 2017-06-19.
 */
public class GuestPage extends PageObject {

    @FindBy(xpath = ".//*[@class=\"content\"]/h1")
    private WebElement header;

    public GuestPage(WebDriver driver) {
        super(driver);
    }

    public boolean headerMatches(String header) {
        if (!this.header.getText().equals(header)) return false;
        return true;
    }
}
