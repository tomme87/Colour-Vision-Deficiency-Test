package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * AsyncTask that fetches information about locally stored tests
 */
public class GetAllLocalTests extends AsyncTask <Void, Void, List<TestInfo>> {
    Context appContext;

    // passing application context from service accessing db
    public GetAllLocalTests(Context appContext) {
        this.appContext = appContext;
    }

    /**
     *
     * @param voids
     * @return list of all local tests
     */
    @Override
    protected List<TestInfo> doInBackground(Void... voids) {
        return AppDatabase.getAppDatabase(this.appContext).testInfoDAO().getAll();
    }

    /**
     * Updates list view when done fetching data from database
     * @param testInfos
     */
    @Override
    protected void onPostExecute(List<TestInfo> testInfos) {
        super.onPostExecute(testInfos);
        // TODO: update LocalTestList
    }
}
