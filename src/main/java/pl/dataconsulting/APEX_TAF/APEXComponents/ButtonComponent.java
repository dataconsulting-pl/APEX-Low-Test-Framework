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
     * @param className  - class name that button should contain. If empty, no class is taken into account
     */
    public void pressPopUpButton(String frameName, String buttonName, String className) {
        switchToFrame(frameName);
        pressButton(buttonName, className);
    }

    /**
     * Presses the button
     *
     * @param buttonName - button name
     * @param className  - class name that button should contain. If empty, no class is taken into account
     */
    public void pressButton(String buttonName, String className) {
        WebElement elementSpanButton = getSpanButton(buttonName, className);
        if (elementSpanButton != null) {
            wait.until(ExpectedConditions.elementToBeClickable(elementSpanButton)).click();
        } else {
            WebElement element = getButton(buttonName, className);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }
        waitForApex();
    }

    private WebElement getSpanButton(String buttonName, String className) {

        String xpath = String.format("//button/span[text()='%s' and contains(@class,'%s')]/..", buttonName, className);

        try {
            return driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private WebElement getButton(String buttonName, String className) {

        String xpath = String.format("//*[@type='button' and text()='%s' and contains(@class,'%s')]", buttonName, className);
        try {
            return driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return null;
        }

    }
}