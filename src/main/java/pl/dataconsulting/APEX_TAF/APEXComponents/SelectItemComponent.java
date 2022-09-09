package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class SelectItemComponent extends BaseComponent {


    /**
     * Selects value in select item
     *
     * @param frameTitle - name of the frame where select item is located
     * @param value      - value to be selected
     * @param fieldName  - name of the field
     */
    public void selectItem(String frameTitle, String value, String fieldName) {
        switchToFrame(frameTitle);
        selectItemsValue(getWebElementByLabel(fieldName), value);
    }

    /**
     * Selects value in select item
     *
     * @param value     - value to be selected
     * @param fieldName - name of the
     */
    public void selectItem(String value, String fieldName) {
        selectItemsValue(getWebElementByLabel(fieldName), value);
    }

    /**
     * Verifies value in select item
     *
     * @param expectedValue - value to be verified
     * @param fieldName     - name of the field
     */
    public void verifySelectItemValue(String expectedValue, String fieldName) {
        WebElement selectItem = getWebElementByLabel(fieldName);
        final Select selectBox = new Select(selectItem);
        String selectedOption = selectBox.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption.trim(), expectedValue.trim(), "Compare selected value in select item filed: " + fieldName);
    }

    /**
     * Select value in select List
     *
     * @param element - WebElement of the select item item
     * @param value   - value to be selected
     */
    private void selectItemsValue(WebElement element, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        final Select selectBox = new Select(element);
        selectBox.selectByVisibleText(value);
    }


}
