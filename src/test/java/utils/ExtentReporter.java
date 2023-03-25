package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.BeforeClass;

import java.io.File;

public class ExtentReporter {
    public static String testName;

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReports = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "\\target\\ExtentReports\\Report.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Test Automation Result Report");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
        extentReports.getStats();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("USER COUNTRY", System.getProperty("user.country"));
        extentReports.setSystemInfo("OPERATING SYSTEM", System.getProperty("os.name"));
        extentReports.setSystemInfo("USER NAME", System.getProperty("user.name"));
        extentReports.setSystemInfo("JAVA VERSION", System.getProperty("java.version"));


        return extentReports;

    }

    @BeforeClass
    public static String getTestName(String string) {
        testName = string;
        return testName;
    }
}

