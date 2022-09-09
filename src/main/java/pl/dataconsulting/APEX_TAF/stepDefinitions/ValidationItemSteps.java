package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.FormComponent;

public class ValidationItemSteps {

    @Autowired
    private FormComponent formComponent;

    /**
     * Step verifies the validation message of the APEX item
     * @param expectedValidationMessage -expected validation message
     * @param fieldName - name of the field
     * @param frameName - name of the iframe (APEX pop-up)
     */
    @Then("validation message {string} for {string} field is displayed in {string} pop-up")
    public void verify_validation_message(String expectedValidationMessage, String fieldName, String frameName) {
        formComponent.verifyValidationMessage(frameName, fieldName, expectedValidationMessage);
    }

    /**
     * Step verifies the validation message of the APEX item
     * @param expectedValidationMessage -expected validation message
     * @param fieldName - name of the field
     */
    @Then("validation message {string} for {string} field is displayed")
    public void verify_validation_message(String expectedValidationMessage, String fieldName) {
        formComponent.verifyValidationMessage(fieldName, expectedValidationMessage);
    }


}
