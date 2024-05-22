package qtriptest.pages;


import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {

    RemoteWebDriver driver;

    @FindBy(id = "floatingInput")
    WebElement emailInput;

    @FindBy(id = "floatingPassword")
    WebElement passInput;

    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement signButton;

    

    public LoginPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public void performLogin(String userName , String password){
        SeleniumWrapper.sendkeys(emailInput, userName);
        SeleniumWrapper.sendkeys(passInput, password);
        SeleniumWrapper.click(signButton, driver);
    }

    public boolean isLoginPageDisplayed(){
       if(driver.getCurrentUrl().contains("login")){
            return true;
        }else{
            return false;
        }
    }

    
}
