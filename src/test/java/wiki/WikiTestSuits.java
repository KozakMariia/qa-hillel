package wiki;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WikiTestSuits {
    private static final String CHROME_PROPERTY_KEY = "webdriver.chrome.driver";
    private static final String CHROME_PROPERTY_VALUE = "chromedriver.exe";

    private static final String WIKI_MAIN_PAGE_LINK = "https://uk.wikipedia.org";

    private static final String TEXT_TO_SEARCH = "Java";
    private static final String CHECKED_WORD_IN_FIRST_HEADING = "Java";

    private WebDriver driver;

    @Before
    public void initialWebDriver() {
        System.setProperty(CHROME_PROPERTY_KEY, CHROME_PROPERTY_VALUE);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void checkFirstHeadingContainsWorld() {
        driver.get(WIKI_MAIN_PAGE_LINK);

        final WikiMainPage mainPage = new WikiMainPage(driver);
        mainPage.typeSearchText(TEXT_TO_SEARCH);

        final SearchResultPage clickButton = mainPage.clickFindButton();
        String firstHeadingText = clickButton.getFirstHeadingText();

        Assert.assertTrue("The main header doesn`t contain the word 'Java'",
                firstHeadingText.contains(CHECKED_WORD_IN_FIRST_HEADING));
    }

    @After
    public void closeWebDriver() {
        driver.close();
    }
}
