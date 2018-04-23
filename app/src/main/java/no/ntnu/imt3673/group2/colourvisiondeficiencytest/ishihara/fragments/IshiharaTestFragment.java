package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;

public class IshiharaTestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ishihara_test, container, false);
    }
}
