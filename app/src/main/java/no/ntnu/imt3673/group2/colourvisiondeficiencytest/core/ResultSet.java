package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSet <T extends Result>{
    private List<T> results = new ArrayList<>();
    private String testId;
    private Date time;
    private Map<String, Object> extraData = new HashMap<>();

    public ResultSet() {
    }

    public ResultSet(List<T> results, String testId, Date time, Map<String, Object> extraData) {
        this.results = results;
        this.testId = testId;
        this.time = time;
        this.extraData = extraData;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    /**
     * adds result to set
     * @param result
     */
    public void addResult (T result) {
        this.results.add(result);
    }

    /**
     * adds or replaces if key exists
     * @param key
     * @param value
     */
    public void addExtraData (String key, Object value) {
        this.extraData.put(key,value);
    }
}
