package pages.mobile;

import data.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class AccountArea extends BaseMobilePage {

    @FindBy(css = "a.icon-lock")
    private WebElement iconLockBtn;

    @FindBy(css = "div[class=\"person\"] > p")
    private WebElement accountFullName;

    public AccountArea() {
        super();
        PageFactory.initElements(appiumDriver, this);
    }

    public boolean isAccountArea() {
        return (appiumDriver.getTitle().contains("Личный Кабинет"));
    }

    public boolean isCorrectAccountFullName() {
        waitIsVisible(accountFullName);
    return (accountFullName).getText().contains(User.VALID_USER.name);
    }

}
