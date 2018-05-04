package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.DownloadInfoFragment;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.DownloadTestListFragment;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.LocalTestListFragment;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.TestWelcomeFragment;

/**
 * Main activity of the application.
 */
public class MainActivity extends AppCompatActivity implements OnGetActivityDataListener {
    private static final String TAG = "MainActivity";

    // Local and downloadable list. final is important!!
    final private List<TestInfo> localTestInfos = new ArrayList<>();
    final private List<TestInfo> downloadableTestInfos = new ArrayList<>();

    private DownloadCompleteReceiver downloadCompleteReceiver;

    private TestInfo currentTestInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.downloadCompleteReceiver = new DownloadCompleteReceiver();
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadCompleteReceiver, intentFilter);

        //getSupportActionBar().setTitle("Hola");
        this.startLocalListFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(this.downloadCompleteReceiver);
    }

    /**
     * Show the list of locally downloaded tests.
     */
    public void startLocalListFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new LocalTestListFragment())
                .commit();
    }

    /**
     * Show the list of downloadable tests.
     */
    public void startDownloadFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new DownloadTestListFragment())
                .addToBackStack(null)
                .commit();
    }

    /**
     * Show the welcome screen for a test.
     * @param testInfo Information of the test
     */
    public void startWelcomeFragment(TestInfo testInfo) {
        this.currentTestInfo = testInfo;
        Log.d(TAG, "List size: " + this.localTestInfos.size());
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new TestWelcomeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void startWelcomeFragmentFromDownloadInfo(TestInfo testInfo) {
        this.currentTestInfo = testInfo;
        Log.d(TAG, "List size: " + this.localTestInfos.size());
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new TestWelcomeFragment())
                .commit();
    }

    /**
     * From this fragment it its possible to download a test.
     * The fragment shows information about the test as well.
     * @param testInfo Information of the test
     */
    public void startDownloadInfoFragment(TestInfo testInfo) {
        this.currentTestInfo = testInfo;
        Log.d(TAG, "List size: " + this.downloadableTestInfos.size());

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new DownloadInfoFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public List<TestInfo> getLocalTestInfos() {
        return localTestInfos;
    }

    
    public List<TestInfo> getDownloadableTestInfos() {
        return downloadableTestInfos;
    }

    @Override
    public TestInfo getCurrentTestInfo() {
        return currentTestInfo;
    }
}
