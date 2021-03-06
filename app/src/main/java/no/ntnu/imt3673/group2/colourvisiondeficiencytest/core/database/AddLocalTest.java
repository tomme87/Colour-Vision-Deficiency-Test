package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * AsyncTask that stores information about newly downloaded test in local database
 */
public class AddLocalTest extends AsyncTask<TestInfo,Void,List<TestInfo>> {

    private static final String TAG = "AddLocalTest";
    private final WeakReference<Context> weakContext;
    //private final Context appContext;

    // passing application context from service accessing db
    public AddLocalTest(Context appContext) {
        this.weakContext = new WeakReference<>(appContext);
        //this.appContext = appContext;
    }

    /**
     * insert all TestInfo objects
     * @param testInfos Information of the tests objects.
     * @return newly updated TestInfo list
     */
    @Override
    protected List<TestInfo> doInBackground(TestInfo... testInfos) {
        Context context = weakContext.get();
        if (context == null) {
            // context is no longer valid, don't do anything!
            return null;
        }
        TestInfoDAO testInfoDAO = AppDatabase.getAppDatabase(context).testInfoDAO();
        for (TestInfo testInfo: testInfos) {
            try {
                testInfoDAO.insert(testInfo);
            } catch (SQLiteConstraintException e) {
                Log.d(TAG, "Already in database");
            }

        }
        return testInfoDAO.getAll();
    }

    /**
     * Updates list view when done fetching data from database
     * @param testInfoList List that contains information of the local tests.
     */
    @Override
    protected void onPostExecute(List<TestInfo> testInfoList) {
        super.onPostExecute(testInfoList);
        // TODO: update LocalTestList
        Log.d(TAG, "Size: "+ testInfoList.size());
    }
}
