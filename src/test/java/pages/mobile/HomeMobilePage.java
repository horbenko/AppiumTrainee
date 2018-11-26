package pages.mobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HomeMobilePage extends BaseMobilePage {
    private final String PAGE_URL = "https://smt.ua";

    @FindBy(css = "a.icon-lock")
    private WebElement singInBtn;

    @FindBy(css = "#top_form_cart > div > button")
    private WebElement cartBtn;

    @FindBy(className = "categories-block-a")
    private List<WebElement> itemsOfMainMenuCategories;

    @FindBy(css = "div.foot_links > a")
    private List<WebElement> footerLinks;

    public HomeMobilePage() {
        super();
        PageFactory.initElements(appiumDriver, this);
    }

    public LoginDialog clickSingInBtn() {
        waitIsClickable(singInBtn);
        singInBtn.click();
        return new LoginDialog();
    }

    public DialogCartMenu clickCartBtn() {
        waitIsClickable(cartBtn);
        cartBtn.click();
        return new DialogCartMenu();
    }

    public List<WebElement> getListOfMainMenuCategories() {
        waitIsAllVisible(itemsOfMainMenuCategories);
        return itemsOfMainMenuCategories;
    }

    public MenuElements selectRandomMainMenuCategory() {
        int exclude = 6;  // Exclude temporary empty category
        List<WebElement> mainMenuCategories = getListOfMainMenuCategories();
        int randomNum = ThreadLocalRandom.current().nextInt(0, mainMenuCategories.size());
        if (randomNum == exclude) randomNum += 1;
        mainMenuCategories.get(randomNum).click();
        return new MenuElements();
    }

    public MenuElements selectMainMenuCategory(int menuCategoryNumber) {
        List<WebElement> mainMenuCategories = getListOfMainMenuCategories();
        if (menuCategoryNumber < 0 || menuCategoryNumber > mainMenuCategories.size()) {
            throw new IllegalArgumentException("Cannot select menu category.");
        }
        mainMenuCategories.get(menuCategoryNumber).click();
        return new MenuElements();
    }

    public List<WebElement> getFooterLinks() {
        waitIsAllVisible(footerLinks);
        return footerLinks;
    }

}
