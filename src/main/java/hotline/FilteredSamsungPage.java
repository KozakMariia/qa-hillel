package hotline;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FilteredSamsungPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public FilteredSamsungPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 30);
    }
}
