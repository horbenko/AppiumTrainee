package config;

import config.WebDriverFactory.WebDriverFactoryMode;
import data.Device;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class DriverInstanciator {
    private static InheritableThreadLocal<WebDriver> webDriver = new InheritableThreadLocal<>();
    private static InheritableThreadLocal<AppiumDriver> appiumDriver = new InheritableThreadLocal<>();
    private static WebDriverFactory webDriverFactory;
    private static AppiumDriverFactory appiumDriverFactory;

    public static void setDriver(BrowserName browserName, WebDriverFactoryMode webDriverFactoryMode){
        webDriverFactory = new WebDriverFactory();
        webDriver.set(webDriverFactory.getWebDriver(browserName, webDriverFactoryMode));
    }

    public static void setDriver(Device testDevice){
        appiumDriverFactory = new AppiumDriverFactory();
        appiumDriver.set(appiumDriverFactory.getAppiumDriver(testDevice));
    }

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static AppiumDriver getAppiumDriver() {
        return appiumDriver.get();
    }

}
