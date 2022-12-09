package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.PopupLovComponent;


public class PopupLovSteps {

    @Autowired
    private PopupLovComponent popupLovComponent;

    /**
     * Step select given element in popup lov
     *
     * @param value     - value to be selected
     * @param fieldName - text field name
     */
    @Given("user selected {string} in {string} popup lov element")
    @When("user selects {string} in {string} popup lov element")
    public void user_select_element_in_popup_lov(String value, String fieldName) {
        popupLovComponent.selectElement(fieldName, value);
    }

    /**
     * Step verifies the given value is selected in popup lov component
     *
     * @param expectedValue - expected value to be selected
     * @param fieldName     - popup lov field name
     */
    @Then("verify, that value of popup lov field {string} is {string}")
    @Then("value in pop-up lov filed {string} is {string}")
    public void user_verifies_value_in_text_field(String fieldName, String expectedValue) {
        popupLovComponent.verifyPopupLovValue(expectedValue, fieldName);
    }


}
