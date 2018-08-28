package rozetkasite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaptopAndComputerPage {

    @FindBy(xpath = "//a[contains(text(), 'Планшеты')]")
    private WebElement tabletsLink;




    private WebDriver driver;

    public LaptopAndComputerPage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MainTabletPage clickTabletsLink() {
        tabletsLink.click();
        return new MainTabletPage(driver);
    }
}
