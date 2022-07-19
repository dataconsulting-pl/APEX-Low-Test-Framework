package pl.dataconsulting.APEX_TAF.framework.util;

import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class Asserts {
    public void assertEqualRegexp(String action, String fieldName, String expected, String was) {
        if (!was.matches(expected)) {
            setFail(action, fieldName, expected, was);
        } else {
            System.out.println("OK " + fieldName);
        }
    }

    private void setFail(String action, String fieldName, String expected, String was) {
        String messageTemplate = "Value for the column \"%s\" does not match. Expected: '%s' but was: '%s'";
        Assert.fail(action + " Expected result: " + String.format(messageTemplate, fieldName, expected, was));
    }

}
