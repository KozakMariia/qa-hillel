package hotline;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 20.08.2018.
 */
public class HotlineMainPage {

    @FindBy(xpath = "//li[contains(@class, 'level-1 mobile')]")
    private WebElement navigateToMobileButton;

    public static final String MOBILE_GARNITURE_BUTTON = "//li[contains(@data-menu-id, '1697')]";
    public static final String SMARTPHONE_MOBILE_BUTTON = "//a[contains(@class, 'mobilnye-telefony-i-smartfony')]";

    private WebDriver driver;
    private Actions actions;
    private WebDriverWait webDriverWait;

    public HotlineMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        actions = new Actions(driver);
        webDriverWait = new WebDriverWait(driver, 20);
    }

    public HotlineFilteredSearchResultPage navigateToUrl() {
        actions.moveToElement(navigateToMobileButton).build().perform();
        final WebElement mobileGarnitureButton = navigateToMobileButton.findElement(By.xpath(MOBILE_GARNITURE_BUTTON));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(mobileGarnitureButton));

        actions.moveToElement(mobileGarnitureButton).build().perform();

        final WebElement smartphoneMobileButton = mobileGarnitureButton.findElement(By.xpath(SMARTPHONE_MOBILE_BUTTON));
        smartphoneMobileButton.click();
        return new HotlineFilteredSearchResultPage(driver);
    }
}


