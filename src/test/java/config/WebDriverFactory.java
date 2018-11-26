package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {


    public WebDriver getWebDriver(BrowserName browserName, WebDriverFactoryMode webDriverFactoryMode) {
        WebDriver webDriver;
        switch (webDriverFactoryMode) {
            case GRID:
                return webDriver = getRemoteWebDriver(browserName);
            case LOCAL:
            default:
                return webDriver = getLocalWebDriver(browserName);
        }
    }

    private WebDriver getRemoteWebDriver(BrowserName browserName) {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setJavascriptEnabled(true);
        switch (browserName) {
            case FIREFOX:
                capability = DesiredCapabilities.firefox();
                return new RemoteWebDriver(capability);
            case IE:
                capability = DesiredCapabilities.internetExplorer();
                capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                return new RemoteWebDriver(capability);
            case CHROME:
            default:
                capability = DesiredCapabilities.chrome();
                capability.setCapability("prefs", "--disable-notifications");
                return new RemoteWebDriver(capability);
        }
    }

    private WebDriver getLocalWebDriver(BrowserName browserName) {
        WebDriver webDriver;
        if (getOS().contains("Window")) {
            return webDriver = getDriverForWindows(browserName);
        } else
            return webDriver = getDriverForUnix(browserName);
    }

    private WebDriver getDriverForWindows(BrowserName browserName) {
        Resources resources = new Resources();
        switch (browserName) {
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions().setLegacy(true);
                System.setProperty(
                        "webdriver.gecko.driver",
                        resources.getResource("/drivers/windows/geckodriver.exe"));
                return new FirefoxDriver(firefoxOptions);
            case IE:
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
                System.setProperty(
                        "webdriver.ie.driver",
                        resources.getResource("/drivers/windows/IEDriverServer.exe"));
                return new InternetExplorerDriver(ieOptions);
            case CHROME:
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("prefs", "--disable-notifications");
                chromeOptions.addArguments("start-maximized");
                System.setProperty(
                        "webdriver.chrome.driver",
                        resources.getResource("/drivers/windows/chromedriver.exe"));
                return new ChromeDriver(chromeOptions);
        }
    }

    private WebDriver getDriverForUnix(BrowserName browserName) {
        Resources resources = new Resources();
        switch (browserName) {
            case FIREFOX:
                System.setProperty(
                        "webdriver.gecko.driver",
                        resources.getResource("/drivers/unix/geckodriver"));
                return new FirefoxDriver();
            case IE: throw new IllegalArgumentException("Not supported browser for Unix/Linux platform.");
            case CHROME:
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("prefs", "--disable-notifications");
                chromeOptions.addArguments("start-maximized");
                System.setProperty(
                        "webdriver.chrome.driver",
                        resources.getResource("/drivers/unix/chromedriver"));
                return new ChromeDriver(chromeOptions);
        }
    }

    private String getOS() {
        return System.getProperty("os.name");
    }

    public enum WebDriverFactoryMode {
        LOCAL("local"),
        GRID("grid");

        private final String webDriverFactoryMode;

        WebDriverFactoryMode(String webDriverFactoryMode) {
            this.webDriverFactoryMode = webDriverFactoryMode;
        }

        public String getWebDriverFactoryMode() {
            return webDriverFactoryMode;
        }
    }

}
