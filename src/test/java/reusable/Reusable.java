package reusable;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reusable {
    public static String takeFailTestScreenshot(WebDriver driver, String testName) {
        String destinationScreenshotPath = System.getProperty("user.dir") + "\\target\\Screenshots\\" + testName + ".png";
        try {
            File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Path path = Paths.get(System.getProperty("user.dir") + "\\target\\Screenshots\\");
                Files.createDirectories(path);
                FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destinationScreenshotPath;
    }
}
