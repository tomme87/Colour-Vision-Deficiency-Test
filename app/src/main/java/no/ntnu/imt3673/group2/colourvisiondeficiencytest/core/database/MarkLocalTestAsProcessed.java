package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * AsyncTask that marks  in Db the test that are already downloaded.
 */
public class MarkLocalTestAsProcessed extends AsyncTask<TestInfo, Void, TestInfo> {
    private final WeakReference<Context> weakContext;
    private final PostExecuteListener postExecuteListener;

    /**
     * Passing application context from service accessing db
     * @param context Context of the application
     * @param postExecuteListener  Listener for completed process.
     */
    public MarkLocalTestAsProcessed(Context context, PostExecuteListener postExecuteListener) {
        //this.context = context;
        this.weakContext = new WeakReference<>(context);
        this.postExecuteListener = postExecuteListener;
    }

    /**
     * Marks a test in DB as downloaded, given its ID
     *
     * @param testInfos TestInfo objects
     * @return TestInfo object recently marked as downloaded.
     */
    @Override
    protected TestInfo doInBackground(TestInfo... testInfos) {
        Context context = weakContext.get();
        if (context == null) {
            // context is no longer valid, don't do anything!
            return null;
        }

        String id = testInfos[0].getId();

        AppDatabase appDatabase = AppDatabase.getAppDatabase(context);

        appDatabase.testInfoDAO().setProcessedById(id);
        return appDatabase.testInfoDAO().getById(id);
    }

    @Override
    protected void onPostExecute(TestInfo testInfo) {
        if(postExecuteListener != null && testInfo != null) {
            postExecuteListener.onProcessedMarked(testInfo);
        }
    }

    /**
     * Listener for completed process.
     */
    public interface PostExecuteListener {
        void onProcessedMarked(TestInfo testInfo);
    }
}
