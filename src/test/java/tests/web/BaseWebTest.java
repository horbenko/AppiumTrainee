package tests.web;

import config.BrowserName;
import config.WebDriverFactory.WebDriverFactoryMode;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import static config.DriverInstanciator.getWebDriver;
import static config.DriverInstanciator.setDriver;

@Listeners(WebTestListener.class)
public class BaseWebTest {

    @BeforeClass
    @Parameters({"browserName", "webDriverFactoryMode"})
    public void setUp(String browserName, String runMode){
        BrowserName castedBrowserName = BrowserName.valueOf(browserName.toUpperCase());
        WebDriverFactoryMode castedWebDriverFactoryMode = WebDriverFactoryMode.valueOf(runMode.toUpperCase());
        setDriver(castedBrowserName, castedWebDriverFactoryMode);
    }

    @AfterClass
    public void tearDown() throws Exception{
        getWebDriver().quit();
    }

}
