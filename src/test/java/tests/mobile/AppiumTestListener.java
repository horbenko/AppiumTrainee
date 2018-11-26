package tests.mobile;

import config.DriverInstanciator;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AppiumTestListener extends BaseMobileTest implements ITestListener {

        @Attachment(value = "Screenshot of the page: ", type = "image/png")
        public byte[] saveScreenshotPNG (AppiumDriver appiumDriver) {
            return ((TakesScreenshot)appiumDriver).getScreenshotAs(OutputType.BYTES);
        }

        @Override
        public void onTestStart(ITestResult iTestResult) {

        }

        @Override
        public void onTestSuccess(ITestResult iTestResult) {

        }

        @Override
        public void onTestFailure(ITestResult iTestResult) {
            AppiumDriver appiumDriver = DriverInstanciator.getAppiumDriver();
            saveScreenshotPNG(appiumDriver);
        }

        @Override
        public void onTestSkipped(ITestResult iTestResult) {

        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

        }

        @Override
        public void onStart(ITestContext iTestContext) {

        }

        @Override
        public void onFinish(ITestContext iTestContext) {

        }
}
