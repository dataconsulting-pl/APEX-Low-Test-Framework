package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

import java.util.List;

@APEXComponent
public class CheckboxComponent extends BaseComponent {

    // == Checkbox Item action functions ==

    /**
     * Sets checkbox items on the checkbox component.  As result, option given in the list will be set.
     *
     * @param itemName            name (label) of the checkbox component
     * @param optionNamesToSelect list of option to be selected
     */
    public void setCheckboxesByOptionName(String itemName, List<String> optionNamesToSelect) {
        WebElement element = getWebElementByLabel(itemName);

        List<WebElement> options =
                element.findElements(By.xpath("//*[@class='apex-item-option']/input[@type = 'checkbox']"));

        List<String> notMatch = optionNamesToSelect.stream().filter(
                s -> options.stream().noneMatch(name -> name.getAttribute("data-display").trim().equals(s.trim()))).toList();
        List<WebElement> match = options.stream().filter(
                s -> optionNamesToSelect.stream().anyMatch(name -> name.trim().equals(s.getAttribute("data-display").trim()))).toList();

        if (!notMatch.isEmpty()) {
            String log = String.join("; ", notMatch);
            Assert.assertEquals("Not all checkbox options could be found", "All checkbox options could be found",
                    "Checkbox option not found: " + log);
        }
        if (match.isEmpty()) {
            Reporter.log("Could not find any checkbox elements to select. Element list: " + String.join("; ", optionNamesToSelect));
        }

        String checkboxValuesToSelect = match.stream().map(e -> e.getAttribute("value")).reduce("", (a, b) -> a + ":" + b).substring(1);
        setValueJS(element.getAttribute("id"), checkboxValuesToSelect);
    }

    /**
     * Unsets checkbox items on the checkbox component.  As result, option given in the list will be unset.
     *
     * @param itemName           name (label) of the checkbox component
     * @param optionNamesToUnset list of option to be unset
     */
    public void unsetCheckboxesByOptionName(String itemName, List<String> optionNamesToUnset) {
        WebElement element = getWebElementByLabel(itemName);

        // get all the options
        List<WebElement> options =
                element.findElements(By.xpath("//*[@class='apex-item-option']/input[@type = 'checkbox']"));
        // get selected options
        List<String> selectedOptions = getSelectedOptions(element);

        // get list of option that should be set after the action
        List<String> toSelect = selectedOptions.stream().filter(
                s -> optionNamesToUnset.stream().noneMatch(name -> name.trim().equals(s.trim()))).toList();

        setCheckboxesByOptionName(itemName, toSelect);

    }


    // == String Item verification functions ==


    /**
     * Verifies selected checkbox items on the checkbox component.
     *
     * @param itemName            name (label) of the checkbox component
     * @param expectedOptionNames list of option that should be selected
     */
    public void verifyCheckboxesByOptionName(String itemName, List<String> expectedOptionNames) {
        WebElement element = getWebElementByLabel(itemName);

        List<String> selectedOptions = getSelectedOptions(element);
        List<String> notMatch = expectedOptionNames.stream().filter(
                s -> selectedOptions.stream().noneMatch(name -> name.trim().equals(s.trim()))).toList();

        if (!notMatch.isEmpty()) {
            String log = String.join("; ", notMatch);
            Assert.assertEquals("Not all checkbox are set", "All checkboxes are set",
                    "Checkbox option not set: " + log);
        } else {
            Assert.assertEquals("All checkbox options are set", "All checkbox options are set",
                    itemName + " Checkbox options set: " + String.join(";", expectedOptionNames));
        }
    }


}
