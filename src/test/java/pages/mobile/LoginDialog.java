package pages.mobile;

import data.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginDialog extends BaseMobilePage {

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
        PageFactory.initElements(appiumDriver, this);
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
        loginBtn.click();
    }

    public boolean loginValidUser(User user) {
        setLoginName(User.VALID_USER.email);
        setLoginPassword(User.VALID_USER.password);
        clickLoginBtn();
        return isLoginSuccess(User.VALID_USER);
    }

        private boolean isLoginSuccess(User user) {
            return personStr.getText().contains(User.VALID_USER.name);
        }

}
