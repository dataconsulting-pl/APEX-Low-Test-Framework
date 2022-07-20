package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class SwitchItemComponent extends BaseComponent {


    /**
     * Switches switch item to on
     * @param switchItemName - name of the switch item
     * @param frameTitle - name of the frame
     */
    public void switchItemOn(String switchItemName, String frameTitle) {
        switchToFrame(frameTitle);
        switchItem(getWebElementByLabel(switchItemName), true);
    }


    /**
     * Switches switch item to on
     * @param switchItemName - name of the switch item
     */
    public void switchItemOn(String switchItemName) {
        switchItem(getWebElementByLabel(switchItemName), true);
    }

    /**
     * Switches switch item to off
     * @param switchItemName - name of the switch item
     * @param frameTitle - name of the frame
     */
    public void switchItemOff(String switchItemName, String frameTitle) {
        switchToFrame(frameTitle);
        switchItem(getWebElementByLabel(switchItemName), false);
    }

    /**
     * Switches switch item to off
     * @param switchItemName - name of the switch item
     */
    public void switchItemOff(String switchItemName) {
        switchItem(getWebElementByLabel(switchItemName), false);
    }

    /**
     * Verifies switch item
     * @param switchItemName - name of the switch item
     * @param expected - expected value. True - element is switched to on
     */
    public void verifySwitchItem(String switchItemName, boolean expected) {

        String message = "Verify Switch item: " + switchItemName;
        if (expected) {
            Assert.assertEquals(getValueJS(getWebElementByLabel(switchItemName)),
                    "Y", message);
        } else {
            Assert.assertEquals(getValueJS(getWebElementByLabel(switchItemName)),
                    "N", message);
        }
    }

    private void switchItem(WebElement switchItemElement, boolean switchTo) {
        if (switchTo)
            setValueJS(switchItemElement,"Y");
        else
            setValueJS(switchItemElement,"N");

//        String xpath = String.format(".//label[@class='a-Button'][text()='%s']", switchTo);
//        wait.until(ExpectedConditions.elementToBeClickable(
//                        switchItemElement.findElement(By.xpath(String.format(xpath, switchTo)))))
//                .click();
//        waitForApex();
    }


}
