package my.company.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import my.company.steps.WebDriverSteps;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 28.10.13
 */
public class SearchTest {

    private WebDriverSteps steps;

    @Before
    public void setUp() throws Exception {
        ChromeDriverManager.getInstance().setup();
        steps = new WebDriverSteps(new ChromeDriver());
    }

    @Test
    public void searchTest() throws Exception {
        steps.openMainPage();
        steps.searchGoogle("Allure framework", null);
        steps.makeScreenShot();
        steps.quit();
    }
}

