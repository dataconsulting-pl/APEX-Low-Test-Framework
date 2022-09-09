package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.MenuComponent;

import java.util.Arrays;
import java.util.List;


public class MenuItemSteps {

    @Autowired
    private MenuComponent menuComponent;

    /**
     * Step navigates to the menu option.
     *
     * @param options - menu option to be selected In case navigation should be done to some sub-menu please use -> as delimiter.
     *                For exampele Administration->Access Control->Settings
     */
    @When("user navigates to {listOfMenuElements} page")
    public void user_navigate_to_menu(List<String> options) {
        menuComponent.navigateToMenu(options);
    }

    /**
     * Step verifies, if menu option is selected.
     *
     * @param options - menu option to be verified.  In case verification should be performed on some sub-menu please use -> as delimiter.
     *                For exampele Administration->Access Control->Settings
     */
    @Then("verify, that menu option {listOfMenuElements} is selected")
    public void verify_menu_element_selected(List<String> options) {
        menuComponent.verifyMenuOption(options);
    }


}
