package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;


public class LoginPageSteps {

    @Autowired
    private BaseComponent baseComponent;

    /**
     * Step accesses the given url
     * @param url - url to access
     */
    @Given("the user access the {string} url")
    public void user_access_url(String url) {
        baseComponent.navigateToUrl(url);
    }

    /**
     * Step switches content to iframe for example APEX pop-up
     * @param frameName - iframe name
     */
    @Given("frame/pop-up {string} is visible")
    @Then("frame/pop-up {string} has been opened")
    public void switch_to_frame(String frameName){
        baseComponent.switchToFrame(frameName);
    }

    /**
     * Step switches content back to main frame
     */
    @Given("main frame/page is visible")
    @Then("a main frame/page is again active")
    public void switch_to_main_frame(){
        baseComponent.switchToMainFrame();
    }


}
