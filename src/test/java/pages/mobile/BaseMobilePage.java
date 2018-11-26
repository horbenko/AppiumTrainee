package pages.mobile;

import config.DriverInstanciator;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseMobilePage {
    final AppiumDriver appiumDriver;
    final WebDriverWait wait;
    private final String HOME_PAGE_URL = "https://smt.ua";

    @FindBy(css = "div#logo")
    private WebElement homeLogo;

    public BaseMobilePage() {
        appiumDriver = DriverInstanciator.getAppiumDriver();
        wait = new WebDriverWait(appiumDriver, 15);
        PageFactory.initElements(appiumDriver, this);
    }

    public HomeMobilePage clickHomePage() {
        waitIsVisible(homeLogo);
        homeLogo.click();
        return new HomeMobilePage();
    }

    public BaseMobilePage clickOnLinkByText(String linkText) {
        appiumDriver.findElement(By.linkText(linkText)).click();
        return new BaseMobilePage();
    }

    public void getHomePage() {
        appiumDriver.get(HOME_PAGE_URL);
    }

    public String getPageTitle() {
        return appiumDriver.getTitle();
    }

    void waitIsClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    void waitIsVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    void waitIsAllVisible(List<WebElement> list) {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    boolean waitIsToBeTextPresent(WebElement element, String str) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, str));
    }

}
