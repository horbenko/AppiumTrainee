package pages.web;

import config.DriverInstanciator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseWebPage {
    final WebDriver driver;
    final WebDriverWait wait;
    private final String HOME_PAGE_URL = "https://smt.ua";

    @FindBy(css = "img.img-responsive")
    private WebElement homeLogo;

    public BaseWebPage() {
        driver = DriverInstanciator.getWebDriver();
        wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public HomeWebPage clickHomePage() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#cart_modal > div")));
        waitIsClickable(homeLogo);
        homeLogo.click();
        return new HomeWebPage();
    }

    public BaseWebPage clickOnLinkByText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
        return new BaseWebPage();
    }

    public void getHomePage() {
        driver.get(HOME_PAGE_URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
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
