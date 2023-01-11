package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class GoodArticlePage extends BasePage{

    @FindBy(id="coll-download-as-rl")
    private WebElement downloadLink;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement downloadButton;

    private String articleTitle;

    public void goToDownloadPage(){
        this.articleTitle = driver.findElement(By.xpath("//span[@class='mw-page-title-main']")).getText();
        System.out.println(this.articleTitle);
        downloadLink.click();
    }

    public void downloadArticleAsPdf(){
        downloadButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean fileIsDownloaded(){
        File file = new File(System.getProperty("user.dir") + File.separator + "articles"
                + File.separator + articleTitle.replace(" ", "_") + ".pdf");
        return file.exists();
    }

    public GoodArticlePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}