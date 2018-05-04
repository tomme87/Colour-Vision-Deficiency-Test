package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * AsynTask that creates the Test object from plates.json
 */
class CreateTestObject extends AsyncTask<File, Void, Test<IshiharaPlate>> {
    private static final String TAG = "CreateTestObject";
    private final TestInfo testInfo;
    private final OnTaskDone done;

    /**
     * Interface to use when a task is done.
     */
    public interface OnTaskDone {
        void setTest(Test<IshiharaPlate> test);
    }

    /**
     * Constructor with data needed to create Test object.
     *
     * @param testInfo TestInfo object to be added to the Test object.
     * @param done Listener to use after prcessing.
     */
    CreateTestObject(TestInfo testInfo, OnTaskDone done) {
        this.testInfo = testInfo;
        this.done = done;
    }

    /**
     * Read the JSON file and marshall to list of IshiharaPlate, then add it to Test object.
     * @param files the plates.json file
     * @return Test object that contains plates and TestInfo.
     */
    @Override
    protected Test<IshiharaPlate> doInBackground(File... files) {
        Gson gson = new Gson();
        final Type type = new TypeToken<List<IshiharaPlate>>() {
        }.getType();
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
    protected void onPostExecute(Test<IshiharaPlate> test) {
        if (done != null && test != null) {
            done.setTest(test);
        }
    }
}
