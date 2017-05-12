package my.company.steps;

import com.google.common.base.Predicate;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * The content of the following class is for demonstration purposes only. In
 * your project you should organize your code in the different way, an example
 * move all the selectors to separate place. Usually better to use some other
 * library to organize access to your service pages such as Yandex HTMLElements.
 *
 * @author Dmitry Baev charlie@yandex-team.ru Date: 28.10.13
 */
public class WebDriverSteps {

    static final String ID = "ID";
    static final String XPATH = "XPATH";
    static final String CSS = "CSS";
    static final String CLASS_NAME = "CLASS_NAME";
    public static final String[] HEADERS = {"Inicio", "Quiénes somos", "Productos", "Responsabilidad Corporativa", "Prensa", "Empleo", "Contacto"};
    public static final String[] HEADER_BOX = {"Aviva Vida y Pensiones", "Aviva Gestión"};
    public static final String HEADER_NAV = "headerNav";
    public static final String HOME_URL = "http://www.aviva.es/";
    private static final String HOME_TITLE = "Grupo Aviva - compañía de seguros líder en Europa, el mayor grupo asegurador de Reino Unido y uno de los principales proveedores de seguros de vida y generales de Europa";


    private WebDriver driver;
    private WebDriverWait wait;
            
    public WebDriverSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void openMainPage() {
        driver.get("http://google.es");
    }

    @Step
    public void searchGoogle(String text, String condition) {
        driver.findElement(By.id("lst-ib")).sendKeys(text + Keys.RETURN);
        
        if (condition!=null){
            wait = new WebDriverWait(driver,100);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(condition)));
        }
        
    }

    @Attachment
    @Step("Make screen shot of results page")
    public byte[] makeScreenShot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Find Element by Id")
    public WebElement findElementById(String text) {
        WebElement element = null;
        WebElement elementaux;

        elementaux = driver.findElement(By.id("text"));

        if (null != elementaux) {
            element = elementaux;
        }

        return element;
    }

    @Step("Find Element by linkText")
    public WebElement findElementByLinkText(String text) {
        WebElement element = null;
        WebElement elementaux;

        elementaux = driver.findElement(By.linkText(text));

        if (null != elementaux) {
            element = elementaux;
        }
        return element;
    }

    @Step("Click")
    public void forceClick(WebElement element, String condition) {
        if (driver.toString().contains("InternetExplorerDriver")) {
            element.sendKeys(Keys.ENTER);
        } else {
            element.click();
        }
        
        if (condition!=null){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(condition)));
        Assert.assertEquals(HOME_TITLE, driver.getTitle());
        }
    }

    public void quit() {
        driver.quit();
    }

    private Predicate<WebDriver> mainContainLoaded() {
        return new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver input) {
                return driver.findElement(By.className("main__content")).isDisplayed();
            }
        };
    }

}
