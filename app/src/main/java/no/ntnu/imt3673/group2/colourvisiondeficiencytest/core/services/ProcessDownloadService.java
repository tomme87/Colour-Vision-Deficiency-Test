package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.services;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.DownloadCompleteReceiver;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.Test;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.GetLocalTestByDownloadId;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.MarkLocalTestAsProcessed;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments.LocalTestListFragment;

/**
 * Created by Tomme on 22.04.2018.
 */

public class ProcessDownloadService extends JobIntentService implements GetLocalTestByDownloadId.PostExecuteListener, MarkLocalTestAsProcessed.PostExecuteListener {
    static final int JOB_ID = 1001;
    private static final String TAG = "ProcessDlService";


    /**
     * Convenience method for enqueuing work in to this service.
     */
    public static void enqueueWork(Context context, Intent work) {
        enqueueWork(context, ProcessDownloadService.class, JOB_ID, work);
    }

    /**
     * Process the the work that has been enqueued for this service
     * @param intent
     */
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        long downloadId = intent.getLongExtra(DownloadCompleteReceiver.EXTRA_ID, 0);
        if(downloadId == 0) {
            return;
        }

        new GetLocalTestByDownloadId(getApplicationContext(), this).execute(downloadId);
    }

    @Override
    public void onTestInfoFound(TestInfo testInfo) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(testInfo.getDownloadId());
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Cursor c = manager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                Log.d(TAG, "Processing download: " + testInfo.getName() + " : " + Uri.parse(uriString).getPath());
                processDownload(testInfo, new File(Uri.parse(uriString).getPath()));
            }
        }
    }

    private void processDownload(TestInfo testInfo, File zip) {
        try {
            unzip(zip, new File(getApplicationContext().getFilesDir(), Test.TEST_FOLDER + File.separator + testInfo.getId()));
            Log.d(TAG, "Unzipped");
            // TODO mark as processed and broadcast it?
            new MarkLocalTestAsProcessed(getApplicationContext(), this).execute(testInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onProcessedMarked(TestInfo testInfo) {
        Log.d(TAG, "db updated, Sending bradcast");
        Intent intent = new Intent(LocalTestListFragment.DownloadProcessedReceiver.ACTION_RESP);
        //intent.putExtra(TestInfo.EXTRA, testInfo);
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        sendBroadcast(intent);
    }

    /**
     * Unzip file.
     *
     * Taken from: https://stackoverflow.com/a/27050680
     *
     * @param zipFile File to unzip
     * @param targetDirectory where to unzip
     * @throws IOException
     */
    public static void unzip(File zipFile, File targetDirectory) throws IOException {
        Log.d(TAG, "Output dir: " + targetDirectory.getAbsolutePath());
        try (ZipInputStream zis = new ZipInputStream(
                new BufferedInputStream(new FileInputStream(zipFile)))) {
            ZipEntry ze;
            int count;
            byte[] buffer = new byte[8192];
            while ((ze = zis.getNextEntry()) != null) {
                File file = new File(targetDirectory, ze.getName());
                File dir = ze.isDirectory() ? file : file.getParentFile();
                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Failed to ensure directory: " +
                            dir.getAbsolutePath());
                if (ze.isDirectory())
                    continue;
                FileOutputStream fout = new FileOutputStream(file);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            }
        }
    }
}
