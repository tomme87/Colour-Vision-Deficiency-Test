package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaTestActivity;

/**
 * This class is a factory of the different kind of tests the application  supports.
 */
public class TestActivityClassFactory {

    /**
     * Empty constructor
     */
    public TestActivityClassFactory() {
    }

    /**
     * Return the kind of test that will be created.
     * @param type of test.
     * @return The kind of test that will be created.
     */
    public Class<?> getActivity(String type) {
        switch (type) {
            case "Ishihara":
                return IshiharaTestActivity.class;
            default:
                return null;
        }
    }
}
