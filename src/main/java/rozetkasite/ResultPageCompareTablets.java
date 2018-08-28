package rozetkasite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ResultPageCompareTablets {

    @FindBy(xpath = "//div[contains(@name, 'equal')][2]/div[2]/div/div")
    private WebElement screenViewFirstTablet;

    @FindBy(xpath = "//div[contains(@name, 'equal')][2]/div[3]/div/div")
    private WebElement screenViewSecondTablet;

    @FindBy(xpath = "//div[contains(@name, 'equal')][4]/div[2]/div/div/div")
    private WebElement screenDiagonalFirstTablet;

    @FindBy(xpath = "//div[contains(@name, 'equal')][4]/div[3]/div/div/div")
    private WebElement screenDiagonalSecondTablet;

    @FindBy(xpath = "//div[contains(@name, 'equal')][5]/div[2]/div/div")
    private WebElement ramFirstTablet;

    @FindBy(xpath = "//div[contains(@name, 'equal')][5]/div[2]/div/div")
    private WebElement ramSecondTablet;

    public ResultPageCompareTablets(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getScreenViewFirstTablet() {
        return screenViewFirstTablet.getText();
    }

    public String getScreenViewSecondTablet() {
        return screenViewSecondTablet.getText();
    }

    public String getScreenDiagonalFirstTablet() {
        return screenDiagonalFirstTablet.getText();
    }

    public String getScreenDiagonalSecondTablet() {
        return screenDiagonalSecondTablet.getText();
    }

    public String getRamFirstTablet() {
        return ramFirstTablet.getText();
    }

    public String getRamSecondTablet() {
        return ramSecondTablet.getText();
    }
}
