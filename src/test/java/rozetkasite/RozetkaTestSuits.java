package rozetkasite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RozetkaTestSuits {

    private static final String CHROME_PROPERTY_KEY = "webdriver.chrome.driver";
    private static final String CHROME_PROPERTY_VALUE = "chromedriver.exe";

    private static final String ROZETKA_MAIN_PAGE_LINK = "https://rozetka.com.ua";

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @Before
    public void initialWebDriver() {
        System.setProperty(CHROME_PROPERTY_KEY, CHROME_PROPERTY_VALUE);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        webDriverWait = new WebDriverWait(driver, 60);
    }

    @Test
    public void testTabletSamsungFilter() throws InterruptedException {
        driver.get(ROZETKA_MAIN_PAGE_LINK);

        RozetkaMainPage mainPage = new RozetkaMainPage(driver);
        LaptopAndComputerPage laptopAndComputerPage = mainPage.clickLaptopAndComputerLink();

        MainTabletPage mainTabletPage = laptopAndComputerPage.clickTabletsLink();

        AllTabletPage allTabletPage = mainTabletPage.clickAllTabletLink();

        List<WebElement> tabletsBeforeFiltering = allTabletPage.getTabletsWithNameContains("Samsung");
        List<String> textElementBeforeFiltering = allTabletPage.getElementsText(tabletsBeforeFiltering);

        AllTabletPage allTabletPageAfterFilter = allTabletPage.clickSamsungProducerCheckBox();

        driver.navigate().refresh();

        List<WebElement> tabletsAfterFiltering = allTabletPageAfterFilter.getAllTabletsOnPage();
        List<String> textElementAfterFiltering = allTabletPageAfterFilter.getElementsText(tabletsAfterFiltering);

        Assert.assertTrue(textElementAfterFiltering.containsAll(textElementBeforeFiltering));
    }

    @Test
    public void comparisonAddTablets() {

        driver.get(ROZETKA_MAIN_PAGE_LINK);
        RozetkaMainPage mainPage = new RozetkaMainPage(driver);
        LaptopAndComputerPage laptopAndComputerPage = mainPage.clickLaptopAndComputerLink();

        MainTabletPage mainTabletPage = laptopAndComputerPage.clickTabletsLink();

        AllTabletPage allTabletPage = mainTabletPage.clickAllTabletLink();

        allTabletPage.clickSamsungProducerCheckBox();

        allTabletPage.clickFirstTabletSamsungAdd();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        allTabletPage.clickSecondTabletsamsunAdd();
        //    allTabletPage.clickButtonCompare();


    }

    @After
    public void closeWebDriver() {
        driver.close();
    }


}
