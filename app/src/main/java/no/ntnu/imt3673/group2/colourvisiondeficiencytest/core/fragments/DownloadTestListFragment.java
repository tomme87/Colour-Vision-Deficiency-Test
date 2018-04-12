package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadTestListFragment extends Fragment {


    public DownloadTestListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download_test_list, container, false);
    }

}
