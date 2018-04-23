package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import android.content.Intent;
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
import java.util.List;
import java.util.Random;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.ResultSet;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments.IshiharaTestFragment;

public class IshiharaTestActivity extends AppCompatActivity {
    private static final String TAG = "IshiharaTestActivity";

    private Test<IshiharaPlate> test;
    private TestInfo testInfo;
    private List<IshiharaPlate> plates;
    private ResultSet results;

    IshiharaPlate currentPlate;

    //  TODO: Create Test object that contains TestInfo and List<Plates>
    //  TODO: Create/get ResultSet object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.testInfo = getIntent().getParcelableExtra(TestInfo.EXTRA);

        File file = new File(getFilesDir(), Test.TEST_FOLDER + File.separator + testInfo.getId() + File.separator + "plates.json");
        new CreateTestObject().execute(file);

        /*
        setContentView(R.layout.activity_ishihara_test);

        Log.v(TAG, "Hello from ishihara test activity");
        runIshiharaTestFragment();
        */
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

    public void runRandomPlate() {
        int size = test.getPlates().size();
        if(size == 0) {
            // TODO end test, show results
            Log.d(TAG, "No more plates");
            return;
        }

        Random random = new Random();
        runIshiharaTestFragment(random.nextInt(size));
    }

    public void storeResultAndNext(String result) {
        Log.d(TAG, "Result is: " + result + ", plate nr: " + this.currentPlate.getId());

        try {
            int resultNum = Integer.parseInt(result);
            // TODO create Result and add to ResultSet
        } catch (NumberFormatException e) {
            Log.d(TAG, "Unable to parse input", e);
        }

        runRandomPlate();
    }

    public IshiharaPlate getCurrentPlate() {
        return currentPlate;
    }

    public TestInfo getTestInfo() {
        return testInfo;
    }

    /*

    public void startIshiharaResultFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new LocalTestListFragment())
                .addToBackStack(null)
                .commit();
    }
    */

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
            //IshiharaPlate ishiharaPlate = (IshiharaPlate) mTest.getPlates().get(0);
            test = mTest;
            runFirstPlate();
            //Log.d(TAG, "Test object created : " + test.getInfo().getName() + " : " + ishiharaPlate.getFilename());
        }
    }
}
