package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://ru.wikipedia.org/w/index.php?title=%D0%A1%D0%BB%D1%83%D0%B6%D0%B5%D0%B1%D0%BD%D0%B0%D1%8F:%D0%92%D1%85%D0%BE%D0%B4&returnto=%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F+%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0
public class WikiLoginPage extends BasePage{

    @FindBy(id = "wpName1")
    private WebElement loginInputField;

    @FindBy(id = "wpPassword1")
    private WebElement passwordInputField;

    @FindBy(id = "wpLoginAttempt")
    private WebElement loginSubmitButton;

    @FindBy(xpath = "//li[@id='pt-login']/a")
    private WebElement loginButton;

    private String keywordPageUrl;

    public void goToLoginPage(){
        keywordPageUrl=driver.getCurrentUrl();
        loginButton.click();
    }

    public void fillLoginField(String login){
        loginInputField.click();
        loginInputField.clear();
        loginInputField.sendKeys(login);
    }

    public void fillPasswordField(String password){
        passwordInputField.click();
        passwordInputField.clear();
        passwordInputField.sendKeys(password);
    }

    public void loginSubmit(){
        loginSubmitButton.click();
    }

    public boolean loginSuccess(){
        return driver.getCurrentUrl().equals(keywordPageUrl);
    }

    public WikiLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}