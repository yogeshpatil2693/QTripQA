package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {

    static RemoteWebDriver driver;


    @FindBy(id = "duration-select")
    WebElement filter;

    @FindBy(id = "category-select")
    WebElement category;

    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    List<WebElement> filterResult;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement eventclearButton;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement categoryclearButton;

    @FindBy(xpath = "//div[@onclick='resetAdventuresData()']")
    WebElement resetclearButton;

    @FindBy(id = "search-adventures")
    WebElement searchAdventureField;

    public AdventurePage(RemoteWebDriver driver){
        this.driver = driver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(factory, this);
    }

    public void selectFilter(String DurationFilter) throws InterruptedException{
        SeleniumWrapper.click(filter, driver);
        Thread.sleep(2000);
        Select select = new Select(filter);
        select.selectByVisibleText(DurationFilter);
    }

    public void addCategory(String Category_Filter ) throws InterruptedException{
        SeleniumWrapper.click(category, driver);
        Thread.sleep(2000);
        Select select = new Select(category);
        select.selectByVisibleText(Category_Filter);
    }

    public String countFilterResult(){
        int r = filterResult.size();
        return Integer.toString(r);
    }

    public void removeFilter(){
        SeleniumWrapper.click(eventclearButton, driver);
        SeleniumWrapper.click(categoryclearButton, driver);
        SeleniumWrapper.click(resetclearButton, driver);
    }

    public void searchAdventure(String adventure) throws InterruptedException{
        SeleniumWrapper.sendkeys(searchAdventureField, adventure);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//h5[text()='"+adventure+"']")).click();
    }

    public void data1(String dataset) throws InterruptedException{
        String s1 = dataset.split(";")[1];
        searchAdventure(s1);
    }

    
}