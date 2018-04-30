package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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

    private TestInfo testInfo;

    private TextView tv_test_name;
    private TextView tv_test_type;
    private TextView tv_test_desc;
    private SeekBar sb_brightness_slider;

    public TestWelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.testActivityClassFactory = new TestActivityClassFactory();
        this.mainActivity = (MainActivity) getActivity();

        this.mainActivity.setActionBarTitle(getString(R.string.app_name_info_fragment));

        this.testInfo = mainActivity.getCurrentTestInfo();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test_welcome, container, false);

        //  Set the text information
        this.tv_test_name = view.findViewById(R.id.tv_test_welcome_info_name);
        this.tv_test_name.setText(testInfo.getName());

        this.tv_test_type = view.findViewById(R.id.tv_test_welcome_info_type_this_test);
        this.tv_test_type.setText(testInfo.getType());

        this.tv_test_desc = view.findViewById(R.id.tv_test_welcome_info_desc_this_test);
        this.tv_test_desc.setText(testInfo.getDescription());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.button = getView().findViewById(R.id.btn_start_test);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TestInfo testInfo = mainActivity.getCurrentTestInfo();
                Intent i = new Intent(getContext(), testActivityClassFactory.getActivity(testInfo.getType()));
                i.putExtra(TestInfo.EXTRA, testInfo);
                startActivity(i);
            }
        });
    }
}
