package no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.GsonPostRequest;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaPlate;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaTestActivity;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaThreshold;

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

    RequestQueue queue;


    public IshiharaTestResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.activity = (IshiharaTestActivity) getActivity();
        this.testInfo = this.activity.getTestInfo();

        this.queue = Volley.newRequestQueue(getContext());

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
                sendResults();
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

    private void sendResults() {
        Gson gson = new Gson();


        String payload = gson.toJson(this.activity.getResults());
        Log.d(TAG, "Payload: " + payload);

        GsonPostRequest<String> request = new GsonPostRequest<>(this.testInfo.getResourceUrl(), payload,
                String.class, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response sent, got" + response);
                Toast.makeText(getContext(), R.string.response_testresult_sent, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Unable to send response : " + error.getMessage());
                Toast.makeText(getContext(), R.string.response_testresult_sent_error, Toast.LENGTH_LONG).show();
            }
        });

        this.queue.add(request);

    }

    private int formatResults() {
        int resultsNormal = this.activity.getCounters()[IshiharaTestActivity.NORMAL];
        IshiharaThreshold ishiharaThreshold = this.activity.getIshiharaThreshold();

        if (resultsNormal > ishiharaThreshold.getNormal()) {
            return R.string.results_ishihara_normal;
        }
        if (resultsNormal < ishiharaThreshold.getDeficiency()) {
            if (this.activity.getCounters()[IshiharaTestActivity.DEUTAN] >
                    this.activity.getCounters()[IshiharaTestActivity.PROTAN]) {
                return R.string.results_ishihara_deutan;
            }
            if (this.activity.getCounters()[IshiharaTestActivity.DEUTAN] <
                    this.activity.getCounters()[IshiharaTestActivity.PROTAN]) {
                return R.string.results_ishihara_protan;
            }
            return R.string.results_ishihara_general_red_green;
        }

        return R.string.results_ishihara_uncertain;

    }
}
