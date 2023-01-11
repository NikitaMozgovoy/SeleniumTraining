package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.BasePage;

import java.io.File;
import java.time.Duration;
import java.util.Hashtable;
import java.util.Map;

abstract public class BaseTest {
    protected static WebDriver driver;

    @BeforeTest
    public void setUp(){
        // Установка драйвера из сети или локальной версии
        System.setProperty("webdriver.chrome.driver","../chromedriver.exe");
//        WebDriverManager.chromedriver().setup();

        // Установка директории для загрузки через Selenium
        String defaultDownloadDirectory = System.getProperty("user.dir")+ File.separator+"articles";
        Map<String, Object> preferences = new Hashtable<String, Object>();

        // Убираем всплывающие окна
        preferences.put("profile.default_content_settings.popups", 0);

        // Убираем подсказки при загрузке
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", defaultDownloadDirectory);

        // Выключаем просмотр PDF-файлов в браузере вместо скачивания
        preferences.put("plugins.plugins_disabled", new String[]{
                "Adobe Flash Player", "Chrome PDF Viewer"});

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        BasePage.setDriver(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
