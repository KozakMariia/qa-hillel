package seleniumweb;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumTestSuits {

    private static final String CHROME_PROPERTY_KEY = "webdriver.chrome.driver";
    private static final String CHROME_PROPERTY_VALUE = "chromedriver.exe";

    private static final String SELENIUM_MAIN_PAGE_LINK = "https://www.seleniumhq.org";

    private WebDriver driver;

    @Before
    public void initialWebDriver() {
        System.setProperty(CHROME_PROPERTY_KEY, CHROME_PROPERTY_VALUE);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void compareList(){
        driver.get(SELENIUM_MAIN_PAGE_LINK);
        final SeleniumMainPage mainPage = new SeleniumMainPage(driver);
        DownloadPage downloadPage = mainPage.clickDownloadTab();

        List<String> firstColumn = downloadPage.getFirstColumnText();

        List<String> expectedFirstColumn = Arrays.asList("Java", "C#", "Ruby", "Python", "Javascript (Node)");
        Assert.assertTrue(expectedFirstColumn.size() == firstColumn.size());
        Assert.assertTrue(expectedFirstColumn.containsAll(firstColumn));
    }

    @After
    public void closeWebDriver() {
        driver.close();
    }

}
