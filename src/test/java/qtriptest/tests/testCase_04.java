package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_04 {
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



    @Test(description = "Verify the reservation with multiple datasets",dataProvider = "drivetest", dataProviderClass = DP.class, priority = 4, groups = "Reliability Flow")
    public void testCase04(String NewUserName, String Password, String dataset1,String dataset2,String dataset3) throws InterruptedException{
        test = report.startTest("Verify the reservation with multiple datasets");
        HomePage homepage = new HomePage(driver);
        RegisterPage registerpage = new RegisterPage(driver);
        LoginPage loginpage = new LoginPage(driver);
        AdventurePage adventurepage = new AdventurePage(driver);
        AdventureDetailsPage adventuredetailspage = new AdventureDetailsPage(driver);
        
        homepage.navigateToHomePage();
        Thread.sleep(3000);
       // create new user
        homepage.clickRegister();
        Thread.sleep(3000);

        registerpage.registerUser(NewUserName, Password, true);
        Thread.sleep(3000);

        lastgeneratedusername = registerpage.lastGeneratedUsername;
        loginpage.performLogin(lastgeneratedusername, Password);
        Thread.sleep(3000);

        homepage.data(dataset1);
        adventurepage.data1(dataset1);
        Thread.sleep(4000);
        adventuredetailspage.fillform(dataset1);
        Thread.sleep(3000);
        homepage.clickOnHomeButton();
        Thread.sleep(4000);

        homepage.data(dataset2);
        adventurepage.data1(dataset2);
        Thread.sleep(4000);
        adventuredetailspage.fillform(dataset2);
        Thread.sleep(3000);
        homepage.clickOnHomeButton();
        Thread.sleep(4000);

        homepage.data(dataset3);
        adventurepage.data1(dataset3);
        Thread.sleep(4000);
        adventuredetailspage.fillform(dataset3);
        Thread.sleep(3000);
        adventuredetailspage.clickHere();
        Thread.sleep(5000);

        int transactionCount = adventuredetailspage.transCount();
        Thread.sleep(2000);
        if(transactionCount==3){
            test.log(LogStatus.PASS, "TestCase is pass");
        }else{
            test.log(LogStatus.FAIL, "TestCase is fail");
        }
 
        homepage.logout();

    }

       
}
