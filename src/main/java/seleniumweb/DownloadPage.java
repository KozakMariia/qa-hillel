package seleniumweb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class DownloadPage {

    @FindBys(@FindBy(xpath = "//div[@id='mainContent']/table[1]/tbody/tr/td[1]"))
    private List<WebElement> downloadTableFirstColumn;

    public DownloadPage(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getFirstColumnText(){
        List<String> firstColumnText = new ArrayList<String>();
        for (WebElement cell : downloadTableFirstColumn){
            firstColumnText.add(cell.getText());
        }
        return firstColumnText;
    }

}
