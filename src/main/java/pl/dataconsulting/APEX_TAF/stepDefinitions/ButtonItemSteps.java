package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.ButtonComponent;

public class ButtonItemSteps {

    @Autowired
    private ButtonComponent buttonComponent;

    /**
     * Step presses the button on pop-up by its name
     * @param buttonName - name of the button
     * @param frameName - iframe name (pop-up name)
     */
    @When("user presses the {string} button on {string} pop-up")
    public void user_presses_the_button_on_popup(String buttonName, String frameName) {
        buttonComponent.pressPopUpButton(frameName, buttonName);
    }

    /**
     * Step presses the button by its name
     * @param buttonName - name of the button
     */
    @Given("user clicked on the {string} button")
    @When("user presses the {string} button")
    public void user_presses_the_button(String buttonName) {
        buttonComponent.pressButton(buttonName);
    }


}
