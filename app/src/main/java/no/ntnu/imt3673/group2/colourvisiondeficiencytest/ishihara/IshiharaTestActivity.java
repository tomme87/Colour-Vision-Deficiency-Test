package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.LocalTestListFragment;

public class IshiharaTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishihara_test);
    }

    public void startIshiharaTestFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new LocalTestListFragment())
                .addToBackStack(null)
                .commit();
    }

    public void startIshiharaResultFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new LocalTestListFragment())
                .addToBackStack(null)
                .commit();
    }
}
