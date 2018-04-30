package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.ResultSet;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments.IshiharaTestFragment;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments.IshiharaTestResultsFragment;

public class IshiharaTestActivity extends AppCompatActivity {
    private static final String TAG = "IshiharaTestActivity";
    public static final int NORMAL = 0;
    public static final int DEUTAN = 1;
    public static final int PROTAN = 2;
    public static final int TOTAL = 3;
    private int[] counters = {0,0,0,0};

    private Test<IshiharaPlate> test;
    private TestInfo testInfo;
    private List<IshiharaPlate> plates;
    private ResultSet<IshiharaResult> results;

    IshiharaThreshold ishiharaThreshold;

    IshiharaPlate currentPlate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.testInfo = getIntent().getParcelableExtra(TestInfo.EXTRA);

        File file = new File(getFilesDir(), Test.TEST_FOLDER + File.separator + testInfo.getId() + File.separator + "plates.json");
        new CreateTestObject().execute(file);
    }


    public void runIshiharaTestFragment(int i) {
        Log.d(TAG, "Size before: " + test.getPlates().size());
        this.currentPlate = test.getPlates().remove(i); // Get and remove.
        Log.d(TAG, "Size after: " + test.getPlates().size());

        IshiharaTestFragment fragment = new IshiharaTestFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                //.addToBackStack(null)
                .commit();
    }

    public void runFirstPlate() {
        initResultSet();
        Integer firstPlate = testInfo.getFirstPlate();
        if(firstPlate != null) {
            for (int i = 0; i < this.test.getPlates().size(); i++) {

                if (this.test.getPlates().get(i).getId() == firstPlate) {
                    runIshiharaTestFragment(i);
                    return;
                }
            }
        }
        Log.d(TAG, "First plate not found. firstplate is: " + firstPlate);
        runRandomPlate();
    }

    private void initResultSet() {
        this.results = new ResultSet<IshiharaResult>();
        this.results.setTestId(this.testInfo.getId());
        this.results.setTime(new Date());
    }

    public void runRandomPlate() {
        int size = test.getPlates().size();
        if(size == 0) {
            Log.d(TAG, "No more plates");
            summerizeResults();
            return;
        }

        Random random = new Random();
        runIshiharaTestFragment(random.nextInt(size));
    }

    private void summerizeResults() {


        boolean matched = false;

        for (IshiharaResult result: this.results.getResults()) {
            IshiharaPlate plate = result.getPlate();
            Integer answer = result.getAnswer();

            matched = isMatched(plate, answer);
            if (!matched) {
                // treat unmatched answer as "null"
                isMatched(plate, null);
            }
        }

        runIshiharaTestSummary();
    }

    private void runIshiharaTestSummary() {
        IshiharaTestResultsFragment fragment = new IshiharaTestResultsFragment();
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                //.addToBackStack(null)
                .commit();
    }

    private boolean isMatched(IshiharaPlate plate, Integer answer) {
        boolean matched = false;
        if (answer == plate.getNormal()) {
            this.counters[NORMAL]++;
            matched = true;
        }
        if (answer == plate.getDeutanStrong() ) { 
            this.counters[DEUTAN]++;
            matched = true;
        }
        if (answer == plate.getProtanStrong()) {
            this.counters[PROTAN]++;
            matched = true;
        }
        if (answer == plate.getTotal()) {
            this.counters[TOTAL]++;
            matched = true;
        }
        return matched;
    }

    /**
     * On input from user
     * @param result
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

    public IshiharaPlate getCurrentPlate() {
        return currentPlate;
    }

    public TestInfo getTestInfo() {
        return testInfo;
    }

    public ResultSet<IshiharaResult> getResults() {
        return results;
    }

    public int[] getCounters() {
        return counters;
    }

    public IshiharaThreshold getIshiharaThreshold() {
        return ishiharaThreshold;
    }

    class CreateTestObject extends AsyncTask<File, Void, Test> {

        @Override
        protected Test doInBackground(File... files) {
            Gson gson = new Gson();
            final Type type = new TypeToken<List<IshiharaPlate>>(){}.getType();
            try {
                JsonReader jsonReader = new JsonReader(new FileReader(files[0]));
                List<IshiharaPlate> ishiharaPlates = gson.fromJson(jsonReader, type);
                return new Test<>(testInfo, ishiharaPlates);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d(TAG, "Plates not found", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Test mTest) {
            test = mTest;
            File file = new File(getFilesDir(), Test.TEST_FOLDER + File.separator + testInfo.getId() + File.separator + "thresholds.json");
            new CreateThresholdObject().execute(file);
        }
    }

    class CreateThresholdObject extends AsyncTask<File, Void, IshiharaThreshold> {

        @Override
        protected IshiharaThreshold doInBackground(File... files) {
            Gson gson = new Gson();
            try {
                JsonReader jsonReader = new JsonReader(new FileReader(files[0]));
                IshiharaThreshold ishiharaThreshold = gson.fromJson(jsonReader, IshiharaThreshold.class);
                return ishiharaThreshold;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.d(TAG, "Threshold file not found", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(IshiharaThreshold threshold) {
            ishiharaThreshold = threshold;
            runFirstPlate();
        }
    }
}
