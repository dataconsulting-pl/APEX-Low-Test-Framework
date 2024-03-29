package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class FormComponent extends BaseComponent {

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
        sendKeys(getWebElementByLabel(fieldName), text);
    }

    /**
     * Enters text to the text item component
     *
     * @param fieldName - name of the field
     * @param text      - text to enter
     */
    public void enterStringIntoTextItem(String fieldName, String text) {
        sendKeys(getWebElementByLabel(fieldName), text);

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
     * Verifies validation message of the corresponding field
     *
     * @param frameName       - name of the frame
     * @param fieldName       - field name
     * @param expectedMessage - expected message
     */
    public void verifyValidationMessage(String frameName, String fieldName, String expectedMessage) {
        switchToFrame(frameName);
        Assert.assertEquals(getValidationWebElementByLabel(fieldName).getText(), expectedMessage);
    }

    /**
     * Verifies validation message of the corresponding field
     *
     * @param fieldName       - field name
     * @param expectedMessage - expected message
     */
    public void verifyValidationMessage(String fieldName, String expectedMessage) {
        Assert.assertEquals(getValidationWebElementByLabel(fieldName).getText(), expectedMessage);
    }


}
