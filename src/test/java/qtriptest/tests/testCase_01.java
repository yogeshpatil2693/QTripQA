package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class testCase_01{

    static RemoteWebDriver driver;
    public static String lastgeneratedusername;
    static ExtentTest test;
    static ExtentReports report;

    @BeforeSuite(alwaysRun = true)
        public static void createDriver() throws MalformedURLException {
                // Launch Browser using Zalenium
            ReportSingleton reportSingleton = ReportSingleton.getInstanceOfSingletonBrowserClass();
            report = reportSingleton.config();
            DriverSingleton driverSingleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
            driver = driverSingleton.getWebDriver();
           
        }

       
    @Test(description = "Verify user registration - login - logout", dataProvider = "drivetest", dataProviderClass = DP.class, priority = 1, groups = "Login Flow")
    public void TestCase01(String UserName, String Password) throws InterruptedException{
        test = report.startTest("Verify user registration - login - logout");
        HomePage homepage = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        LoginPage loginpage = new LoginPage(driver);
        boolean status ;
        //Navigate to homepage
        homepage.navigateToHomePage();
        Thread.sleep(4000);
        //click on register page
        homepage.clickRegister();
        Thread.sleep(4000);
        //verify registration page displayed
        status = register.isRegistrationPageDisplayed();
        if(status==true){
            test.log( LogStatus.PASS, "registration page is displayed");
        }else{
            test.log( LogStatus.FAIL, "registration page is not displayed");
        }


        //register a user
        register.registerUser(UserName, Password, true);

        Thread.sleep(4000);
       //verify login page displayed
       status =  loginpage.isLoginPageDisplayed();
        if(status==true){
            test.log( LogStatus.PASS, "login page is displayed");
        }else{
            test.log(LogStatus.FAIL, "login page is not displayed");
        }

        //perform login with created credentials
        Thread.sleep(4000);
        lastgeneratedusername = register.lastGeneratedUsername;
        loginpage.performLogin(lastgeneratedusername, Password);

        //verify user logged in
        status = homepage.isuserloggedin();
        if(status==true){
            test.log( LogStatus.PASS, "user is logged in");
        }else{
            test.log(LogStatus.FAIL, "user is not logged in");
        }

        Thread.sleep(4000);
        //click on logout
        homepage.logout();

        Thread.sleep(4000);
        //verify user is logged out
        status = homepage.isloginHereDisplayed();
        if(status==true){
            test.log( LogStatus.PASS, "user is logged out");
        }else{
            test.log(LogStatus.FAIL, "user is not logged out");
        }


    }

    @AfterSuite
        public static void quitDriver() {
                report.endTest(test);
                System.out.println("quit()");
                driver.quit();
                report.flush();
        }
    
}
