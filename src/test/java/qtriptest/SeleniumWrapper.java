package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumWrapper {

    static RemoteWebDriver driver;


    // public static boolean click(WebElement element, WebDriver driver)throws InterruptedException{
    //     if(element.isDisplayed()){
    //         JavascriptExecutor js = (JavascriptExecutor)driver;
    //         js.executeScript("argument[0].scrollIntoView(true)",element);
    //         element.click();
    //         return true;
    //     }else{
    //         return false;
    //     }
    // }
    public static boolean click(WebElement element, WebDriver driver){
        try {
            JavascriptExecutor js = (JavascriptExecutor)driver;
            
            if(element.isDisplayed()){
                js.executeScript("arguments[0].scrollIntoView(true)", element);
                element.click();
                return true; // Return true if click operation is successful
            } else {
                return false; // Return false if element is not displayed
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false if any exception occurs
        }
    }
    

    public static boolean sendkeys(WebElement element, String keys){
        element.clear();
        element.sendKeys(keys);
        return true;
    }

    public static boolean navigate(WebDriver driver, String url){
        if(!driver.getCurrentUrl().equals(url)){
            driver.navigate().to(url);
            return true;
        }else{
            return false;
        }
    }

    public static WebElement findElementWithRetry(WebDriver driver, By locator, int retrycount){

        int i=0;
        WebElement element = null;
        while(retrycount<i){
            try{
               element = driver.findElement(locator);
               break;
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }
        return element;
    }
}
