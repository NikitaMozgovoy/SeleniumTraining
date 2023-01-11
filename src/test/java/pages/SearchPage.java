package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchPage extends BasePage{

    @FindBy(name = "q")
    private WebElement inputArea;

    @FindBy(name = "btnK")
    private WebElement inputButton;


    public void keywordInput(String keyword){
        inputArea.click();
        inputArea.clear();
        inputArea.sendKeys(keyword);
    }

    public void submitKeyword(){
        inputButton.click();
    }

    public void extractSearchByKeyword(String keyword){
        keywordInput(keyword);
        submitKeyword();
    }

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}