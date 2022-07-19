package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.MenuComponent;


public class MenuItemSteps {

    @Autowired
    private MenuComponent menuComponent;

    @Then("user navigates to {string} page")
    public void navigateToMainMenuPage(String page) {
        menuComponent.navigateToMenu(page);
    }



}
