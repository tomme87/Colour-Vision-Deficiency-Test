package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.ResultSet;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments.IshiharaTestFragment;

public class IshiharaTestActivity extends AppCompatActivity {
    final String TAG = "Mob_Dev_Project";

    private Test<IshiharaPlate> test;
    private TestInfo testInfo;
    private List<IshiharaPlate> plates;
    private ResultSet results;

    //  TODO: Create Test object that contains TestInfo and List<Plates>
    //  TODO: Create/get ResultSet object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishihara_test);

        Log.v(TAG, "Hello from ishihara test activity");
        runIshiharaTestFragment();
    }


    public void runIshiharaTestFragment() {
        IshiharaTestFragment fragment = new IshiharaTestFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }
    /*

    public void startIshiharaResultFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new LocalTestListFragment())
                .addToBackStack(null)
                .commit();
    }
    */
}
