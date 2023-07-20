package Utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    ExtentReports extentReport = ExtentReportGenerator.getExtentReport();
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO,testName+" test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       String testName =  result.getName();
       extentTest.log(Status.PASS,testName+" test got passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName =  result.getName();
        extentTest.log(Status.FAIL,testName+" test got failed");

        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            extentTest.addScreenCaptureFromPath(takeScreenShot(testName,driver),testName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.log(Status.INFO,result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName =  result.getName();
        extentTest.log(Status.SKIP,testName+" test got skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();

        //Automatically open extent report in browser
        /*File reportFile = new File(System.getProperty("user.dir")+"\\ExtentReport\\Extent_Report.html");

        try {
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}
