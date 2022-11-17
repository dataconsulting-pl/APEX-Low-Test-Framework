package pl.dataconsulting.APEX_TAF.APEXComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import pl.dataconsulting.APEX_TAF.framework.annotation.APEXComponent;

import java.util.List;

@APEXComponent
public class ShuttleComponent extends BaseComponent {

    // == Radio Item action functions ==

    /**
     * Sets shuttle options in the shuttle component.  As result, option given in the list will be set.
     *
     * @param radioItemName       name (label) of the radiobox component
     * @param optionNamesToSelect list of option to be selected
     */
    public void moveShuttleOptionName(String radioItemName, List<String> optionNamesToSelect) {
        WebElement element = getWebElementByLabel(radioItemName);
        String alreadySelectedOptions = "";

        List<WebElement> optionToBeSelected =
                element.findElements(By.xpath("//*[contains(@class, 'apex-item-select') and contains(@class, 'shuttle')]/option[not(@disabled)]"));
        List<String> notMatch = optionNamesToSelect.stream().filter(
                s -> optionToBeSelected.stream().noneMatch(name -> name.getText().trim().equals(s.trim()))).toList();
        // get already selected options
        List<String> selectedOptions = getSelectedOptions(element);
        if (!selectedOptions.isEmpty())
            alreadySelectedOptions = ":" + selectedOptions.stream().reduce("", (a, b) -> a + ":" + b).substring(1);

        List<WebElement> match = optionToBeSelected.stream()
                .filter(s -> optionNamesToSelect.stream().anyMatch(name -> name.trim().equals(s.getText().trim())))
                .toList();

        if (!notMatch.isEmpty()) {
            String log = String.join("; ", notMatch);
            Assert.assertEquals("Not all Shuttle options could be found", "All Shuttle options could be found",
                    "Checkbox option not found: " + log);
        }
        if (match.isEmpty()) {
            Reporter.log("Could not find any Shuttle elements to select. Element list: " + String.join("; ", optionNamesToSelect));
            setValueJS(element.getAttribute("id"), "");
        } else {
            String radioValuesToSelect = match.stream().map(e -> e.getAttribute("value")).reduce("", (a, b) -> a + ":" + b).substring(1);
            String optionsToSelect = radioValuesToSelect + alreadySelectedOptions;
            setValueJS(element.getAttribute("id"), optionsToSelect);
        }

    }

    /**
     * Move all shuttle options in the shuttle component.  As result, option given in the list will be set.
     *
     * @param radioItemName name (label) of the radiobox component
     */
    public void moveAllShuttleOptions(String radioItemName) {
        WebElement element = getWebElementByLabel(radioItemName);

        List<WebElement> optionToBeSelected =
                element.findElements(By.xpath("//*[contains(@class, 'apex-item-select') and contains(@class, 'shuttle')]/option[not(@disabled)]"));

        String radioValuesToSelect = optionToBeSelected.stream().map(e -> e.getAttribute("value")).reduce("", (a, b) -> a + ":" + b).substring(1);
        setValueJS(element.getAttribute("id"), radioValuesToSelect);
    }

    /**
     * Remove selections from shuttle component.  As result, option given in the list will be removed.
     *
     * @param itemName            name (label) of the radiobox component
     * @param optionNamesToRemove list of option to be removed
     */
    public void removeShuttleOptionName(String itemName, List<String> optionNamesToRemove) {
        WebElement element = getWebElementByLabel(itemName);

        // get all the options
        List<WebElement> options =
                element.findElements(By.xpath("//*[contains(@class, 'apex-item-select') and contains(@class, 'shuttle')]/option[not(@disabled)]"));
        // get selected options
        List<String> selectedOptions = getSelectedOptionsDisplayName(element);

        // get list of option that should be set after the action
        List<String> toSelect = selectedOptions.stream().filter(
                s -> optionNamesToRemove.stream().noneMatch(name -> name.trim().equals(s.trim()))).toList();

        moveShuttleOptionName(itemName, toSelect);

    }

    /**
     * Move all shuttle options in the shuttle component.  As result, option given in the list will be set.
     *
     * @param radioItemName name (label) of the radiobox component
     */
    public void removeAllShuttleOptions(String radioItemName) {
        WebElement element = getWebElementByLabel(radioItemName);
        setValueJS(element.getAttribute("id"), "");
    }


    // == String Item verification functions ==


    /**
     * Verifies chooses shuttle items in the shuttle component.
     *
     * @param itemName            name (label) of the shuttle component
     * @param expectedOptionNames list of option that should be chosen
     */
    public void verifyShuttleByOptionName(String itemName, List<String> expectedOptionNames) {
        WebElement element = getWebElementByLabel(itemName);

        List<String> selectedOptions = getSelectedOptionsDisplayName(element);
        List<String> notMatch = expectedOptionNames.stream().filter(
                s -> selectedOptions.stream().noneMatch(name -> name.trim().equals(s.trim()))).toList();

        if (!notMatch.isEmpty()) {
            String log = String.join("; ", notMatch);
            Assert.assertEquals("Not all shuttle options are chosen on shuttle item ", "All shuttle items are set",
                    itemName + " Shuttle options not set : " + log);
        } else {
            Assert.assertEquals("All shuttle options are chosen on shuttle item", "All shuttle options are chosen on shuttle item",
                    itemName + " Shuttle options set : " + String.join(";", expectedOptionNames));
        }
    }

    /**
     * Verifies that no elements are chooses sin in the shuttle component.
     *
     * @param itemName name (label) of the shuttle component
     */
    public void verifyNoShuttleOptionChosen(String itemName) {
        WebElement element = getWebElementByLabel(itemName);

        List<String> selectedOptions = getSelectedOptionsDisplayName(element);

        if (!selectedOptions.isEmpty()) {
            String log = String.join("; ", selectedOptions);
            Assert.assertEquals("Some shuttle options are chosen in shuttle item ", "No shuttle options are chosen in shuttle item",
                    itemName + " Shuttle options chosen : " + log);
        } else {
            Assert.assertEquals("No shuttle options are chosen in shuttle item", "No shuttle options are chosen in shuttle item",
                    "No option chosen on shuttle item: " + itemName);
        }
    }

    /**
     * Moves all values in shuttle item
     *
     * @param itemName name (label) of the shuttle component
     */
    public void moveAllValues(String itemName) {
        WebElement element = getWebElementByLabel(itemName);


    }


}
