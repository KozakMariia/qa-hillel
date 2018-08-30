package hotline;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HotlineComparePage {

    private final static String FIND_NAME_PHONES_XPATH = ".//div[contains(@class, 'title-overflow')]/a";

    @FindBy(xpath = "//div[contains(@class, 'item item-product')]")
    private List<WebElement> allProductsOnPage;

    @FindBy(xpath = "//a[contains(@class, 'action-link')]/i[contains(@class, 'delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[contains(@class, 'btn-graphite')]")
    private WebElement yesDeleteButton;

    @FindBy(xpath = "//div[contains(@data-compare-row, '5221')]/p")
    private List<WebElement> typeProducts;

    @FindBy(xpath = "//div[contains(@data-compare-row, '16498')]/p")
    private List<WebElement> typeSimProducts;

    private WebDriverWait webDriverWait;
    private WebDriver driver;

    public HotlineComparePage(WebDriver driver) {
        Set<String> windowHandles = new HashSet<>(driver.getWindowHandles());
        windowHandles.remove(driver.getWindowHandle());

        driver = driver.switchTo().window(windowHandles.iterator().next());
        this.driver = driver;
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 20);
    }


    public List<String> getAllCompareProductsNames(String substringBefore) {
        List<String> phones = new ArrayList<>();
        for (WebElement phone : allProductsOnPage) {
            WebElement nameElement = phone.findElement(By.xpath(FIND_NAME_PHONES_XPATH));
            String name = nameElement.getText();
            phones.add(StringUtils.substringBefore(name, substringBefore));
        }

        return phones;
    }

    public List<WebElement> getAllProductsOnPage() {
        return allProductsOnPage;
    }

    public void clickDeleteButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
    }

    public void clickYesDeleteButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(yesDeleteButton));
        yesDeleteButton.click();
    }

    public List<String> getTypeAllProducts() {
        return typeProducts.stream()
                .map(type -> type.getText())
                .collect(Collectors.toList());
    }

    public List<String> getTypeSimAllProducts() {
        return typeSimProducts.stream()
                .map(typeSim -> typeSim.getText())
                .collect(Collectors.toList());
    }

}
