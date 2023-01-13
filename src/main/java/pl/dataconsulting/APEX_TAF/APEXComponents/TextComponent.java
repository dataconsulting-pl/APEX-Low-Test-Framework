package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class TextComponent extends BaseComponent {

    // == String Item action functions ==

    /**
     * Enters text to the text item component
     *
     * @param frameTitle - name of the frame
     * @param fieldName  - name of the field
     * @param text       - text to enter
     */
    public void enterStringIntoTextItem(String frameTitle, String fieldName, String text) {
        switchToFrame(frameTitle);
        enterStringIntoTextItem(fieldName, text);
    }

    /**
     * Enters text to the text item component
     *
     * @param fieldName - name of the field
     * @param text      - text to enter
     */
    public void enterStringIntoTextItem(String fieldName, String text) {
        WebElement element = getWebElement(fieldName);
        // check if element is autocomplete
        if (element.getAttribute("class").contains("apex-item-group--auto-complete")) {
            String id = element.getAttribute("id");
            executeJSCommand(String.format("return apex.item('%s').setValue('%s')", id, text));
        } else {
            sendKeys(getWebElement(fieldName), text);
        }


    }

    /**
     * Sends key to the text item component
     *
     * @param fieldName - name of the field
     * @param key       - key to be sent
     */
    public void sendKeyIntoTextItem(String fieldName, Keys key) {
        getWebElement(fieldName).sendKeys(key);

    }

    // == String Item verification functions ==

    /**
     * Verifies text in the text field
     *
     * @param fieldName     - name of the filed
     * @param expectedValue - expected value
     */
    public void verifyTextItemValue(String fieldName, String expectedValue) {
        Assert.assertEquals(getTextFromWebElement(getWebElementByLabel(fieldName)), expectedValue);

    }

    /**
     * Gets text from text field
     *
     * @param fieldName - name of the filed
     */
    public String getTextItemValue(String fieldName) {
        return getTextFromWebElement(getWebElementByLabel(fieldName));

    }

    /**
     * Verifies validation message of the corresponding field
     *
     * @param frameName       - name of the frame
     * @param fieldName       - field name
     * @param expectedMessage - expected message
     */
    public void verifyValidationMessage(String frameName, String fieldName, String expectedMessage) {
        switchToFrame(frameName);
        verifyValidationMessage(fieldName,expectedMessage);
        Assert.assertEquals(getValidationWebElementByLabel(fieldName).getText(), expectedMessage);
    }

    /**
     * Verifies validation message of the corresponding field
     *
     * @param fieldName       - field name
     * @param expectedMessage - expected message
     */
    public void verifyValidationMessage(String fieldName, String expectedMessage) {
        Assert.assertEquals(getValidationWebElementByLabel(fieldName).getText().trim(), expectedMessage.trim());
    }


}
