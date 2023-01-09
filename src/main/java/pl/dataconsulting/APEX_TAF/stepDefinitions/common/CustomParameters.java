package pl.dataconsulting.APEX_TAF.stepDefinitions.common;

import com.google.common.base.CharMatcher;
import io.cucumber.java.ParameterType;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dataconsulting.APEX_TAF.APEXComponents.BaseComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class CustomParameters {

    @Autowired
    BaseComponent baseComponent;

    @ParameterType("['|@]([^'\\\\]*(\\\\.[^'\\\\]*)*)'?")
    public String myString(String arg) {
        if (arg.startsWith("$")){
            arg = arg.substring(1);
            return baseComponent.getTestData(arg);
        } else {
            return arg;
        }

    }

    @ParameterType("(?:[^,]*)(?:,\\s?[^,]*)*")
    public List<String> listOfStrings(String arg) {
        String argTrimmed = CharMatcher.is('\'').trimFrom(arg);

        return Arrays.asList(argTrimmed.split(",\\s?"));
    }

    @ParameterType("(?:[^,]*)(?:->\\s?[^,]*)*")
    public List<String> listOfMenuElements(String arg) {

        String argTrimmed = CharMatcher.is('\'').trimFrom(arg);

        return Arrays.asList(argTrimmed.split("->\\s?"));
    }

    @ParameterType("EQ|Equals" +
            "|NEQ|Not Equals" +
            "|LT|Less Than" +
            "|LTE|Less than or equal to" +
            "|GT|Greater Than" +
            "|LIKE|SQL Like operator" +
            "|NLIKE|Not Like" +
            "|C|Contains" +
            "|NC|Not Contains")
    public String operator(String operator) {
        if (operator.equals("Equals"))
            return "EQ";
        if (operator.equals("Not Equals"))
            return "NEQ";
        if (operator.equals("Less Than"))
            return "LT";
        if (operator.equals("Less than or equal to"))
            return "LTE";
        if (operator.equals("Greater Than"))
            return "GT";
        if (operator.equals("SQL Like operator"))
            return "LIKE";
        if (operator.equals("Not Like"))
            return "NLIKE";
        if (operator.equals("Contains"))
            return "C";
        if (operator.equals("Not Contains"))
            return "NC";
        else
            return operator;
    }

}
