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
import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments.IshiharaTestFragment;

public class IshiharaTestActivity extends AppCompatActivity {
    private static final String TAG = "IshiharaTestActivity";

    //  TODO: Create Test object that contains TestInfo and List<Plates>
    //  TODO: Create/get ResultSet object

    TestInfo testInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.testInfo = getIntent().getParcelableExtra(TestInfo.EXTRA);

        File file = new File(getFilesDir(), Test.TEST_FOLDER + File.separator + testInfo.getId() + File.separator + "plates.json");
        new CreateTestObject().execute(file);

        setContentView(R.layout.activity_ishihara_test);
    }

    /*
    public void startIshiharaTestFragment() {
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new IshiharaTestFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

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
        protected void onPostExecute(Test test) {
            IshiharaPlate ishiharaPlate = (IshiharaPlate) test.getPlates().get(0);

            Log.d(TAG, "Test object created : " + test.getInfo().getName() + " : " + ishiharaPlate.getFilename());
        }
    }
}
