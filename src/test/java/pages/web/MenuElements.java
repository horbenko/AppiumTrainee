package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MenuElements extends BaseWebPage {
    @FindBy(css = "div[itemprop='itemListElement']")
    private List<WebElement> itemsOfTheCategorie;

    @FindBy(css = "span.price.pricecat")
    private WebElement strPrice;

    @FindBy(css = "a[itemprop=\"url\"]")
    private WebElement productNameStr;

    @FindBy(css = "div[id=\"other-options\"]")
    private List<WebElement> otherOptionsFilter;

    public MenuElements() {
        super();
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> getElementsInList() {
        waitIsAllVisible(itemsOfTheCategorie);
        return itemsOfTheCategorie;
    }

    public ProductMenu selectRandomProduct() {
        List<WebElement> list = getElementsInList();
        int randomNum = ThreadLocalRandom.current().nextInt(0, list.size());
        list.get(randomNum).click();
        return new ProductMenu();
    }

    public float getProductPrice() {
        waitIsVisible(strPrice);
        return Float.parseFloat(strPrice.getText().replaceAll("\\D+", ""));
    }

    public String getProductName() {
        waitIsVisible(productNameStr);
        return productNameStr.getText();
    }

    public List<WebElement> getOtherOptionsFilterElementsInList() {
        waitIsAllVisible(otherOptionsFilter);
        return otherOptionsFilter;
    }

    public MenuElements clickOtherOptionsFilterElements(String strToMatch) {
        MenuElements menuElements = new MenuElements(); // redo
        List<WebElement> otherOptionsFilterElements = menuElements.getOtherOptionsFilterElementsInList();
        for (WebElement element:otherOptionsFilterElements) {
            if(element.getText().contains(strToMatch)) {
                element.findElement(By.xpath("//div[@id=\"other-options\"]//label[contains(., \"" + strToMatch + "\")]")).click();
                return new MenuElements();
            }
        } throw new IllegalArgumentException("Cannot select provided filter element.");
    }

    public List<String> getAllProductsNamesInList() {
        List<String> productsNames = new LinkedList<>();
        List<WebElement> elements = getElementsInList();
        for (WebElement element:elements
        ) {
            productsNames.add(element.findElement(By.cssSelector("div.right > div.name > a")).getText());
        }
        return productsNames;
    }

}
