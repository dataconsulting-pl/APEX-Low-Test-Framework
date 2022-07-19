package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.SelectItemComponent;


public class SelectItemSteps {


    @Autowired
    private SelectItemComponent selectItemComponent;


    /**
     * Select an option on the select item
     * @param value - option to be selected
     * @param fieldName - name of the filed
     * @param frameName - frame/pop-up name
     */
    @Given("user selected {string} in {string} field on {string} pop-up")
    @When("user selects {string} in {string} field on {string} pop-up")
    public void user_set_value_in_select_item(String value, String fieldName, String frameName) {
        selectItemComponent.selectItem(frameName, value, fieldName);
    }

    /**
     * Select an option on the select item
     * @param value - option to be selected
     * @param fieldName - name of the filed
     */
    @Given("user selected {string} in {string} field")
    @When("user selects {string} in {string} field")
    public void user_set_value_in_select_item(String value, String fieldName) {
        selectItemComponent.selectItem(value, fieldName);
    }

    /**
     * Verify a value in the select item
     * @param expectedValue - option to be selected
     * @param fieldName - name of the filed
     */
    @Then("value {string} is selected in {string} field")
    public void verify_select_item(String expectedValue, String fieldName) {
        selectItemComponent.verifySelectItemValue(expectedValue, fieldName);
    }


}
