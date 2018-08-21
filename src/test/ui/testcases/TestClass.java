package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by scao on 30/10/2017.
 */
public class TestClass {

    WebDriver webDriver;
    WebDriverWait webDriverWait;

    @BeforeMethod
    public void SetUp() throws MalformedURLException {

        LoggingPreferences log = new LoggingPreferences();
        log.enable(LogType.DRIVER, Level.ALL);

        URL url = new URL("http://127.0.0.1:4444/wd/hub/"); // for remote server url


        // Scenario1: Test RemoteWebDriver using selenium standalone server
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.LOGGING_PREFS, log);
        webDriver = new RemoteWebDriver(url, cap);

        // Scenario2: Test firefox headless in local web driver(webdriver:3.8.1;firefox:57.0.4;geckodriver:v0.19)
        //System.setProperty("webdriver.gecko.driver", "geckodriver"); // No need to use this when >=3.6+
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        firefoxOptions.setHeadless(true);
//        webDriver = new FirefoxDriver(firefoxOptions);
//        webDriverWait = new WebDriverWait(webDriver, 20);

        // Scenario3: Test Chrome headless in local web driver(webdriver:3.8.1;chrome:64;chromedriver:v2.35)
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setHeadless(true);
//        webDriver = new ChromeDriver(chromeOptions);

        //Scenario4: Test chrome headless in remote web driver
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setHeadless(true);
//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//        webDriver = new RemoteWebDriver(url, cap);


        webDriverWait = new WebDriverWait(webDriver, 20);
    }

    @Test
    public void LoginTest() {
        webDriver.get("https://www.baidu.com/");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Take screenshot
//        File file = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(file, new File("headless_screenshot.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Assert.assertTrue(webDriver.findElement(By.id("kw")).isDisplayed());;
    }
    @AfterMethod
    public void TearDown() {
       webDriver.close();
    }
}
