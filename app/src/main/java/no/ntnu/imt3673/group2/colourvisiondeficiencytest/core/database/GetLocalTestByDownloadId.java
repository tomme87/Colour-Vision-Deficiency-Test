package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

public class GetLocalTestByDownloadId extends AsyncTask<Long, Void, TestInfo> {
    //private Context context;
    private final WeakReference<Context> weakContext;
    private PostExecuteListener postExecuteListener;

    public GetLocalTestByDownloadId(Context context, PostExecuteListener postExecuteListener) {
        //this.context = context;
        this.weakContext = new WeakReference<>(context);
        this.postExecuteListener = postExecuteListener;
    }

    @Override
    protected TestInfo doInBackground(Long... longs) {
        Context context = weakContext.get();
        if (context == null) {
            // context is no longer valid, don't do anything!
            return null;
        }

        return AppDatabase.getAppDatabase(context).testInfoDAO().getByDownloadId(longs[0]);
    }

    @Override
    protected void onPostExecute(TestInfo testInfo) {
        if(postExecuteListener != null && testInfo != null) {
            postExecuteListener.onTestInfoFound(testInfo);
        }
    }

    public interface PostExecuteListener {
        void onTestInfoFound(TestInfo testInfo);
    }
}
