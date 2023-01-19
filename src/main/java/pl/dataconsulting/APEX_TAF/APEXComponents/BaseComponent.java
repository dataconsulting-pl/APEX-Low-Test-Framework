package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;
import pl.dataconsulting.APEX_TAF.framework.service.TestData;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@APEXComponent
public class BaseComponent {

    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @Autowired
    protected TestData testData;


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

    public void saveTestData(String key, String value) {
        testData.savedData(key, value);
    }

    public String getTestData(String key) {
        return testData.getData(key);
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
                waitForApex();
                break;
            }
        }
    }

    /**
     * Accepts alert
     */
    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Error on accepting alert. Alert not present");
        }
    }

    /**
     * Dismiss alert
     */
    public void dismissAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            Assert.fail("Error on dismissing alert. Alert not present");
        }
    }

    /**
     * Send text to Alert
     *
     * @param text - text to be send
     */
    public void sendTextToAlert(String text) {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().sendKeys(text);
        } catch (NoAlertPresentException e) {
            Assert.fail("Error on sending text to alert. Alert not present");
        }
    }

    /**
     * Gets Alert's Text
     *
     * @return - text of the Alert
     */
    public String getAlertText() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return driver.switchTo().alert().getText();
        } catch (NoAlertPresentException e) {
            Assert.fail("Error on sending text to alert. Alert not present");
            return null;
        }
    }

    /**
     * Click on the link by link text
     *
     * @param linkText - text to be clicked
     */
    public void clickByLinkText(String linkText) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText(linkText)))).click();
        ;
    }

    /**
     * Sends keys to webElement
     *
     * @param element    - webElement to where the key must be sent
     * @param textToSend - text to be sent
     */
    protected void sendKeys(WebElement element, String textToSend) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
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
        return element.getAttribute("value");
    }


    protected WebElement getWebElement(String identity) {
        WebElement webElement = getWebElementByLabel(identity);
        if (webElement == null) {
            try {
                webElement = driver.findElement(By.id(identity));
            } catch (NoSuchElementException e) {
                Assert.fail("Element count not be localised", e.getCause());
            }

        }
        return webElement;
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
        String xpath = String.format(ELEMENT_TEMPLATE_XPATH_STARTS_WITH, label);

        WebElement element = null;
        try {
            element = driver.findElement(By.xpath(xpath));
            String searchElementId = element.getAttribute("for");
            if (searchElementId == null) {
                Assert.assertEquals("Web element could not be found", "Web element can be found",
                        "Searched webElement: " + label);
                return null;
            }
            WebElement searchedWebElement = driver.findElement(By.id(searchElementId));
            return searchedWebElement;

        } catch (NoSuchElementException e) {
            // Add some logging, that element was not found
            return null;
        }
    }

    /**
     * Gets validation webElement by its label
     *
     * @param label - label of the APEX component
     * @return - returns validation webElement of the corresponding label
     */
    protected WebElement getValidationWebElementByLabel(String label) {

        WebElement element = getWebElement(label);
        // gets the ID of the element and add _error to the name
        WebElement searchedWebElement = driver.findElement(By.id(element.getAttribute("id") + "_error"));
        return searchedWebElement;
    }

    /**
     * Waits until u-Processing class is visible
     */
    protected void waitForApex() {
        // wait only if alert is not present
        if (!isAlertPresent()) {
            try {
                waitForAjax();
                Thread.sleep(200);
                while (driver.findElements(By.className("u-Processing")).size() > 0) {
                    Thread.sleep(200);
                }
            } catch (InvalidArgumentException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void waitForAjax() throws InterruptedException {
        String jsCommand = String.format("return jQuery.active == 0");
        while (true) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean ajaxResult = (Boolean) js.executeScript(jsCommand);
            if (ajaxResult)
                break;
            Thread.sleep(200);

        }

    }

    /**
     * Gets selected options of APEX item using JS
     *
     * @param element - corresponding webElement
     * @return - returns list of selected option
     */
    protected List<String> getSelectedOptions(WebElement element) {
        String script = String.format("return apex.item( \"%s\" ).getValue()",
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
     * Gets display names of selected options of APEX item using JS
     *
     * @param element - corresponding webElement
     * @return - returns list of selected option
     */
    protected List<String> getSelectedOptionsDisplayName(WebElement element) {
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
     * Gets display name of selected option of APEX item using JS
     *
     * @param element - corresponding webElement
     * @return - returns selected option
     */
    protected String getDisplayValue(WebElement element) {
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


    // == protected functions ==

    /**
     * Check if alert is preset
     *
     * @return true if alert is present, false otherwise
     */
    protected boolean isAlertPresent() {
        try {
            new WebDriverWait(driver, Duration.ofMillis(50)).until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }


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

    /**
     * Executes JS command
     *
     * @param jsCommand - JS command to be executed
     */
    protected String executeJSCommand(String jsCommand) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(jsCommand);
        if (jsResult instanceof String stringResult) {
            return stringResult;
        } else if (jsResult instanceof ArrayList<?>) {
            return jsResult.toString();
        } else
            return null;
    }


}
