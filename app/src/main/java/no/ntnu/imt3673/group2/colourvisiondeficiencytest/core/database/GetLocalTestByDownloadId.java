package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * Created by Tomme on 22.04.2018.
 */

public class GetLocalTestByDownloadId extends AsyncTask<Long, Void, TestInfo> {
    private Context context;

    public GetLocalTestByDownloadId(Context context) {
        this.context = context;
    }

    @Override
    protected TestInfo doInBackground(Long... longs) {
        return AppDatabase.getAppDatabase(this.context).testInfoDAO().getByDownloadId(longs[0]);
    }
}
