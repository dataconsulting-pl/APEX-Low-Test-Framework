package pl.dataconsulting.APEX_TAF;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/java/pl/dataconsulting/APEX_TAF/example_component_steps"},
        tags = "@unit",
        glue = {"pl.dataconsulting.APEX_TAF.stepDefinitions" },
        monochrome = false,
        dryRun = false,
        plugin = {"pretty","html:target/cucumber.html", "json:target/cucumber.json"})
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios(){
        return super.scenarios();
    }


}