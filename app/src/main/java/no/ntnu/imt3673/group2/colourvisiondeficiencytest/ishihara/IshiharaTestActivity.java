package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.util.Date;
import java.util.Random;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.ResultSet;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments.IshiharaTestFragment;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments.IshiharaTestResultsFragment;

public class IshiharaTestActivity extends AppCompatActivity implements OnGetActivityDataListener,
        CreateTestObject.OnTaskDone, CreateThresholdObject.OnTaskDone {
    private static final String TAG = "IshiharaTestActivity";

    private Test<IshiharaPlate> mTest;
    private TestInfo testInfo;
    private ResultSet<IshiharaResult> results;

    IshiharaThreshold ishiharaThreshold;

    IshiharaPlate currentPlate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.testInfo = getIntent().getParcelableExtra(TestInfo.EXTRA);

        File file = new File(getFilesDir(),
                Test.TEST_FOLDER + File.separator + testInfo.getId() + File.separator + "plates.json");
        new CreateTestObject(this.testInfo, this).execute(file);
    }

    /**
     * We stop the test if it is abrupted.
     */
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void runIshiharaTestFragment(int i) {
        Log.d(TAG, "Size before: " + mTest.getPlates().size());
        this.currentPlate = mTest.getPlates().remove(i); // Get and remove.
        Log.d(TAG, "Size after: " + mTest.getPlates().size());

        IshiharaTestFragment fragment = new IshiharaTestFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(android.R.id.content, fragment)
                .commit();
    }

    public void runFirstPlate() {
        initResultSet();
        Integer firstPlate = testInfo.getFirstPlate();
        if (firstPlate != null) {
            for (int i = 0; i < this.mTest.getPlates().size(); i++) {

                if (this.mTest.getPlates().get(i).getId() == firstPlate) {
                    runIshiharaTestFragment(i);
                    return;
                }
            }
        }
        Log.d(TAG, "First plate not found. firstplate is: " + firstPlate);
        runRandomPlate();
    }

    private void initResultSet() {
        this.results = new ResultSet<>();
        this.results.setTestId(this.testInfo.getId());
        this.results.setTime(new Date());
    }

    public void runRandomPlate() {
        int size = mTest.getPlates().size();
        if (size == 0) {
            Log.d(TAG, "No more plates");
            runIshiharaTestSummary();
            return;
        }

        Random random = new Random();
        runIshiharaTestFragment(random.nextInt(size));
    }

    private void runIshiharaTestSummary() {
        IshiharaTestResultsFragment fragment = new IshiharaTestResultsFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(android.R.id.content, fragment)
                .commit();
    }

    /**
     * On input from user
     *
     * @param result what the user typed in the edit text.
     */
    public void storeResultAndNext(String result) {
        Log.d(TAG, "Result is: " + result + ", plate nr: " + this.currentPlate.getId());
        Integer resultNum;

        try {
            resultNum = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            //  Log.d(TAG, "Unable to parse input", e);
            resultNum = null;
        }
        this.results.addResult(new IshiharaResult(this.currentPlate, resultNum));

        runRandomPlate();
    }

    @Override
    public IshiharaPlate getCurrentPlate() {
        return currentPlate;
    }

    @Override
    public TestInfo getTestInfo() {
        return testInfo;
    }

    @Override
    public ResultSet<IshiharaResult> getResultSet() {
        return results;
    }

    @Override
    public IshiharaThreshold getIshiharaThreshold() {
        return ishiharaThreshold;
    }

    @Override
    public void setTest(Test<IshiharaPlate> test) {
        this.mTest = test;

        File file = new File(getFilesDir(),
                Test.TEST_FOLDER + File.separator + testInfo.getId() + File.separator + "thresholds.json");
        new CreateThresholdObject(this).execute(file);
    }

    @Override
    public void setThreshold(IshiharaThreshold threshold) {
        this.ishiharaThreshold = threshold;
        runFirstPlate();
    }
}
