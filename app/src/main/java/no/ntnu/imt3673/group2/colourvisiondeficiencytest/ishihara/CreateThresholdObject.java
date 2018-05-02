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

public class CreateThresholdObject extends AsyncTask<File, Void, IshiharaThreshold> {
    private static final String TAG = "CreateThresholdObject";
    private OnTaskDone done;

    public interface OnTaskDone {
        void setThreshold(IshiharaThreshold threshold);
    }

    CreateThresholdObject(OnTaskDone done) {
        this.done = done;
    }

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
