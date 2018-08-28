package rozetkasite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AllTabletPage {

    private static final String FIND_NAME_TABlET_XPATH = ".//div[contains(@name, 'goods_item_with_promotion')]//" +
            "div[contains(@class, 'g-i-tile-i-title clearfix')]/a";

    @FindBy(xpath = "//a[@name = 'producer_12']")
    private WebElement samsungProducerCheckBox;

    @FindBy(xpath = "//a[contains(text(), 'SM-T580NZKASEK')]/parent::div/following-sibling::div[contains(@class," +
            "'g-tools-container')]//span[@class='g-compare']")
    private WebElement firstTabletSamsungAdd;

    @FindBy(xpath = "//a[contains(text(), 'SM-T585NZKASEK')]/parent::div/following-sibling::div[contains(@class," +
            "'g-tools-container')]//span[@class='g-compare']")
    private WebElement secondTabletSamsungAdd;

    @FindBy(xpath = "//a[contains(@class, 'hub-i-link hub-i-link-empty hub-i-comparison-link sprite-side whitelink')]")
    private WebElement buttonCompare;

    @FindBys(@FindBy(xpath = "//div[@id = 'catalog_goods_block']//div[@class = 'g-i-tile g-i-tile-catalog']"))
    private List<WebElement> allTabletsOnFirstPage;

    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

    public AllTabletPage(WebDriver driver) {
        this.driver = driver;

        webDriverWait = new WebDriverWait(driver, 60, 2000);
        PageFactory.initElements(driver, this);
    }

    public AllTabletPage clickSamsungProducerCheckBox() {
        samsungProducerCheckBox.click();
        return new AllTabletPage(driver);
    }

    public void clickFirstTabletSamsungAdd() {
        firstTabletSamsungAdd.click();
    }

    public void clickSecondTabletsamsunAdd() {
        secondTabletSamsungAdd.click();
    }

    public CompareTablets clickButtonCompare() {
        buttonCompare.click();
        return new CompareTablets(driver);
    }

    public List<WebElement> getAllTabletsOnPage() {
        return allTabletsOnFirstPage;
    }

    public List<WebElement> getTabletsWithNameContains(String namePart) {
        List<WebElement> tablets = new ArrayList<>();

        for (WebElement tablet : allTabletsOnFirstPage) {
            WebElement nameElement = tablet.findElement(By.xpath(FIND_NAME_TABlET_XPATH));
            if (nameElement.getText().contains(namePart)) {
                tablets.add(tablet);
            }
        }
        return tablets;
    }

    public List<String> getElementsText(List<WebElement> elements) {
        List<String> text = new ArrayList<>();

        for (WebElement element : elements) {
            text.add(element.getText());
        }
        return text;
    }
}
