package core;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;


public class WikiTest extends BaseTest {

    private String wikiLogin;
    private String wikiPassword;

    @Test(priority = 0)
    @Parameters({"login","password"})
    public void wikiLoginPageTest(String login, String password) {
        /*
        В тесте проверяется работа функциональности авторизации на сайте Википедии. Данные для логина
        указываются в xml-файле конфигурации для TestNG, предполагается, что пользователь с такими данными действительно
        существует
         */
        WikiLoginPage wikiLoginPage = new WikiLoginPage(driver);
        wikiLoginPage.goToLoginPage();
        wikiLoginPage.fillLoginField(login);
        wikiLoginPage.fillPasswordField(password);
        wikiLoginPage.loginSubmit();
        Assert.assertTrue(wikiLoginPage.loginSuccess(), "Логин в Википедии не удался");
    }

    @Test(priority = 1, dependsOnMethods = "wikiLoginPageTest")
    public void wikiKeywordPageTest(){
        /*
        В тесте часть тематической статьи записывается в указанный текстовый файл в корне проекта, после чего
        открывается главная страница Википедии
         */
        WikiKeywordPage wikiKeywordPage = new WikiKeywordPage(driver);
        wikiKeywordPage.writeIntoFile("text.txt");
        Assert.assertTrue(wikiKeywordPage.fileIsCreated(), "Текст статьи не записан в файл");
        wikiKeywordPage.getToMainPage();
    }

    @Test(priority = 2, dependsOnMethods = "wikiKeywordPageTest")
    public void wikiMainPageTest(){
        /*
        В тесте проверяется совпадение адресов ссылок в названии предлагаемой "хорошей" статьи и кнопке "Читать" в
        том же блоке, при успешном прохождении открывается полная версия статьи
         */
        WikiMainPage wikiMainPage = new WikiMainPage(driver);
        Assert.assertTrue(wikiMainPage.urlsAreSimilar(), "Ссылки не совпадают");
        wikiMainPage.goToCurrentGoodArticle();
    }

    @Test(priority = 3, dependsOnMethods = "wikiMainPageTest")
    public void goodArticlePageTest(){
        /*
        В тесте проверяется функциональность скачивания статьи в формате pdf
         */
        GoodArticlePage goodArticlePage = new GoodArticlePage(driver);
        goodArticlePage.goToDownloadPage();
        goodArticlePage.downloadArticleAsPdf();
        Assert.assertTrue(goodArticlePage.fileIsDownloaded(), "Статья не скачана");
    }
}
