package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportGenerator {

    static ExtentReports extentReport;
    static ExtentSparkReporter sparkReporter;

    public static ExtentReports getExtentReport(){

        extentReport = new ExtentReports();
        File file = new File("F:\\Projects\\LoanSeleniumJava\\ExtentReport\\Extent_Report.html");
        sparkReporter = new ExtentSparkReporter(file);
        extentReport.attachReporter(sparkReporter);

        return extentReport;
    }
}
