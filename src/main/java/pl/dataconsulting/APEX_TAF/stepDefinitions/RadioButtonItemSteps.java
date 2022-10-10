package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.RadioButtonComponent;

import java.util.Arrays;
import java.util.List;

public class RadioButtonItemSteps {

    @Autowired
    private RadioButtonComponent radioButtonComponent;


    /**
     * Step sets radioButton for radioButton item by its name.
     *
     * @param options  - option to be selected
     * @param itemName - the name of a radioButton item
     */
    @When("user sets radioButton's option {string} for {string} radioButton item")
    public void user_sets_radioButton(String options, String itemName) {
        radioButtonComponent.setRadioButtonByOptionName(itemName, options);
    }

     /**
     * Step verifies, if given radioButton is selected
     *
     * @param option   - expected selected option
     * @param itemName - the name of the radioButton item
     */
    @Then("radioButton {string} in {string} radioButton is selected")
    public void verify_selected_radioButton(String option, String itemName) {
        radioButtonComponent.verifyRadioButtonsByOptionName(itemName, option);
    }


}
