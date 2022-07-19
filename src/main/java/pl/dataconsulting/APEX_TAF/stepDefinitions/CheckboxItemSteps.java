package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.CheckboxComponent;

import java.util.Arrays;
import java.util.List;

public class CheckboxItemSteps {

    @Autowired
    private CheckboxComponent checkboxComponent;

    @ParameterType("(?:[^\",]*)(?:,\\s?[^,]*)*")
    public List<String> listOfStrings(String arg) {
        return Arrays.asList(arg.split(",\\s?"));
    }

    /**
     * Set checkboxes for checkbox item. Only given checkboxes will be set and the rest present checkboxes will be unset
     *
     * @param options  - list of checkboxes separated by comma to be selected, for example: item1, item2, item3
     * @param itemName - the name of a checkbox item
     */
    @When("user sets checkbox(es) {listOfStrings} for {string} checkbox item")
    public void user_sets_checkboxes(List<String> options, String itemName) {
        checkboxComponent.setCheckboxesByOptionName(itemName, options);
    }

    @Then("checkbox(es) {listOfStrings} in {string} checkbox are/is selected")
    public void verify_selected_checkboxes(List<String> options, String itemName) {
        checkboxComponent.verifyCheckboxesByOptionName(itemName, options);
    }

    @When("user unsets checkbox(es) {listOfStrings} for {string} checkbox item")
    public void user_unsets_checkboxes(List<String> options, String itemName) {
        checkboxComponent.unsetCheckboxesByOptionName(itemName, options);
    }
}
