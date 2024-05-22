package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_02 {


    static RemoteWebDriver driver;
    boolean status ;
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

    @Test(description = "Verify the adventure filters",dataProvider = "drivetest", dataProviderClass = DP.class, priority = 2, groups = "Search and Filter flow")
    public void TestCase02(String CityName, String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException{
        test = report.startTest("Verify the adventure filters");
        HomePage homepage  = new HomePage(driver);
        AdventurePage adventurepage = new AdventurePage(driver);

        // navigate to homepage
        homepage.navigateToHomePage();

        Thread.sleep(3000);

        //search for non existent city
        homepage.searchForCity("Ranchi");
        Thread.sleep(3000);

        //verify no matches found message
       String resultMessage  = homepage.searchResult();
       Assert.assertEquals(resultMessage,"No City found");

       Thread.sleep(4000);

       // search for city that is present
        homepage.searchForCity(CityName);
        Thread.sleep(3000);
        if(homepage.searchResult().equalsIgnoreCase(CityName)){
            homepage.clickOnCity();
        }else{
            System.out.println("City not found");
        }

        Thread.sleep(3000);
        adventurepage.selectFilter(DurationFilter);

        Thread.sleep(3000);
        adventurepage.addCategory(Category_Filter);

        Thread.sleep(8000);

        String actualFilterResult =  adventurepage.countFilterResult();
        if(actualFilterResult.equals(ExpectedFilteredResults)){
            test.log(LogStatus.PASS, "filter count match");
        }else{
            test.log(LogStatus.FAIL, "filter count doesnt match");
        }
        Assert.assertEquals(actualFilterResult, ExpectedFilteredResults );

        Thread.sleep(7000);

        adventurepage.removeFilter();
        Thread.sleep(3000);

        String actualUnFilterResult =  adventurepage.countFilterResult();
        Assert.assertEquals(actualUnFilterResult, ExpectedUnFilteredResults );


        





    }
}

