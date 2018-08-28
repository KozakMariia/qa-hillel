package rozetkasite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RozetkaMainPage {

    @FindBy(id = "2416")
    private WebElement laptopAndComputerLink;

    private WebDriver driver;

    public RozetkaMainPage(final WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LaptopAndComputerPage clickLaptopAndComputerLink() {
        laptopAndComputerLink.click();
        return new LaptopAndComputerPage(driver);
    }
}
