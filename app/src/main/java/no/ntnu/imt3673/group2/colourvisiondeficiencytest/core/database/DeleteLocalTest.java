package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * Delete from database information about a test being removed from local storage
 */
public class DeleteLocalTest extends AsyncTask <TestInfo, Void, List<TestInfo>> {

    private static final String TAG = "DeleteLocalTest";
    private final Context appContext;

    // passing application context from service accessing db
    public DeleteLocalTest(Context appContext) {
        this.appContext = appContext;
    }

    /**
     * Deletes from database all given TestInfo entries
     * @param testInfos
     * @return current local tests list (after deleting)
     */
    @Override
    protected List<TestInfo> doInBackground(TestInfo... testInfos) {
        TestInfoDAO testInfoDAO = AppDatabase.getAppDatabase(this.appContext).testInfoDAO();

        for (TestInfo testInfo: testInfos) {
            testInfoDAO.delete(testInfo);
        }
        return testInfoDAO.getAll();
    }

    /**
     * Updates list view when done fetching data from database
     * @param testInfoList
     */
    @Override
    protected void onPostExecute(List<TestInfo> testInfoList) {
        super.onPostExecute(testInfoList);
        // TODO: update LocalTestList

        Log.d(TAG, "Size: "+ testInfoList.size());
    }
}
