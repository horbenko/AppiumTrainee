package pages.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductMenu extends BaseWebPage {

    @FindBy(css = "div.intro > div > div > span.sale > strong")
    private WebElement priceValue;

    @FindBy(xpath = "//div[@class = \"left-column\"]//a[contains(@class, \"button_cart\")]")
    private WebElement buyBtn;

    @FindBy(css = "div.title > h1")
    private WebElement productNameStr;

    public ProductMenu() {
        super();
        PageFactory.initElements(driver, this);
    }

    public DialogCartMenu clickBuyBtn() {
        waitIsClickable(buyBtn);
        buyBtn.click();
        return new DialogCartMenu();
    }

    public float getPrice() {
        waitIsVisible(priceValue);
        String priceStr = priceValue.getText().replaceAll("\\D+","");
        return Float.parseFloat(priceStr);
    }

    public String getProductName() {
        waitIsVisible(productNameStr);
        return productNameStr.getText();
    }




}
