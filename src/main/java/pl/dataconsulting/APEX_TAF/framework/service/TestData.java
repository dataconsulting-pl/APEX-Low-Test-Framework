package pl.dataconsulting.APEX_TAF.framework.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Lazy
@Service
public class TestData {

    Map<String, String> testdata;

    public TestData() {
        testdata = new HashMap<>();
    }

    public void savedData(String key, String value) {

        testdata.put(key, value);
    }

    public String getData(String key) {
        return testdata.get(key);
    }
}
