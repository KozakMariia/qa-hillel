package hotline;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotlineFilteredSearchResultPage {

    private final static String FIND_NAME_PHONES_MAIN_PAGE_XPATH = "";
    private static final String PRODUCT_CHECKBOX_XPATH = ".//label[contains(@class,'type-checkbox checkbox-compare')]/input";
    private static final String PRODUCT_NAME_XPATH = ".//p[contains(@class, 'h4')]/a";
    private static final String COMPARE_PRODUCT_ON_POPUP_XPATH = "(.//ul[contains(@class, 'li-default')]/li)[1]";

    @FindBy(xpath = "(//a[@data-filter-link and contains(text(), 'Samsung')])[1]")
    private WebElement samsungCheckbox;

    @FindBy(xpath = "//li[contains(@class, 'product-item')]")
    private List<WebElement> products;

    @FindBy(xpath = "//div[contains(@class, 'item-compare')]/div")
    private WebElement compareButton;

    @FindBy(xpath = "(//ul[contains(@class, 'li-default')]/li)[1]")
    private WebElement compareButtonProduct;

    @FindBy(xpath = "(//li[contains(@class, 'product-item')])[2]")
    private WebElement q;

    @FindBy(xpath = "//div[contains(@class, 'dropdown-bd active')]")
    private WebElement compareProductPopUp;

    @FindBy(xpath = "//div[contains(@class, 'item item-product')]")
    private List<WebElement> productsOnPage;

    @FindBy(xpath = "(//a[contains(@class, 'btn-orange btn-cell')])[2]")
    private WebElement comparePricesButton;

    private List<String> chosenElementsNames = new ArrayList<>();

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public HotlineFilteredSearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 30);
    }

    public FilteredSamsungPage clickSamsungProducerCheckBox() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", samsungCheckbox);

        clickOnElement(samsungCheckbox);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new FilteredSamsungPage(driver);
    }

    public void chooseProduct(int index) {
        WebElement product = products.get(index);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(product));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

        WebElement checkbox = product.findElement(By.xpath(PRODUCT_CHECKBOX_XPATH));
        clickOnElement(checkbox);
        webDriverWait.until(ExpectedConditions.elementToBeSelected(checkbox));

        WebElement productName = product.findElement(By.xpath(PRODUCT_NAME_XPATH));
        chosenElementsNames.add(productName.getText());
    }

    public void clickCompareButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", compareButton);
        clickOnElement(compareButton);
    }

    public HotlineComparePage clickCompareButtonProduct() {
        webDriverWait.until(ExpectedConditions.visibilityOf(compareProductPopUp));

        WebElement compareBtn = compareProductPopUp.findElement(By.xpath(COMPARE_PRODUCT_ON_POPUP_XPATH));
        compareBtn.click();

        return new HotlineComparePage(driver);
    }

    private void clickOnElement(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));

        JavascriptExecutor jsOne = (JavascriptExecutor) driver;
        jsOne.executeScript("arguments[0].click();", webElement);
    }

    public List<String> getChosenElementsNames(String substringBefore) {
        return chosenElementsNames.stream()
                .map(name -> StringUtils.substringBefore(name, substringBefore))
                .collect(Collectors.toList());
    }

    public ProductPricesPage clickComparePricesButton() {
        webDriverWait.until(ExpectedConditions.visibilityOf(q));

        webDriverWait.until(ExpectedConditions.elementToBeClickable(comparePricesButton));
        comparePricesButton.click();

        return new ProductPricesPage(driver);
    }
}
