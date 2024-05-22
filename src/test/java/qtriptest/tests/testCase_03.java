package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
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

public class testCase_03 {


    static RemoteWebDriver driver;
    public static String lastgeneratedusername;
    static ExtentTest test;
    static ExtentReports report;

    @BeforeSuite(alwaysRun = true)
        public static void createDriver() throws MalformedURLException {
                // Launch Browser using Zalenium

            DriverSingleton driverSingleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
            driver = driverSingleton.getWebDriver();
            ReportSingleton reportSingleton = ReportSingleton.getInstanceOfSingletonBrowserClass();
            report = reportSingleton.config();
            
        }



    @Test(description = "verify the reservation and cancellation flow",dataProvider = "drivetest", dataProviderClass = DP.class,priority = 3, groups = "Booking and Cancellation Flow")
    public void TestCase03(String NewUserName, String Password, String SearchCity, 
    String AdventureName, String GuestName, String Date, String count) throws InterruptedException{
        test = report.startTest("verify the reservation and cancellation flow");
        HomePage homepage = new HomePage(driver);
        RegisterPage registerpage = new RegisterPage(driver);
        LoginPage loginpage = new LoginPage(driver);
        AdventurePage adventurepage = new AdventurePage(driver);
        AdventureDetailsPage adventuredetailspage = new AdventureDetailsPage(driver);
        HistoryPage historypage = new HistoryPage(driver);
        //navigate to qtrip
        homepage.navigateToHomePage();
        Thread.sleep(3000);
        //create new user
        homepage.clickRegister();
        Thread.sleep(3000);

        registerpage.registerUser(NewUserName, Password, true);
        Thread.sleep(3000);

        lastgeneratedusername = registerpage.lastGeneratedUsername;
        loginpage.performLogin(lastgeneratedusername, Password);
        Thread.sleep(3000);

        //search for an adventure
        homepage.searchForCity(SearchCity);
        Thread.sleep(3000);
        homepage.clickOnCity();
        Thread.sleep(4000);

        adventurepage.removeFilter();
        Thread.sleep(5000);

        adventurepage.searchAdventure(AdventureName);
        Thread.sleep(4000);

        //enter form details
        adventuredetailspage.enterName(GuestName);
        Thread.sleep(3000);
        
        //enter date
        adventuredetailspage.selectDate(Date);
        Thread.sleep(2000);

        //enter persons
        adventuredetailspage.enterPerson(count);
        Thread.sleep(2000);

        adventuredetailspage.clickReserveBtn();
        Thread.sleep(4000);
       
       String smsg = adventuredetailspage.successmsg();
       if(smsg.contains("successful")){
            test.log(LogStatus.PASS,"Reservation Successfull");
       }else{
            test.log(LogStatus.FAIL, "Reservation UnSuccessfull");
       }

       adventuredetailspage.clickHere();
       Thread.sleep(3000);

       String id =  historypage.getTransId();
       System.out.println(id);

       historypage.clickCancelbtn();
       Thread.sleep(3000);

       String noReserveText = historypage.cancelReservation();
       if(noReserveText.contains("You have not made any reservations")){
            test.log(LogStatus.PASS, "Transaction ID is removed");
       }else{
            test.log(LogStatus.FAIL, "Transaction ID is Not removed");
       }

       homepage.logout();

        
    }
}
