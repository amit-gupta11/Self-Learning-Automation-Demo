package reusable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;

@SuppressWarnings("rawtypes")
public class Base extends PageObjects {
    protected static WebDriver driver;
    protected static Properties driver_properties;
    protected static ArrayList<String> driver_argument;

    @BeforeClass
    public void setUp() {
        getDriver();
    }

    public static WebDriver getDriver() {
        String browser_name = driverProperties().getProperty("browser.name");
        boolean headless = Boolean.parseBoolean(driverProperties().getProperty("browser.headless"));
        if (browser_name.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--window-size=3840,2160");
            }
            options.addArguments(browserArguments());
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            options.setCapability("goog:loggingPrefs", logPrefs);
            driver = new ChromeDriver(options);
        } else if (browser_name.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            System.out.println("No Driver Found for Particular Browser Name = " + browser_name);
            driver = null;
        }
        return driver;
    }

    public static Properties driverProperties() {
        driver_properties = new Properties();
        File file = new File("src/test/resources/driver.properties");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            driver_properties.load(fileInputStream);

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return driver_properties;
    }

    public static ArrayList<String> browserArguments() {
        String argsString = driver_properties.getProperty("browser.arguments");
        if (argsString != null && !argsString.isEmpty()) {
            driver_argument = new ArrayList<>(Arrays.asList(argsString.split("\\|")));
        } else {
            driver_argument = new ArrayList<>();
        }
        return driver_argument;
    }
}
