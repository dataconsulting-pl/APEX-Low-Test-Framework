package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class FormComponent extends BaseComponent {

    // == String Item action functions ==
    public void enterStringIntoTextItem(String frameTitle, String fieldName, String activityName) {
        switchToFrame(frameTitle);
        sendKeys(getWebElementByLabel(fieldName), activityName);
    }

    public void enterStringIntoTextItem(String fieldName, String text) {
        sendKeys(getWebElementByLabel(fieldName), text);

    }

    // == String Item verification functions ==

    public void verifyTextItemValue(String fieldName, String expectedValue){

    }


    public void switchItemOn(String switchName, String frameTitle) {
        switchToFrame(frameTitle);
        switchItem(getWebElementByLabel(switchName), "Yes");
    }


    public void switchItemOn(String switchName) {
        switchItem(getWebElementByLabel(switchName), "Yes");
    }

    public void switchItemOff(String switchName, String frameTitle) {
        switchToFrame(frameTitle);
        switchItem(getWebElementByLabel(switchName), "No");
    }


    public void switchItemOff(String switchName) {
        switchItem(getWebElementByLabel(switchName), "No");
    }


    public void selectCombo(String frameTitle, String option, String fieldName) {
        switchToFrame(frameTitle);
        selectComboElement(getWebElementByLabel(fieldName), option);
    }

    public void selectCombo(String option, String fieldName) {
        selectComboElement(getWebElementByLabel(fieldName), option);
    }

    public void enterData(String fieldName, String text) {
        WebElement webElement = getWebElementByLabel(fieldName);
        String attribute = webElement.getAttribute("class");
        if (attribute.contains("apex-item-text")) {

        } else if (attribute.contains("apex-item-select")) {

        } else {
            //TODO send not supported element error
        }

    }



    public void verifyValidationMessage(String frameName, String fieldName, String expectedMessage) {
        switchToFrame(frameName);
        Assert.assertEquals(getValidationWebElementByLabel(fieldName).getText(), expectedMessage);
    }

    private void selectComboElement(WebElement element, String comboOption) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        final Select selectBox = new Select(element);
        selectBox.selectByVisibleText(comboOption);

    }

    private void switchItem(WebElement switchItemElement, String switchTo) {
        String xpath = String.format(".//label[@class='a-Button'][text()='%s']", switchTo);
        wait.until(ExpectedConditions.elementToBeClickable(
                switchItemElement.findElement(By.xpath(String.format(xpath, switchTo)))))
        .click();
        waitForApex();
    }


}
