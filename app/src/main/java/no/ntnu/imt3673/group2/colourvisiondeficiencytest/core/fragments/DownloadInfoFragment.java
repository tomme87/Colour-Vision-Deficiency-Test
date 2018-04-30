package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.MainActivity;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.AddLocalTest;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.services.DownloadTestService;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadInfoFragment extends Fragment {
    private static final String TAG = "DlInfoFrag";
    private TestInfo testInfo;

    private MainActivity mainActivity;

    private TextView tv_test_name;
    private TextView tv_test_type;
    private TextView tv_test_desc;

    private Button button;

    public DownloadInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.mainActivity = (MainActivity) getActivity();
        this.mainActivity.setActionBarTitle(getString(R.string.app_name_info_fragment));
        this.testInfo = this.mainActivity.getCurrentTestInfo();

        Log.d(TAG, "Test for test: " + testInfo.getName());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_download_info, container, false);

        //  Set the text information
        this.tv_test_name = view.findViewById(R.id.tv_dl_info_name);
        this.tv_test_name.setText(testInfo.getName());

        this.tv_test_type = view.findViewById(R.id.tv_dl_info_type_this_test);
        this.tv_test_type.setText(testInfo.getType());

        this.tv_test_desc = view.findViewById(R.id.tv_dl_info_desc_this_test);
        this.tv_test_desc.setText(testInfo.getDescription());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.button = getView().findViewById(R.id.btn_download);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBtnDownload(view);
            }
        });
    }

    public void onBtnDownload(View view) {
        Log.d(TAG, "Downloading id: " + testInfo.getId());

        for(TestInfo testInfoCheck : mainActivity.getLocalTestInfos()) {
            if(testInfoCheck.getId().equals(testInfo.getId())) {
                Toast.makeText(getContext(), R.string.error_already_downloading, Toast.LENGTH_LONG).show();
                return;
            }
        }
        mainActivity.getLocalTestInfos().add(testInfo);

        Intent i = new Intent(getContext(), DownloadTestService.class);
        i.putExtra(TestInfo.EXTRA, testInfo);
        DownloadTestService.enqueueWork(getContext(), i);

        // TODO Do the actual downloading of files etc. We are just adding to db here for testing.
        //new AddLocalTest(getContext()).execute(testInfo);
        //new DeleteLocalTest(getContext()).execute(testInfo);
    }

}
