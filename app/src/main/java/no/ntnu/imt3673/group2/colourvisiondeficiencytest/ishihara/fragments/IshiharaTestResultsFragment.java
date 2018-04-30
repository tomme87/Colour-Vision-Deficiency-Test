package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaPlate;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaTestActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class IshiharaTestResultsFragment extends Fragment {

    private static final String TAG = "IshiharaTestResults";
    IshiharaTestActivity activity;
    TestInfo testInfo;

    Button btnSend;
    Button btnExit;
    TextView tvResults;


    public IshiharaTestResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.activity = (IshiharaTestActivity) getActivity();
        this.testInfo = this.activity.getTestInfo();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ishihara_test_results, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.btnSend = view.findViewById(R.id.btn_send_and_exit);
        this.btnExit = view.findViewById(R.id.btn_exit);
        this.tvResults = view.findViewById(R.id.tv_result_summary);

        this.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "sending results");
                Log.d(TAG, "exiting");
                getActivity().finish();
            }
        });

        this.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "exiting");
                getActivity().finish();
            }
        });

        this.tvResults.setText(formatResults());

        super.onViewCreated(view, savedInstanceState);
    }

    private int formatResults() {
        switch (this.activity.getMaxIndex()) {
            case IshiharaTestActivity.NORMAL:
                return R.string.results_ishihara_normal;
            case IshiharaTestActivity.DEUTAN:
                if (this.activity.getCounters()[IshiharaTestActivity.DEUTAN] >
                        this.activity.getCounters()[IshiharaTestActivity.PROTAN]) {
                    return R.string.results_ishihara_deutan;
                }
                return R.string.results_ishihara_general_red_green;
            case IshiharaTestActivity.PROTAN:
                return R.string.results_ishihara_protan;
            case IshiharaTestActivity.TOTAL:
                return R.string.results_ishihara_total;
        }
        return R.string.results_ishihara_uncertain;
    }
}
