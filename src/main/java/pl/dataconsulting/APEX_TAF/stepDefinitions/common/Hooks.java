package pl.dataconsulting.APEX_TAF.stepDefinitions.common;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import pl.dataconsulting.APEX_TAF.framework.service.ScreenShotService;

import java.sql.Timestamp;

public class Hooks {

    @Lazy
    @Autowired
    private ScreenShotService screenShotService;

    @Lazy
    @Autowired
    private ApplicationContext applicationContext;

    @AfterStep
    public void captureExceptionImage(Scenario scenario) {
        if (scenario.isFailed()) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String timeMilliseconds = Long.toString(timestamp.getTime());
            scenario.attach(this.screenShotService.getScreenshot(), "image/png", timeMilliseconds);
        }
    }

    @After
    public void tearDown() {
        this.applicationContext.getBean(WebDriver.class).quit();
    }
}
