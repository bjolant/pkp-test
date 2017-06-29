package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Jola on 2017-06-13.
 */
public class ExpandPage extends PageObject {

    @FindBy(xpath = ".//*[@id='wyniki']/tbody/tr[4]/td")
    private WebElement expandResult;

    public ExpandPage(WebDriver driver) {
        super(driver);
    }

    public boolean  expandResultIsPresent() {
        if (!expandResult.isDisplayed()) return false;
        return true;
    }
}
