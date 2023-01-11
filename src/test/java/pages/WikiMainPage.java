package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiMainPage extends BasePage{

    @FindBy(xpath = "//div[@id='main-tga']//span[text()='Читать' or text()='Read']/ancestor::a")
    private WebElement readGoodArticleButton;

    @FindBy(xpath = "//div[@id='main-tga']//span[@class='mw-headline']/a")
    private WebElement goodArticleUrl;

    public WikiMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    public void goToCurrentGoodArticle(){
        readGoodArticleButton.click();
    }

    public boolean urlsAreSimilar(){
        return (goodArticleUrl.getAttribute("href").equals(readGoodArticleButton.getAttribute("href")));
    }
}