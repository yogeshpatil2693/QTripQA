package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.sql.Timestamp;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    RemoteWebDriver driver;
    public String lastGeneratedUsername = "";

    @FindBy(id ="floatingInput")
    WebElement emailInput;

    @FindBy(id = "floatingPassword")
    WebElement passInput;

    @FindBy(name = "confirmpassword")
    WebElement confpassInput;

    @FindBy(xpath="//button[text()='Register Now']")
    WebElement registerNowButton;
    
    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public boolean registerUser(String UserName, String Password, Boolean makeUsernameDynamic) throws InterruptedException{
    
        // Get time stamp for generating a unique username
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String test_data_username = "";
        String testUserName = "";
        if (makeUsernameDynamic){
             // Concatenate the timestamp to string to form unique timestamp
             testUserName = UserName.split("@")[0];
             test_data_username = testUserName + String.valueOf(timestamp.getTime()) + "@gmail.com";
             this.lastGeneratedUsername = test_data_username;
        }
           
        // else
        //     test_data_username = UserName;

        // Type the generated username in the username field
        SeleniumWrapper.sendkeys(emailInput, test_data_username);

        Thread.sleep(7000);

        // Find the password Text Box
        // passInput.clear();
        SeleniumWrapper.sendkeys(passInput, Password);


        Thread.sleep(3000);
        // Enter the Confirm Password Value
        // confpassInput.clear();
        SeleniumWrapper.sendkeys(confpassInput, Password);

        Thread.sleep(3000);

        // Click the register now button
        SeleniumWrapper.click(registerNowButton, driver);

       WebDriverWait wait = new WebDriverWait(driver, 20);
       wait.until(ExpectedConditions.urlContains("login"));

        return this.driver.getCurrentUrl().endsWith("/login");
    }

   

    public boolean isRegistrationPageDisplayed(){
        if(driver.getCurrentUrl().contains("register")){
            return true;
        }else{
            return false;
        }
    }

    

    
}
