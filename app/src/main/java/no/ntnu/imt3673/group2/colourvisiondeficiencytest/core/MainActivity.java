package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.DownloadTestListFragment;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.LocalTestListFragment;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.TestWelcomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.startLocalListFragment();
    }

    /**
     * Show the list of locally downloaded tests.
     */
    public void startLocalListFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new LocalTestListFragment())
                .commit();
    }

    /**
     * Show the list of downloadable tests.
     */
    public void startDownloadFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new DownloadTestListFragment())
                .addToBackStack(null)
                .commit();
    }

    /**
     * Show the welcome screen for a test.
     *
     * TODO parameter with test id?
     */
    public void startWelcomeFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new TestWelcomeFragment())
                .addToBackStack(null)
                .commit();
    }
}
