package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;


import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a test.
 * @param <T> Generic type that extends Plate class.
 */
public class Test <T extends Plate>{
    public static final String TEST_FOLDER = "tests";
    private TestInfo info;
    private List<T> plates = new ArrayList<>();

    /**
     * Empty constructor.
     */
    public Test() {
    }

    /**
     * Constructor.
     * @param info TestInfo object.
     * @param plates List of plates.
     */
    public Test(TestInfo info, List<T> plates) {
        this.info = info;
        this.plates = plates;
    }

    /**
     * Gets the TestInfo object.
     * @return the TestInfo object.
     */
    public TestInfo getInfo() {
        return info;
    }

    /**
     * Sets the TestInfo object.
     * @param info the TestInfo object.
     */
    public void setInfo(TestInfo info) {
        this.info = info;
    }

    /**
     * Gets the plates list of the test.
     * @return the plates list of the test.
     */
    public List<T> getPlates() {
        return plates;
    }

    /**
     * Sets the plates list of the test.
     * @param plates the plates list of the test.
     */
    public void setPlates(List<T> plates) {
        this.plates = plates;
    }
}
