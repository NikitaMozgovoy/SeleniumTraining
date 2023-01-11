package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage extends BasePage {

    String expectedTitle = "аниме - Поиск в Google";

    @FindBy(xpath = "(//div[@class='yuRUbf']//h3[contains(text(), 'Википедия')])[1]/parent::a")
    private WebElement wikiLink;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openWiki(){
        wikiLink.click();
    }

    public boolean pageIsOpened(){
        return expectedTitle.equals(driver.getTitle());
    }

}
