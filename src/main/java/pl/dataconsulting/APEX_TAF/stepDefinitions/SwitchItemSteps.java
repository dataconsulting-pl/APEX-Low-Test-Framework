package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.FormComponent;

public class SwitchItemSteps {

    @Autowired
    private FormComponent formComponent;

    /**
     * Switch on the switch item
     * @param switchName - name of the switch item element
     * @param frameTitle - frame/pop-up name
     */
    @Given("user switched on the {string} switch item on {string} pop-up")
    @When("user switch on the {string} switch item on {string} pop-up")
    public void user_sets_on_switch(String switchName, String frameTitle) {
        formComponent.switchItemOn(switchName,frameTitle);
    }

    /**
     * Switch on the switch item
     * @param switchName - name of the switch item element
     */
    @Given("user switched on the {string} switch item")
    @When("user switch on the {string} switch item")
    public void user_sets_on_switch(String switchName) {
        formComponent.switchItemOn(switchName);
    }

    /**
     * Switch off the switch item
     * @param switchName - name of the switch item element
     * @param frameTitle - frame/pop-up name
     */
    @Given("user switched off the {string} switch item on {string} pop-up")
    @When("user switch off the {string} switch item on {string} pop-up")
    public void user_sets_off_switch(String switchName, String frameTitle) {
        formComponent.switchItemOff(switchName,frameTitle);
    }

    /**
     * Switch off the switch item
     * @param switchName - name of the switch item element
     */
    @Given("user switched off the {string} switch item")
    @When("user switch off the {string} switch item")
    public void user_sets_ff_switch(String switchName) {
        formComponent.switchItemOff(switchName);
    }


}
