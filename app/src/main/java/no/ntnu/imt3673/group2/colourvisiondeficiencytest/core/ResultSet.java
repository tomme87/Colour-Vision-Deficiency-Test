package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSet {
    private List<Result> results = new ArrayList<>();
    private String testId;
    private Date time;
    private Map<String, Object> extraData = new HashMap<>();

    public ResultSet() {
    }

    public ResultSet(List<Result> results, String testId, Date time, Map<String, Object> extraData) {
        this.results = results;
        this.testId = testId;
        this.time = time;
        this.extraData = extraData;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
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
    public void addResult (Result result) {
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
