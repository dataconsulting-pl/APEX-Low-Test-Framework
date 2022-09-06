package pl.dataconsulting.APEX_TAF.stepDefinitions.common;

import io.cucumber.java.ParameterType;

import java.util.Arrays;
import java.util.List;

public class CustomParameters {

    @ParameterType("(?:[^,]*)(?:,\\s?[^,]*)*")
    public List<String> listOfStrings(String arg) {
        return Arrays.asList(arg.split(",\\s?"));
    }
}
