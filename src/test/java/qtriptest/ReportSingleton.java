package qtriptest;

import java.io.File;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ReportSingleton {

    private static ReportSingleton reportSingleton;
    static ExtentTest test;
    static ExtentReports report;

    public static ReportSingleton getInstanceOfSingletonBrowserClass() throws MalformedURLException{
        if(reportSingleton==null){
            reportSingleton = new ReportSingleton();
        }
        return reportSingleton;
    }

    public ExtentReports config() {
        report = new ExtentReports("/home/crio-user/workspace/yogeshpatil2693-ME_QTRIP_QA_V2/app/src/test/java/qtriptest/Reports/reports.html",true);
        report.loadConfig(new File(System.getProperty("user.dir")+"/extent_customization_configs.xml"));
        return getReport();
    }

    public ExtentReports getReport(){
        return report;
    }

   
    public void tearDown(){
        report.endTest(test);
        report.flush();
    }
   
}