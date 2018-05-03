package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import java.util.List;

/**
 *  Used for communication from MainActivity to Fragments.
 */
public interface OnGetActivityDataListener {
    TestInfo getCurrentTestInfo();

    List<TestInfo> getLocalTestInfos();

    void startWelcomeFragmentFromDownloadInfo(TestInfo testInfo);

    List<TestInfo> getDownloadableTestInfos();

    void startDownloadInfoFragment(TestInfo testInfo);

    void startDownloadFragment();

    void startWelcomeFragment(TestInfo testInfo);
}
