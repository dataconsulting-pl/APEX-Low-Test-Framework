package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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
        pressButton(buttonName);
    }

    /**
     * Presses the button
     *
     * @param buttonName - button name
     */
    public void pressButton(String buttonName) {
        WebElement element = getSpanButton(buttonName);
        if (element != null) {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } else {
            element = getButton(buttonName);
            if (element != null) {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            }
        }
        waitForApex();
    }

    private WebElement getSpanButton(String buttonName) {

        String xpath = String.format("//button/span[text()='%s']/..", buttonName);
        try {
            return driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private WebElement getButton(String buttonName) {

        String xpath = String.format("//*[@type='button' and text()='%s']", buttonName);
        try {
            return driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return null;
        }

    }
}