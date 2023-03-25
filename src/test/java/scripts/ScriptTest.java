package scripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import reusable.Base;

public class ScriptTest extends Base {
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get(getXpath("FacebookURL"));
        driver.get("https://www.google.com");
    }

    @Test
    public void test2() {
        driver.get("https://www.Yahoo.com");
        driver.get(getXpath("GoogleURL"));
    }

}
