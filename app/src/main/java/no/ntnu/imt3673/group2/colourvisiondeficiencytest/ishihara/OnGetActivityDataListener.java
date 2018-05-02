package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.ResultSet;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * FOr communication with fragments
 */

public interface OnGetActivityDataListener {
    IshiharaPlate getCurrentPlate();

    TestInfo getTestInfo();

    void storeResultAndNext(String result);

    IshiharaThreshold getIshiharaThreshold();

    ResultSet<IshiharaResult> getResultSet();
}
