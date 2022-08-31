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
     * Set radioButton for radioButton item.
     *
     * @param options  - option to be selected
     * @param itemName - the name of a radioButton item
     */
    @When("user sets radioButton {string} for {string} radioButton item")
    public void user_sets_radioButton(String options, String itemName) {
        radioButtonComponent.setRadioButtonByOptionName(itemName, options);
    }

    @Then("radioButton {string} in {string} radioButton is selected")
    public void verify_selected_radioButton(String options, String itemName) {
        radioButtonComponent.verifyRadioButtonsByOptionName(itemName, options);
    }

}
