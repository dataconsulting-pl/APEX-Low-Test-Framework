package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.TextComponent;

public class ValidationItemSteps {

    @Autowired
    private TextComponent textComponent;

    /**
     * Step verifies the validation message of the APEX item
     * @param expectedValidationMessage -expected validation message
     * @param fieldName - name of the field
     * @param frameName - name of the iframe (APEX pop-up)
     */
    @Then("validation message {myString} for {myString} field is displayed in {myString} pop-up")
    public void verify_validation_message(String expectedValidationMessage, String fieldName, String frameName) {
        textComponent.verifyValidationMessage(frameName, fieldName, expectedValidationMessage);
    }

    /**
     * Step verifies the validation message of the APEX item
     * @param expectedValidationMessage -expected validation message
     * @param fieldName - name of the field
     */
    @Then("validation message {myString} for {myString} field is displayed")
    public void verify_validation_message(String expectedValidationMessage, String fieldName) {
        textComponent.verifyValidationMessage(fieldName, expectedValidationMessage);
    }


}
