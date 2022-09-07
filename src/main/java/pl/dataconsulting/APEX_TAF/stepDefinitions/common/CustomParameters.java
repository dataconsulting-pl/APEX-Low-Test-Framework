package pl.dataconsulting.APEX_TAF.stepDefinitions.common;

import com.google.common.base.CharMatcher;
import io.cucumber.java.ParameterType;

import java.util.Arrays;
import java.util.List;

public class CustomParameters {

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
}
