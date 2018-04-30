package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.MainActivity;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestActivityClassFactory;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestWelcomeFragment extends Fragment {
    private Button button;
    private TestActivityClassFactory testActivityClassFactory;
    MainActivity mainActivity;

    public TestWelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.testActivityClassFactory = new TestActivityClassFactory();
        this.mainActivity = (MainActivity) getActivity();
        this.mainActivity.setActionBarTitle(getString(R.string.app_name_info_fragment));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_welcome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.button = getView().findViewById(R.id.btn_start_test);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestInfo testInfo = mainActivity.getCurrentTestInfo();
                Intent i = new Intent(getContext(), testActivityClassFactory.getActivity(testInfo.getType()));
                i.putExtra(TestInfo.EXTRA, testInfo);
                startActivity(i);
            }
        });
    }
}
