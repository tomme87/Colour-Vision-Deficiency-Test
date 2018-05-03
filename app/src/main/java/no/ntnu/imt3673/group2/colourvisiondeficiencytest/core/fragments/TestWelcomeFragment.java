package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.OnGetActivityDataListener;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestActivityClassFactory;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;


/**
 * A simple {@link Fragment} subclass.
 * The first fragment that is shown when an user run a test.
 * This fragment shows information about the test that is going to be run.
 */
public class TestWelcomeFragment extends Fragment {
    private TestActivityClassFactory testActivityClassFactory;

    private TestInfo testInfo;

    public TestWelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OnGetActivityDataListener callback;
        try {
            callback = (OnGetActivityDataListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnGetActivityDataListener");
        }

        this.testActivityClassFactory = new TestActivityClassFactory();

        getActivity().setTitle(R.string.app_name_welcome_fragment);

        this.testInfo = callback.getCurrentTestInfo();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_welcome, container, false);

        //  Set the text information
        TextView tvTestName = view.findViewById(R.id.tv_test_welcome_info_name);
        tvTestName.setText(testInfo.getName());

        TextView tvTestType = view.findViewById(R.id.tv_test_welcome_info_type_this_test);
        tvTestType.setText(testInfo.getType());

        TextView tvTestDesc = view.findViewById(R.id.tv_test_welcome_info_desc_this_test);
        tvTestDesc.setText(testInfo.getDescription());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnStartTest = view.findViewById(R.id.btn_start_test);

        btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), testActivityClassFactory.getActivity(testInfo.getType()));
                i.putExtra(TestInfo.EXTRA, testInfo);
                startActivity(i);
            }
        });
    }
}
