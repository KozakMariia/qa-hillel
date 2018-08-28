package rozetkasite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainTabletPage {

     @FindBy(xpath = "//a[span[contains(text(), 'планшеты')]]")
     private WebElement allTabletLink;

    private WebDriver driver;

    public MainTabletPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AllTabletPage clickAllTabletLink(){
        allTabletLink.click();
        return new AllTabletPage(driver);
    }
}
