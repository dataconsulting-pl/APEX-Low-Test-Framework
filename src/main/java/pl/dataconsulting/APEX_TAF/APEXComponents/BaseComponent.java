package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@APEXComponent
public class BaseComponent {

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;


    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
    }


    public void navigateToUrl(String url) {
        driver.get(url);
    }

    protected void sendKeys(By by, String textToSend) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToSend);

    }

    /**
     * Switches content back to main frame
     */
    public void switchToMainFrame() {

        driver.switchTo().defaultContent();
    }

    /**
     * Switches content back to given frame
     *
     * @param frameTitle - title of the iframe
     */
    public void switchToFrame(String frameTitle) {

        final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {

            if (iframe.getAttribute("title").equalsIgnoreCase(frameTitle)) {
                driver.switchTo().frame(iframe);
                break;
            }
        }
    }

    /**
     * Sends keys to webElement
     *
     * @param element    - webElement to where the key must be sent
     * @param textToSend - text to be sent
     */
    protected void sendKeys(WebElement element, String textToSend) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToSend);

    }

    /**
     * Sends key to driver
     *
     * @param key - key to be sent
     */
    protected void sendKey(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key);
        actions.perform();
    }

    /**
     * Gets text from webElement
     *
     * @param element - webElement from which text should be returend
     * @return - returns text of the given webElement
     */
    protected String getTextFromWebElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }


    /**
     * Gets webElement by its label
     *
     * @param label - label of the APEX component
     * @return - returns webElement of the corresponding label
     */
    protected WebElement getWebElementByLabel(String label) {
        String action = "Search element by its label";
        String ELEMENT_TEMPLATE_XPATH_STARTS_WITH = "//*[contains(@class,'t-Form-label') and starts-with(text(),'%s')]/.";
        String ELEMENT_TEMPLATE_XPATH_CONTAINS = "//*[contains(@class,'t-Form-label') and starts-with(text(),'%s')]/.";
        String xpath = String.format(ELEMENT_TEMPLATE_XPATH_STARTS_WITH, label);

        WebElement element = driver.findElement(By.xpath(xpath));
        if (element == null) {
            // try to find element using CONTAINS
            xpath = String.format(ELEMENT_TEMPLATE_XPATH_CONTAINS, label);
            element = driver.findElement(By.xpath(xpath));

        }
        String searchElementId = element.getAttribute("for");
        if (searchElementId == null) {
            Assert.assertEquals("Web element could not be found", "Web element can be found",
                    "Searched webElement: " + label);
            return null;
        }
        WebElement searchedWebElement = driver.findElement(By.id(searchElementId));
        return searchedWebElement;
    }

    /**
     * Gets validation webElement by its label
     *
     * @param label - label of the APEX component
     * @return - returns validation webElement of the corresponding label
     */
    protected WebElement getValidationWebElementByLabel(String label) {
        String action = "Search element by its label";
        String ELEMENT_TEMPLATE_XPATH = "//*[contains(@class,'t-Form-label') and starts-with(text(),'%s')]/.";
        String xpath = String.format(ELEMENT_TEMPLATE_XPATH, label);

        WebElement element = driver.findElement(By.xpath(xpath));
        String searchElementId = element.getAttribute("for");
        if (searchElementId == null) {
            Assert.assertEquals("Web element could not be found", "Web element can be found",
                    "Searched webElement: " + label);
            return null;
        }
        WebElement searchedWebElement = driver.findElement(By.id(searchElementId + "_error"));
        return searchedWebElement;
    }

    /**
     * Waits until u-Processing class is visible
     */
    protected void waitForApex() {
        try {
            Thread.sleep(200);
            while (driver.findElements(By.className("u-Processing")).size() > 0) {
                Thread.sleep(200);
            }
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets selected options of APEX item using JS
     *
     * @param element - corresponding webElement
     * @return - returns list of selected option
     */
    protected List<String> getSelectedOptions(WebElement element) {
        String script = String.format("return apex.item( \"%s\" ).displayValueFor( apex.item( \"%s\").getValue() )",
                element.getAttribute("id"),
                element.getAttribute("id"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(script, " ");
        if (jsResult instanceof String stringResult) {
            return Arrays.stream(stringResult.split(",")).toList();
        } else if (jsResult instanceof ArrayList<?>) {
            return (List<String>) jsResult;
        } else
            return null;
    }

    /**
     * Gets selected option of APEX item using JS
     *
     * @param element - corresponding webElement
     * @return - returns selected option
     */
    protected String getSelectedOption(WebElement element) {
        String script = String.format("return apex.item( \"%s\" ).displayValueFor( apex.item( \"%s\").getValue() )",
                element.getAttribute("id"),
                element.getAttribute("id"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(script, " ");
        if (jsResult instanceof String stringResult) {
            return stringResult;
        } else if (jsResult instanceof ArrayList<?>) {
            return jsResult.toString();
        } else
            return null;
    }

    /**
     * Gets value of APEX item using JS
     *
     * @param element - corresponding webElement
     * @return - returns value of the APEX item
     */
    protected String getValueJS(WebElement element) {
        String script = String.format("return apex.item( \"%s\" ).getValue()",
                element.getAttribute("id"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(script, " ");
        if (jsResult instanceof String stringResult) {
            return stringResult;
        } else {
            Assert.assertEquals("Value read from APEX element: " + element.getAttribute("id") + " is not String value",
                    "Value read from APEX element " + element.getAttribute("id") + " is String value",
                    "Verify value returned by APEX element.");
            return null;
        }
    }


    // == private functions ==

    /**
     * Sets the Value of APEX item using JS
     *
     * @param item      - name of the APEX item
     * @param elementId - id of the element
     */
    protected void setValueJS(String item, String elementId) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String SetValueTemplate = "apex.item( \"%s\").setValue(\"%s\")";
        js.executeScript(String.format(SetValueTemplate, item, elementId), "");
    }

    /**
     * Sets the Value of APEX item using JS
     *
     * @param element
     * @param elementId
     */
    protected void setValueJS(WebElement element, String elementId) {
        String item = element.getAttribute("id");
        setValueJS(item, elementId);
    }


}
