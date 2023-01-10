package pl.dataconsulting.APEX_TAF.stepDefinitions.common;

import com.google.common.base.CharMatcher;
import io.cucumber.java.ParameterType;
import org.openqa.selenium.Keys;
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
        if (arg.startsWith("$")) {
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

    @ParameterType("Arrow – Down" +
            "|Arrow – Up" +
            "|Arrow – Left" +
            "|Arrow - Right" +
            "|Backspace" +
            "|Ctrl" +
            "|Alt" +
            "|Delete" +
            "|Enter" +
            "|Shift" +
            "|Space" +
            "|Tab" +
            "|Esc" +
            "|Home" +
            "|Insert" +
            "|PgUp" +
            "|PgDn" +
            "|Function F1" +
            "|Function F2" +
            "|Function F3" +
            "|Function F4" +
            "|Function F5" +
            "|Function F6" +
            "|Function F7" +
            "|Function F8" +
            "|Function F9" +
            "|Function F10" +
            "|Function F11" +
            "|Function F12")
    public Keys key(String key) {
        switch (key) {
            case "Arrow – Down":
                return Keys.ARROW_DOWN;
            case "Arrow – Up":
                return Keys.ARROW_UP;
            case "Arrow – Left":
                return Keys.ARROW_LEFT;
            case "Arrow - Right":
                return Keys.ARROW_RIGHT;
            case "Backspace":
                return Keys.BACK_SPACE;
            case "Ctrl":
                return Keys.CONTROL;
            case "Alt":
                return Keys.ALT;
            case "DELETE":
                return Keys.DELETE;
            case "Enter":
                return Keys.ENTER;
            case "Shift":
                return Keys.SHIFT;
            case "Space":
                return Keys.SPACE;
            case "Tab":
                return Keys.TAB;
            case "Esc":
                return Keys.ESCAPE;
            case "Home":
                return Keys.HOME;
            case "Insert":
                return Keys.INSERT;
            case "PgUp":
                return Keys.PAGE_UP;
            case "PgDn":
                return Keys.PAGE_DOWN;
            case "Function F1":
                return Keys.F1;
            case "Function F2":
                return Keys.F2;
            case "Function F3":
                return Keys.F3;
            case "Function F4":
                return Keys.F4;
            case "Function F5":
                return Keys.F5;
            case "Function F6":
                return Keys.F6;
            case "Function F7":
                return Keys.F7;
            case "Function F8":
                return Keys.F8;
            case "Function F9":
                return Keys.F9;
            case "Function F10":
                return Keys.F10;
            case "Function F11":
                return Keys.F11;
            case "Function F12":
                return Keys.F12;

            default:
                throw new IllegalArgumentException("Invalid key name: " + key);
        }

    }

}
