package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class ButtonComponent extends BaseComponent {

    /**
     * Presses the pop-up button
     *
     * @param frameName  - name of the frame
     * @param buttonName - button name
     */
    public void pressPopUpButton(String frameName, String buttonName) {
        switchToFrame(frameName);
        String xpath = String.format("//button/span[text()='%s']", buttonName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        waitForApex();
    }

    /**
     * Presses the button
     *
     * @param buttonName - button name
     */
    public void pressButton(String buttonName) {
        String xpath = String.format("//button/span[text()='%s']", buttonName);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
        waitForApex();
    }
}
