package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

@APEXComponent
public class SelectItemComponent extends BaseComponent {


    /**
     * Select value in select item
     * @param frameTitle - name of the frame where select item is located
     * @param value - value to be selected
     * @param fieldName - name of the
     */
    public void selectItem(String frameTitle, String value, String fieldName) {
        switchToFrame(frameTitle);
        selectItemsValue(getWebElementByLabel(fieldName), value);
    }

    /**
     *
     * @param option
     * @param fieldName
     */
    public void selectItem(String option, String fieldName) {
        selectItemsValue(getWebElementByLabel(fieldName), option);
    }

    /**
     *
     * @param expectedValue
     * @param fieldName
     */
    public void verifySelectItemValue(String expectedValue, String fieldName) {
        WebElement selectItem = getWebElementByLabel(fieldName);
        final Select selectBox = new Select(selectItem);
        String selectedOption = selectBox.getFirstSelectedOption().getText();
        Assert.assertEquals(selectedOption.trim(),expectedValue.trim(),"Compare selected value in select item filed: " +fieldName );
    }

    private void selectItemsValue(WebElement element, String comboOption) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        final Select selectBox = new Select(element);
        selectBox.selectByVisibleText(comboOption);
    }


}
