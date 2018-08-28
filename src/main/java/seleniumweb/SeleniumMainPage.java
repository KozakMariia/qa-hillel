package seleniumweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumMainPage {

    @FindBy(id = "menu_download")
    private WebElement downloadTab;

    private WebDriver driver;

    public SeleniumMainPage(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DownloadPage clickDownloadTab(){
        downloadTab.click();
        return new DownloadPage(driver);
    }

}
