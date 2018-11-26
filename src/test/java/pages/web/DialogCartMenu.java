package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.TreeSet;

public class DialogCartMenu extends BaseWebPage {

    @FindBy(xpath = "//table[contains(@class, \"table-striped\")]/tbody/tr[not (@class)]")
    private List<WebElement> listOfElements;

    @FindBy(css = "div.right > div > strong")
    private WebElement totalPriceValue;

    @FindBy(css = "td.text-left.name > a")
    private WebElement productName;

    @FindBy(css = "#cart_modal > div > div > div.modal-header > button")
    private WebElement closeCartBtn;

    @FindBy(className = "continue_buy")
    private WebElement continueToBuyBtn;

    @FindBy(css = "td.text-center.del > button")
    private WebElement delBtn;

    public DialogCartMenu() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void clickContinueToBuyBtn() {
        waitIsClickable(continueToBuyBtn);
        continueToBuyBtn.click();
    }

    public List<WebElement> getListOfElementsInCart() {
        //waitIsAllVisible(listOfElements);
        return listOfElements;
    }

    public float getTotalPriceValue() {
        waitIsVisible(totalPriceValue);
        String strTotalPrice = totalPriceValue.getText().replaceAll("\\D+","");
        return Float.parseFloat(strTotalPrice);
    }

    public String getProductName() {
        waitIsVisible(productName);
        return productName.getText();
    }

    public void clickCloseCartBtn() {
        waitIsClickable(closeCartBtn);
        closeCartBtn.click();
    }

    public TreeSet<String> getAllProductsNamesInList() {
        TreeSet<String> productsNames = new TreeSet<>();
        List<WebElement> elements = getListOfElementsInCart();
        for (WebElement element:elements
             ) {
            productsNames.add(element.findElement(By.cssSelector("td.text-left.name > a")).getText());
        }
         return productsNames;
    }

    public DialogCartMenu deleteAllProducts() {
        List<WebElement> elements = getListOfElementsInCart();
        for (WebElement element:elements
        ) {
            element.findElement(By.cssSelector("td.text-center.del > button")).click();
        }
        return new DialogCartMenu();
    }

}
