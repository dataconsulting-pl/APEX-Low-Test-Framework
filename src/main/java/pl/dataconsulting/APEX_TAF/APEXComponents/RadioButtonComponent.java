package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

import java.util.List;

@APEXComponent
public class RadioButtonComponent extends BaseComponent {

    // == RadioButton Item action functions ==

    /**
     * Sets radioButton items on the radioButton component.  As result, option given in the list will be set.
     *
     * @param itemName       name (label) of the radioButton component
     * @param optionToSelect name of the option to be selected
     */
    public void setRadioButtonByOptionName(String itemName, String optionToSelect) {
        WebElement element = getWebElementByLabel(itemName);

        WebElement option = element.findElement(By.xpath(
                String.format("//*[@class='apex-item-option']/input[@type = 'radio' and @data-display='%s']", optionToSelect)));

        if (option == null) {
            Assert.assertEquals("A radioButton option could not be found", "A radioButton option could be found",
                    "RadioButton option not found: " + optionToSelect);
        }

        assert option != null;
        String radioButtonValuesToSelect = option.getAttribute("value");
        setValueJS(element.getAttribute("id"), radioButtonValuesToSelect);
    }

    // == String Item verification functions ==


    /**
     * Verifies selected radioButton items on the radioButton component.
     *
     * @param itemName            name (label) of the radioButton component
     * @param expectedOptionName name of the option that should be selected
     */
    public void verifyRadioButtonsByOptionName(String itemName, String expectedOptionName) {
        WebElement element = getWebElementByLabel(itemName);

        String selectedOptions = getSelectedOption(element);

        if (selectedOptions.equals(expectedOptionName)) {
            Assert.assertEquals("Expected radioButton option set", "Expected radioButton option set",
                    itemName + " Expected radioButton option set: " +expectedOptionName);
        } else {
            Assert.assertEquals("Expected radioButton option not set", "Expected radioButton option set",
                    itemName + " Expected radioButton option not set: " +expectedOptionName);
        }
    }


}
