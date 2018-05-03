package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.ResultSet;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * For communication with fragments
 */
public interface OnGetActivityDataListener {

    /**
     * Gets the current plate of a test.
     * @return the current plate of a test.
     */
    IshiharaPlate getCurrentPlate();

    /**
     * Gets the information about a give test.
     * @return the information about a give test.
     */
    TestInfo getTestInfo();

    /**
     * Saves the answer to a given plate that was introduced by the user.
     * And goes to the next plate.
     * @param result the answer to a given plate that was introduced by the user.
     */
    void storeResultAndNext(String result);

    /**
     * Gets the different thresholds necessary fo the test.
     * @return the different thresholds necessary fo the test.
     */
    IshiharaThreshold getIshiharaThreshold();

    /**
     * Sets the different thresholds necessary fo the test.
     * @return the different thresholds necessary fo the test.
     */
    ResultSet<IshiharaResult> getResultSet();
}
