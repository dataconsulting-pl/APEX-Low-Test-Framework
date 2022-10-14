package pl.dataconsulting.APEX_TAF.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


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

    @Given("the user access the url stored in file: {string}")
    public void user_access_url_from_file(String filePath ) {
        try {
            List<String> fileLines = Files.readAllLines(Path.of(filePath));
            baseComponent.navigateToUrl(fileLines.get(0));
        } catch (IOException e) {
            Assert.fail("File could not be found");
        }


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
