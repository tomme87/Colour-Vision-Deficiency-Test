package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class creates the IshiaraThreshold object from thresholds.json
 */

class CreateThresholdObject extends AsyncTask<File, Void, IshiharaThreshold> {
    private static final String TAG = "CreateThresholdObject";
    private final OnTaskDone done;

    /**
     * Interface to use when a task is done.
     */
    public interface OnTaskDone {
        void setThreshold(IshiharaThreshold threshold);
    }

    /**
     * Constructor with data needed to create Threshold object object.
     * @param done Listener to use after prcessing.
     */
    CreateThresholdObject(OnTaskDone done) {
        this.done = done;
    }

    /**
     * Raad JSON file and marshall to IshiharaTHreshold object.
     * @param files The thresholds.json file.
     * @return IshiharaThreashold object marshalled from the JSON file.
     */
    @Override
    protected IshiharaThreshold doInBackground(File... files) {
        Gson gson = new Gson();
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(files[0]));
            return gson.fromJson(jsonReader, IshiharaThreshold.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "Threshold file not found", e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(IshiharaThreshold threshold) {
        if (done != null && threshold != null) {
            done.setThreshold(threshold);
        }
    }
}
