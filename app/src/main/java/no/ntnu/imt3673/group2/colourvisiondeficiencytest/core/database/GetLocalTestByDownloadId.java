package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * AsyncTask that reads  from the database which test are already downloaded
 *
 */
public class GetLocalTestByDownloadId extends AsyncTask<Long, Void, TestInfo> {
    //private Context context;
    private final WeakReference<Context> weakContext;
    private final PostExecuteListener postExecuteListener;

    /**
     * Passing application context from service accessing db
     * @param context Context of the application
     * @param postExecuteListener Listener for completed process.
     */
    public GetLocalTestByDownloadId(Context context, PostExecuteListener postExecuteListener) {
        //this.context = context;
        this.weakContext = new WeakReference<>(context);
        this.postExecuteListener = postExecuteListener;
    }

    /**
     * Gets form Db information of a test given its ID.Gets form Db information of a test given its ID.
     * @param longs Download ID.
     * @return TestInfo Object from local db.
     */
    @Override
    protected TestInfo doInBackground(Long... longs) {
        Context context = weakContext.get();
        if (context == null) {
            // context is no longer valid, don't do anything!
            return null;
        }

        return AppDatabase.getAppDatabase(context).testInfoDAO().getByDownloadId(longs[0]);
    }

    /**
     * Updates list view when done fetching data from database
     * @param testInfo Information about an test.
     */
    @Override
    protected void onPostExecute(TestInfo testInfo) {
        if(postExecuteListener != null && testInfo != null) {
            postExecuteListener.onTestInfoFound(testInfo);
        }
    }

    /**
     *
     *
     */
    public interface PostExecuteListener {
        void onTestInfoFound(TestInfo testInfo);
    }
}
