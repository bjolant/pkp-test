package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Jola on 2017-06-10.
 */
public class HomePage extends PageObject{
    //=========WebElements=========
    @FindBy(xpath = ".//*[@id='slider-id']/div[1]/p/a/img")
    private WebElement pkpWidget;

    @FindBy(xpath = ".//*[@id='slider-id']/div[2]/p/a/img")
    private WebElement rozkladPkpWidget;

    @FindBy(xpath = ".//*[@alt=\"Kup bilet\"]")
    private WebElement intercityWidget;

    @FindBy(xpath = ".//*[@alt=\"Dobra podróż\"]")
    private WebElement ourCenterWidget;

    @FindBy(xpath = ".//*[@alt=\"Infrastruktura\"]")
    private WebElement realEstateWidget;

    //=========Constructor=========
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //=========Methods=========
    public void goToPkp(){
        this.pkpWidget.click();
    }

    public void goToRozkladPkp(){
        this.rozkladPkpWidget.click();
    }

    public void goToIntercity(){
        this.intercityWidget.click();
    }

    public void goToNaszeDworce(){
        this.ourCenterWidget.click();
    }

    public void goToNieruchomosci(){
        this.realEstateWidget.click();
    }
}
