package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.services;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.DownloadCompleteReceiver;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.GetLocalTestByDownloadId;

/**
 * Created by Tomme on 22.04.2018.
 */

public class ProcessDownloadService extends JobIntentService {
    static final int JOB_ID = 1001;
    private static final String TAG = "ProcessDlService";


    /**
     * Convenience method for enqueuing work in to this service.
     */
    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ProcessDownloadService.class, JOB_ID, work);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        long downloadId = intent.getLongExtra(DownloadCompleteReceiver.EXTRA_ID, 0);
        if(downloadId == 0) {
            return;
        }

        new GetLocalTestByDownloadId(getApplicationContext()){
            @Override
            protected void onPostExecute(TestInfo testInfo) {
                processDownload(testInfo);
            }
        }.execute(downloadId);
    }

    private void processDownload(TestInfo testInfo) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(testInfo.getDownloadId());
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Cursor c = manager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                Log.d(TAG, "Processing download: " + testInfo.getName());
            }
        }
    }
}
