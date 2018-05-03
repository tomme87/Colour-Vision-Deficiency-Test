package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.services;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.AddLocalTest;

/**
 * Service that download the tests.
 */
public class DownloadTestService extends JobIntentService{
    static final int JOB_ID = 1000;
    private static final String TAG = "DlAndPocessTestService";
    private static final String DL_DESCRIPTION = "Downloading resources";

    /**
     * Convenience method for enqueuing work in to this service.
     */
    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, DownloadTestService.class, JOB_ID, work);
    }

    /**
     * Process the the work that has been enqueued for this service
     * @param intent
     */
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        TestInfo testInfo = intent.getParcelableExtra(TestInfo.EXTRA);
        Log.d(TAG, "Testinfo: " + testInfo.getName());

        long downloadId = this.downloadZip(testInfo);
        if(downloadId == -1) {
            Toast.makeText(getApplicationContext(), R.string.error_unable_to_download, Toast.LENGTH_LONG).show();
            return;
        }

        testInfo.setDownloadId(downloadId);
        new AddLocalTest(getApplicationContext()).execute(testInfo);
    }

    /**
     * This method downloads the desired test.
     * @param testInfo
     * @return
     */
    private long downloadZip(TestInfo testInfo) {
        Uri uri = Uri.parse(testInfo.getResourceUrl());

        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        if(downloadManager == null) {
            return -1;
        }
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(testInfo.getName());
        request.setDescription(DL_DESCRIPTION);
        request.setDestinationInExternalFilesDir(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS, testInfo.getId() + ".zip");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        return downloadManager.enqueue(request);
    }
}
