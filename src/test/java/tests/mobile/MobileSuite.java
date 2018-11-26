package tests.mobile;

import data.User;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.mobile.AccountArea;
import pages.mobile.DialogCartMenu;
import pages.mobile.HomeMobilePage;
import pages.mobile.ProductMenu;

import java.util.TreeSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MobileSuite extends BaseMobileTest {

    @Step
    @Test(description = "Login test for the registered user.", priority = 1)
    void loginUserTest() {
        HomeMobilePage homePage = new HomeMobilePage();
        homePage.getHomePage();

        //-залогиниться под валидными данными
        assertTrue(homePage.clickSingInBtn().loginValidUser(User.VALID_USER), "Login is failed.");

        AccountArea accountArea = new AccountArea();
        //- ссылка "Контакты" должна замениться на "ФИО" пользователя
        assertTrue(accountArea.isCorrectAccountFullName(), "The Контакты link is NOT replaced with the user's full name.");

        // - пользователь перенаправлен на страницу личного кабинета
        assertTrue(accountArea.isAccountArea(), "User is NOT redirected to personal account page.");
    }

    @Step("Compare products in the cart.")
    @Test(description = "Test to compare added and present products in the cart.", priority = 0)
    void cartsItemsTest() {
        int productsToTest = 2;
        float totalPriceForTest = 0f;
        TreeSet<String> productNamesForTest = new TreeSet<>();
        TreeSet<String> receivedProductNames;

        HomeMobilePage homePage = new HomeMobilePage();
        homePage.getHomePage();
        ProductMenu productMenu = new ProductMenu();
        DialogCartMenu dialogCartMenu = new DialogCartMenu();

        // - добавить несколько товаров в корзину путем навигации по сайту
        for (int i = 0; i < productsToTest; i++) {
            totalPriceForTest += homePage.selectRandomMainMenuCategory().selectRandomProduct().getPrice();
            productNamesForTest.add(productMenu.getProductName());
            productMenu.clickBuyBtn();
            homePage.getHomePage();
        }

        //- сумма в корзине соответсвует сумме товаров
        assertEquals(totalPriceForTest, homePage.clickCartBtn().getTotalPriceValue(), "The total price in the basket is NOT corresponds to the sum of selected products.");
        dialogCartMenu.clickCloseCartBtn();
        homePage.getHomePage();

        //- выбраные товары присутсвуют в корзине
        receivedProductNames = homePage.clickCartBtn().getAllProductsNamesInList();
        for (String productName : productNamesForTest){
            assertTrue(receivedProductNames.contains(productName), "Some products name are not equal.");
        }
        dialogCartMenu.clickCloseCartBtn();
    }

    //Проверка ссылок секции "Компания", "Покупателям" в футтере сайта
    // - последовательно перейти по каждой ссылке -> отображается страница корректная страница
    @Step("Checking footer links.")
    @Test(dataProvider = "getDataForFooterLinksCheck", priority = 1)
    @Description("Test to check footer links.")
    void footerLinkCheck(String linkText, String  title) {
        HomeMobilePage homePage = new HomeMobilePage();
        homePage.getHomePage();
        assertEquals(homePage.clickOnLinkByText(linkText).getPageTitle(), title);
    }

    @DataProvider
    public Object[][] getDataForFooterLinksCheck() {
        return new Object[][]{
                {"Асик майнеры", "Купить асики для майнинга | купить Asic | в SMT.UA"},
                {"Майнинг фермы", "Майнинг фермы GPU | Фермы для майнинга на видеокартах | купить в SMT.UA"},
                {"Крипто кошельки", "Криптокошельки: Ledger Nano S, Сoolwallet, BitFreezer, TREZOR"},
                {"Ноутбуки", "Ноутбуки купить в интернет магазине SMT.ua"}
        };
    }

}
