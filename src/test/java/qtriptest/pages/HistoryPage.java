
package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.FindBy;

public class HistoryPage {

    static RemoteWebDriver driver;


    @FindBy(xpath = "//tbody//th")
    WebElement transID;

    @FindBy(className = "cancel-button")
    WebElement cancelBtn;

    @FindBy(id = "no-reservation-banner")
    WebElement noreservation;
    

    public HistoryPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }


    public String getTransId(){
        return transID.getText();
    }

    public void clickCancelbtn(){
        SeleniumWrapper.click(cancelBtn, driver);
    }

    public String cancelReservation(){
        return noreservation.getText();
    }

}