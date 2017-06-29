package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jola on 2017-06-21.
 */
public class RealEstatePage extends PageObject {

    @FindBy(xpath = ".//*[@class=\"box dark first\"]")
    private WebElement saleButton;

    @FindBy(xpath = ".//*[@class=\"box dark\"]")
    private WebElement rentButton;

    public RealEstatePage(WebDriver driver) {
        super(driver);
    }

    public void goToSaleOffers(){
        saleButton.click();
    }

    public void goToRentOffers(){
        rentButton.click();
    }
}
