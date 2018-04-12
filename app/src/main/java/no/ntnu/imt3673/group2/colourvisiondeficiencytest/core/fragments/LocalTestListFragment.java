package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.MainActivity;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalTestListFragment extends Fragment {
    MainActivity mainActivity;


    public LocalTestListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainActivity = (MainActivity) getActivity();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_test_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton floatingActionButton = getView().findViewById(R.id.fab_open_download_list);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.startDownloadFragment();
            }
        });
    }
}
