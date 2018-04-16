package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * AsyncTask that stores information about newly downloaded test in local database
 */
public class AddLocalTest extends AsyncTask<TestInfo,Void,List<TestInfo>> {

    private Context appContext;

    // passing application context from service accessing db
    public AddLocalTest(Context appContext) {
        this.appContext = appContext;
    }

    /**
     * insert all TestInfo objects
     * @param testInfos
     * @return newly updated TestInfo list
     */
    @Override
    protected List<TestInfo> doInBackground(TestInfo... testInfos) {
        TestInfoDAO testInfoDAO = AppDatabase.getAppDatabase(this.appContext).testInfoDAO();
        for (TestInfo testInfo: testInfos) {
            testInfoDAO.insert(testInfo);
        }
        return testInfoDAO.getAll();
    }

    @Override
    protected void onPostExecute(List<TestInfo> testInfoList) {
        super.onPostExecute(testInfoList);
        // TODO: update LocalTestList
    }
}
