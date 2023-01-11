package core;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ResultPage;
import pages.SearchPage;

public class GoogleTest extends BaseTest{

    @Test
    public void googleSearchByKeywordTest() {
        /*
        В тесте проверяется открытие главной страницы Google-поиска, поиск по ключевому слову,
        открытие соответствующей запросу страницы выдачи. При успешном прохождении всех тестов для
        дальнейшего использования открывается соответствующая запросу страница Википедии
         */
        driver.get("https://google.com/");
        SearchPage searchPage = new SearchPage(driver);
        searchPage.extractSearchByKeyword("аниме");
        ResultPage resultPage = new ResultPage(driver);
        Assert.assertTrue(resultPage.pageIsOpened(), "Страница с результатами поиска не открылась");
        resultPage.openWiki();
    }
}
