package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.FormComponent;

public class ValidationItemSteps {

    @Autowired
    private FormComponent formComponent;

    @Then("validation message {string} for {string} field is displayed in {string} pop-up")
    public void verify_validation_message(String validationMessage, String fieldName, String frameName) {
        formComponent.verifyValidationMessage(frameName, fieldName, validationMessage);
    }


}
