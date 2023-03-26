package reusable;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static reusable.Base.driver;

public class TestListeners implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;
    String testName;


    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        extentTest = extentReports.createTest(testName);
        extentTest.log(Status.INFO, MarkupHelper.createLabel(testName + "- Test Execution Started", ExtentColor.WHITE));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, MarkupHelper.createLabel(testName + "- Test Case Passed", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String destinationScreenshotPath = Reusable.takeFailTestScreenshot(driver,testName);
        extentTest.addScreenCaptureFromPath(destinationScreenshotPath).log(Status.INFO,"Screen Shot Taken");
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL,MarkupHelper.createLabel(testName + "- Test Case Failed", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.FAIL, MarkupHelper.createLabel(testName + "- Test Case Skipped", ExtentColor.YELLOW));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        extentTest.log(Status.WARNING, MarkupHelper.createLabel(testName + " - Test Case Passed but with a warning", ExtentColor.AMBER));

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        String pathOfExtentReport = System.getProperty("user.dir") + "\\target\\ExtentReports\\Report.html";

        File extentReport = new File(pathOfExtentReport);
        //This is to open extentReport automatically
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
