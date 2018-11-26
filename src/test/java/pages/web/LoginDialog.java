package pages.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import data.User;

public class LoginDialog extends BaseWebPage {

    @FindBy(id = "input-name")
    private WebElement inputName;

    @FindBy(id = "input-password")
    private WebElement inputPassword;

    @FindBy(id = "login_but")
    private WebElement loginBtn;

    @FindBy(css = "div.person > p")
    private WebElement personStr;

    public LoginDialog() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void setLoginName(String username) {
        waitIsVisible(inputName);
        inputName.sendKeys(username);
    }

    public void setLoginPassword(String password) {
        waitIsVisible(inputPassword);
        inputPassword.sendKeys(password);
    }

    public void clickLoginBtn() {
        waitIsClickable(loginBtn);
        loginBtn.click();
    }

    public boolean loginValidUser(User user) {
        setLoginName(User.VALID_USER.email);
        setLoginPassword(User.VALID_USER.password);
        clickLoginBtn();
        return isLoginSuccess(User.VALID_USER);
    }

    private boolean isLoginSuccess(User user) {
        waitIsVisible(personStr);
        return personStr.getText().contains(User.VALID_USER.name);
    }

}
