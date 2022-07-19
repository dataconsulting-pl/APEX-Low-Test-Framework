package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.FormComponent;


public class TextFieldSteps {

    @Autowired
    private FormComponent formComponent;

    /**
     * Enter text into the text field
     *
     * @param value     - text to enter
     * @param fieldName - text field name
     * @param frameName - frame/pop-up name
     */
    @Given("user entered {string} in {string} field on {string} pop-up")
    @When("user enters {string} in {string} field on {string} pop-up")
    public void user_enters_text_into_field(String value, String fieldName, String frameName) {
        formComponent.enterStringIntoTextItem(frameName, fieldName, value);
    }

    /**
     * Enter text into the text field
     *
     * @param value     - text to enter
     * @param fieldName - text field name
     */
    @Given("user entered {string} in {string} field")
    @When("user enters {string} in {string} field")
    public void user_enters_text_into_field(String value, String fieldName) {
        formComponent.enterStringIntoTextItem(fieldName, value);
    }

    /**
     * Verify text in text field item
     * @param fieldName
     * @param expectedValue
     */
    @Then("verify, that text item {string} value is {string}")
    @Then("text item {string} value is {string}")
    public void user_verifies_value_in_text_field(String fieldName, String expectedValue) {
        formComponent.verifyTextItemValue(fieldName,expectedValue);
    }


}
