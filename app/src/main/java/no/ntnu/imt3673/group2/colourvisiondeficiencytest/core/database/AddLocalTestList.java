package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * Adds a list of TestInfo objects into database
 */
public class AddLocalTestList extends AsyncTask <List<TestInfo>, Void, List<TestInfo>> {

    private Context appContext;

    // passing application context from service accessing db
    public AddLocalTestList(Context appContext) {
        this.appContext = appContext;
    }

    /**
     * Adds a list of TestInfo objects to database
     * @param lists
     * @return updated list of lcal tests
     */
    @Override
    protected List<TestInfo> doInBackground(List<TestInfo>[] lists) {
        TestInfoDAO testInfoDAO = AppDatabase.getAppDatabase(this.appContext).testInfoDAO();
        testInfoDAO.insertAll(lists[0]);
        return testInfoDAO.getAll();
    }

    /**
     * Updates list view when done fetching data from database
     * @param testInfoList
     */
    @Override
    protected void onPostExecute(List<TestInfo> testInfoList) {
        super.onPostExecute(testInfoList);
        //TODO: update LocalTestList
    }
}
