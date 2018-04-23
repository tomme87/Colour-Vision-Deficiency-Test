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

        testInfo = new TestInfo(
                "ishi1",
                "Ishihara test 1",
                "This is the first ishihara test",
                "www.example.com",
                "www.example.com",
                1,
                "ishihara",
                1,
                (long) 1,
                true
        );
        IshiharaPlate plate = new IshiharaPlate();
        plate.setId(1);
        plate.setFilename("filename to plate");
        plate.setNormal(12);
        plate.setDeutanStrong(12);
        plate.setProtanStrong(12);
        plate.setTotal(12);
        plate.setExtra(false);

        plates.add(plate);

        plate = new IshiharaPlate();
        plate.setId(2);
        plate.setFilename("filename to plate2");
        plate.setNormal(10);
        plate.setDeutanStrong(15);
        plate.setProtanStrong(15);
        plate.setTotal(null);
        plate.setExtra(false);

        plates.add(plate);

        this.test.setInfo(testInfo);
        this.test.setPlates(plates);

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
