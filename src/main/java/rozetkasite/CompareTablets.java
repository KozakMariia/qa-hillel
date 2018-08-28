package rozetkasite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompareTablets {

    @FindBy(xpath = "//a[contains(@class, 'btn-link btn-link-gray')]")
    private WebElement buttonCompareTheseItems;

    private final WebDriver driver;

    public CompareTablets(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ResultPageCompareTablets clickButtonCompareTheseItems() {
        buttonCompareTheseItems.click();
        return new ResultPageCompareTablets(driver);
    }




}
