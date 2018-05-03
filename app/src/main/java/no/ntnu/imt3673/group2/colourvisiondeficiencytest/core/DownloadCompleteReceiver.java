package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.services.ProcessDownloadService;
/**
 * Listen to the Download Manager in order to know when a file  has been downloaded.
 */
public class DownloadCompleteReceiver extends BroadcastReceiver {
    public static final String EXTRA_ID = "DlCompleteId";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            return;
        }

        Bundle extras = intent.getExtras();
        if(extras == null) {
            return;
        }

        Long downloadId = extras.getLong(DownloadManager.EXTRA_DOWNLOAD_ID);

        Intent i = new Intent(context, ProcessDownloadService.class);
        i.putExtra(EXTRA_ID, downloadId);
        ProcessDownloadService.enqueueWork(context, i);
    }
}
