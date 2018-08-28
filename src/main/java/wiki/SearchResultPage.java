package wiki;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {

    @FindBy(id = "firstHeading")
    private WebElement firstHeading;

    public SearchResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public String getFirstHeadingText(){
        return firstHeading.getText();
    }
}
