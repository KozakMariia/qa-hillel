package wiki;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiMainPage {

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    private WebDriver driver;

    public WikiMainPage(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeSearchText(final String textToSearch){
        searchInput.sendKeys(textToSearch);
    }

    public SearchResultPage clickFindButton(){
        searchButton.click();
        return new SearchResultPage(driver);
    }
}
