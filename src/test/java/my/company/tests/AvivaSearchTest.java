/*
 * Copyright 2017 Marcel.gutierrez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package my.company.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import my.company.steps.WebDriverSteps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 * @author Marcel.gutierrezz
 */
public class AvivaSearchTest {
    private WebDriverSteps steps;
    public static final String HOME_URL = "http://www.aviva.es/";
    
    
    @Before
    public void setUp() throws Exception {
        ChromeDriverManager.getInstance().setup();
        steps = new WebDriverSteps(new ChromeDriver());
    }

    @Test
    public void searchTest() throws Exception {
        steps.openMainPage();
        steps.searchGoogle("Aviva", "Grupo Aviva - compañía de seguros líder en Europa, el mayor grupo ...");
        steps.makeScreenShot();
        
        WebElement aviva= checkFirstResult("Grupo Aviva - compañía de seguros líder en Europa, el mayor grupo ...");
        
        Assert.assertEquals(HOME_URL, aviva.getAttribute("href"));
        steps.forceClickIn("Pulsar primer elemento de la busqueda google ",aviva,"headerNav");
        steps.makeScreenShot();
        steps.quit();
    }
    
    
    private WebElement checkFirstResult(String condition) {
        return steps.findElementByLinkText(condition);
    }
}
