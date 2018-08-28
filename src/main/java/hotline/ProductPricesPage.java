package hotline;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ProductPricesPage {

    @FindBy(xpath = "//span[contains(@class, 'price-lg pointer active')]/span[contains(@class, 'value')]")
    private WebElement productPrice;

    @FindBy(xpath = "//a[contains(@class, 'price-lg')]/span[contains(@class, 'price-format')]/span[contains(@class, 'value')]")
    private List<WebElement> productPrices;

    private WebDriver driver;
    private WebDriverWait webDriverWait;


    public ProductPricesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 30);
    }

    public String getProductLowestPrice() {
        String lowestPrice = StringUtils.substringBefore(productPrice.getText(), "–");
        return StringUtils.trim(lowestPrice);
    }

    public String getProductHighestPrice() {
        String highestPrice = StringUtils.substringAfter(productPrice.getText(), "–");
        return StringUtils.trim(highestPrice);
    }

    public List<String> getAllProductPrices() {
        return productPrices.stream()
                .map(price -> price.getText())
                .collect(Collectors.toList());
    }
}
