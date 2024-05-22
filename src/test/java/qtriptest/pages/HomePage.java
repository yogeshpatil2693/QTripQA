package qtriptest.pages;


import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class HomePage {

    RemoteWebDriver driver;
    String url = "https://qtripdynamic-qa-frontend.vercel.app/";
    String CityName = "";


    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerButton;


    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//a[text()='Login Here']")
    WebElement loginHere;

    @FindBy(id = "autocomplete")
    WebElement searchBox;

    @FindBy(id = "results")
    WebElement resultCity;

    @FindBy(xpath = "//a[text()='Home']")
    WebElement homeBtn;



    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);

    }

    public void navigateToHomePage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public boolean clickRegister() {
        SeleniumWrapper.click(registerButton, driver);
        boolean status = driver.getCurrentUrl().contains("register");
        return status;
    }

    public void login() {
        SeleniumWrapper.click(loginHere, driver);
    }

    public void logout() {
        SeleniumWrapper.click(logoutButton, driver);
    }

    public boolean isuserloggedin() {
        if (logoutButton.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isloginHereDisplayed() {
        if (loginHere.isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void searchForCity(String city){
        SeleniumWrapper.sendkeys(searchBox, city);
    }

    public String searchResult(){
        return resultCity.getText();
    }

    public void clickOnCity(){
        SeleniumWrapper.click(resultCity, driver);
    }

    public void data(String dataset) throws InterruptedException{
        String s1 = dataset.split(";")[0];
        searchForCity(s1);
        Thread.sleep(3000);
        clickOnCity();
    }
  
    public void clickOnHomeButton(){
        SeleniumWrapper.click(homeBtn, driver);
    }


}
