package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaTestActivity;

public class TestActivityClassFactory {

    public TestActivityClassFactory() {
    }

    public Class<?> getActivity(String type) {
        switch (type) {
            case "Ishihara":
                return IshiharaTestActivity.class;
            default:
                return null;
        }
    }
}
