package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdventureDetailsPage {


    static RemoteWebDriver driver;

    @FindBy(xpath = "//input[@type='text']")
    WebElement nameTextField;

    @FindBy(name = "date")
    WebElement dateTextField;

    @FindBy(xpath = "//input[@name='person']")
    WebElement personCount;

    @FindBy(xpath = "//button[text()='Reserve']")
    WebElement reserveBtn;

    @FindBy(id = "reserved-banner")
    WebElement succmsg;

    @FindBy(xpath = "//*[text()='here']")
    WebElement here;

    @FindBy(xpath = "//tbody//th")
    WebElement tCount;


    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public void enterName(String name){
        SeleniumWrapper.sendkeys(nameTextField,name);
    }

    public void selectDate(String Date){
        SeleniumWrapper.sendkeys(dateTextField,Date);
    }

    public void enterPerson(String Count){
        SeleniumWrapper.sendkeys(personCount,Count);
    }

    public void clickReserveBtn(){
        SeleniumWrapper.click(reserveBtn, driver);
    }

    public String successmsg() throws InterruptedException{
        Thread.sleep(5000);
        String message = succmsg.getText();
        return message; 
    }

    public void clickHere(){
        SeleniumWrapper.click(here, driver);
    }

    public void fillform(String dataset){
        String s1 = dataset.split(";")[2];
        enterName(s1);
        String s2 = dataset.split(";")[3];
        selectDate(s2);
        String s3 = dataset.split(";")[4];
        enterPerson(s3);
        clickReserveBtn();

    }

    public int transCount(){
        List<WebElement> tr =  driver.findElements(By.xpath("//tbody//th"));
        return tr.size();
    }

}