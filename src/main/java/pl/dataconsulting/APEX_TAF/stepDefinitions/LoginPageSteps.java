package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;


public class LoginPageSteps {

    @Autowired
    private BaseComponent baseComponent;


    @Given("the user access the {string} url")
    public void user_access_url(String url) {
        baseComponent.navigateToUrl(url);
    }

    @Given("frame {string} is visible")
    @Then("frame {string} has been opened")
    public void switch_to_frame(String frameName){
        baseComponent.switchToFrame(frameName);
    }

    @Given("main frame is visible")
    @Then("a main frame is again active")
    public void switch_to_main_frame(){
        baseComponent.switchToMainFrame();
    }


}
