package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// https://ru.wikipedia.org/wiki/%D0%90%D0%BD%D0%B8%D0%BC%D0%B5
public class WikiKeywordPage extends BasePage{

    @FindBy(css = ".mw-wiki-logo")
    private WebElement wikiMainLink;

    @FindBy(xpath = "//p[count(preceding-sibling::h2)=1]")
    private WebElement infoToStore;

    private String fileName;
    
    public WikiKeywordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void writeIntoFile(String name){
        this.fileName=name;
        String text = infoToStore.getText();
        try(FileWriter writer = new FileWriter(name, false)){
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean fileIsCreated(){
        File file = new File(System.getProperty("user.dir") + File.separator + this.fileName);
        return file.exists();
    }

    public void getToMainPage(){
        wikiMainLink.click();
    }
}