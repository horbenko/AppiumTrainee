package pages.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountArea extends BaseWebPage {

    @FindBy(css = "a.icon-lock")
    private WebElement iconLockBtn;

    @FindBy(className = "cl_name")
    private WebElement accountFullName;

    public AccountArea() {
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean isAccountArea() {
       return (driver.getTitle().contains("Личный Кабинет"));
    }

    public boolean isCorrectAccountFullName() {
        waitIsVisible(accountFullName);
        return (accountFullName).getText().contains("Имя Фамилия!");
    }

    public boolean isExitTextPresent() {
        return waitIsToBeTextPresent(iconLockBtn, "Выйти");
    }

}
