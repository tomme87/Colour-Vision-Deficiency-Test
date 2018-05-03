package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class a represents the sets of results of a test.
 * @param <T> Generic type. It must extends Result class.
 */
public class ResultSet <T extends Result>{
    private List<T> results = new ArrayList<>();
    private String testId;
    private Date time;
    private Map<String, Object> extraData = new HashMap<>();

    /**
     * Empty constructor.
     */
    public ResultSet() {
    }

    /**
     * Constructor
     *
     * @param results List of results of the test
     * @param testId  Id of the test
     * @param time    Timestamp of the test
     * @param extraData Eventual extra data needed.
     */
    public ResultSet(List<T> results, String testId, Date time, Map<String, Object> extraData) {
        this.results = results;
        this.testId = testId;
        this.time = time;
        this.extraData = extraData;
    }

    /**
     * Gets the results of the test
     * @return  the results of the test
     */
    public List<T> getResults() {
        return results;
    }

    /**
     * Sets the results of the test
     * @param results the results of the test
     */
    public void setResults(List<T> results) {
        this.results = results;
    }

    /**
     * Gets the id of the test
     * @return the id of the test
     */
    public String getTestId() {
        return testId;
    }

    /**
     * Sets the id of the test
     * @param testId the id of the test
     */
    public void setTestId(String testId) {
        this.testId = testId;
    }

    /**
     * Gets the timestamp of the test
     * @return the timestamp of the test
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets the timestamp of the test
     * @param time the timestamp of the test
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Gets the extra data of the test
     * @return the extra data of the test
     */
    public Map<String, Object> getExtraData() {
        return extraData;
    }

    /**
     * Sets the extra data of the test.
     * @param extraData the extra data of the test.
     */
    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    /**
     * Adds result to set
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
