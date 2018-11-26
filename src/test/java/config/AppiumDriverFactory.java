package config;

import data.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {
    private DesiredCapabilities capabilities;
    private data.Device device;

    public AppiumDriver getAppiumDriver(Device testDevice) {
        AppiumDriver appiumDriver;
        switch(testDevice.getPlatformName()) {
            case "iOS":
                return appiumDriver = getForIOS(testDevice);
            case "Android":
                try {
                    return appiumDriver = getForAndroid(testDevice);
                } catch (MalformedURLException | InterruptedException e) {
                    e.printStackTrace();
                }
            default:
                throw new IllegalArgumentException("Unsupported platform.");
        }
    }

    private AppiumDriver getForAndroid(Device testDevice) throws MalformedURLException, InterruptedException {
        Resources resources = new Resources();
        ChromeOptions chromeOptions = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities().android();

        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("[--disable-translate]");
        //chromeOptions.addArguments("--disable-glsl-translator");
        //chromeOptions.addArguments("--disable-translate-new-ux[5]");
        //chromeOptions.addArguments("--translate-script-url");

        //chromeOptions.addArguments("--disable-plugins");
        //chromeOptions.addArguments("--start-maximized");

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, testDevice.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, testDevice.getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, testDevice.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(AndroidMobileCapabilityType.CHROME_OPTIONS, chromeOptions);
        capabilities.setCapability(AndroidMobileCapabilityType.NATIVE_WEB_SCREENSHOT, true);
        capabilities.setCapability("chromedriverExecutable",
                                                    resources.getResource("/drivers/windows/chromedriver.exe"));
        //capabilities.setCapability("autoDismissAlerts", true);
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }

    private AppiumDriver getForIOS(Device testDevice) {
        return new IOSDriver(capabilities);
    }

}
