package hotline;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class HotlineTestSuits {

    private WebDriver driver;

    @Before
    public void driverSetUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://hotline.ua/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void verifyHotlineCheckList() {
        HotlineMainPage mainPage = new HotlineMainPage(driver);
        HotlineFilteredSearchResultPage productsSearchResultPage = mainPage.navigateToUrl();

        productsSearchResultPage.chooseProduct(0);
        productsSearchResultPage.chooseProduct(1);

        List<String> productsOnMainPage = productsSearchResultPage.getChosenElementsNames();

        productsSearchResultPage.clickCompareButton();
        HotlineComparePage hotlineComparePage = productsSearchResultPage.clickCompareButtonProduct();

        List<String> compareProductsName = hotlineComparePage.getAllCompareProductsNames();

        Assert.assertThat(productsOnMainPage, containsInAnyOrder(compareProductsName.toArray()));

        hotlineComparePage.clickDeleteButton();
        hotlineComparePage.clickYesDeleteButton();

        Assert.assertTrue(hotlineComparePage.getAllProductsOnPage().size() == 0);
    }

    @Test
    public void verifyProductPrice() {
        HotlineMainPage mainPage = new HotlineMainPage(driver);
        HotlineFilteredSearchResultPage productsSearchResultPage = mainPage.navigateToUrl();

        ProductPricesPage productPricePage = productsSearchResultPage.clickComparePricesButton();

        String productLowestPrice = productPricePage.getProductLowestPrice();
        String productHighestPrice = productPricePage.getProductHighestPrice();

        List<String> productPrices = productPricePage.getAllProductPrices();

        Assert.assertTrue(productPrices.contains(productLowestPrice));
        Assert.assertTrue(productPrices.contains(productHighestPrice));
    }

    @Test
    public void verifyProductsSumsung() {
        HotlineMainPage mainPage = new HotlineMainPage(driver);
        HotlineFilteredSearchResultPage productsSearchResultPage = mainPage.navigateToUrl();

        productsSearchResultPage.clickSamsungProducerCheckBox();

        productsSearchResultPage.chooseProduct(0);
        productsSearchResultPage.chooseProduct(1);
        productsSearchResultPage.chooseProduct(2);

        List<String> productsOnMainPage = productsSearchResultPage.getChosenElementsNames();

        productsSearchResultPage.clickCompareButton();
        HotlineComparePage hotlineComparePage = productsSearchResultPage.clickCompareButtonProduct();

        List<String> compareProductsName = hotlineComparePage.getAllCompareProductsNames();

        Assert.assertThat(productsOnMainPage, containsInAnyOrder(compareProductsName.toArray()));
    }

    @Test
    public void verifyTypesProductsSumsung() {
        HotlineMainPage mainPage = new HotlineMainPage(driver);
        HotlineFilteredSearchResultPage productsSearchResultPage = mainPage.navigateToUrl();

        productsSearchResultPage.clickSamsungProducerCheckBox();

        productsSearchResultPage.chooseProduct(0);
        productsSearchResultPage.chooseProduct(1);
        productsSearchResultPage.chooseProduct(2);

        productsSearchResultPage.clickCompareButton();
        HotlineComparePage hotlineComparePage = productsSearchResultPage.clickCompareButtonProduct();

        List<String> productTypes = hotlineComparePage.getTypeAllProducts();
        for (String productType : productTypes) {
            Assert.assertEquals("Смартфон", productType);
        }

        List<String> productSimTypes = hotlineComparePage.getTypeSimAllProducts();
        for (String productSimType : productSimTypes) {
            Assert.assertEquals("Nano-SIM", productSimType);
        }
    }

    @After
    public void driverTeamDown() {
        driver.close();
    }
}

