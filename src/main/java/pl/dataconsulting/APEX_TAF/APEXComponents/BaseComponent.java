package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
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

    private String ELEMENT_TEMPLATE_XPATH = "//*[contains(@class,'t-Form-label') and starts-with(text(),'%s')]/.";

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

    public void switchToMainFrame() {

        driver.switchTo().defaultContent();
    }

    public void switchToFrame(String frameTitle) {

        final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {

            if (iframe.getAttribute("title").equalsIgnoreCase(frameTitle)) {
                driver.switchTo().frame(iframe);
                break;
            }
        }
    }

    protected void sendKeys(WebElement element, String textToSend) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToSend);

    }

    protected void sendKey(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key);
        actions.perform();
    }

    protected String getFieldValue(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }



    protected WebElement getWebElementByLabel(String label) {
        String action = "Search element by its label";
        String xpath = String.format(ELEMENT_TEMPLATE_XPATH, label);

        WebElement element = driver.findElement(By.xpath(xpath));
        String searchElementId = element.getAttribute("for");
        if (searchElementId == null) {
            //TODO put error
            return null;
        } else {
            //TODO put logs
        }
        WebElement searchedWebElement = driver.findElement(By.id(searchElementId));
        return searchedWebElement;
    }

    protected WebElement getValidationWebElementByLabel(String label) {
        String action = "Search element by its label";
        String xpath = String.format(ELEMENT_TEMPLATE_XPATH, label);

        WebElement element = driver.findElement(By.xpath(xpath));
        String searchElementId = element.getAttribute("for");
        if (searchElementId == null) {
            //TODO put error
            return null;
        } else {
            //TODO put logs
        }
        WebElement searchedWebElement = driver.findElement(By.id(searchElementId + "_error"));
        return searchedWebElement;
    }

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

    protected List<String> getSelectedOptions(WebElement element) {
        String script = String.format("return apex.item( \"%s\" ).displayValueFor( apex.item( \"%s\").getValue() )",
                element.getAttribute("id"),
                element.getAttribute("id"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Object jsResult = js.executeScript(script, " ");
        if (jsResult instanceof String stringResult){
            return Arrays.stream(stringResult.split(",")).toList();
        } else if (jsResult instanceof ArrayList<?>){
            return (List<String>) jsResult;
        }
        else
            return null;
    }


}
