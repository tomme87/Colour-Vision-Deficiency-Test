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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.GsonPostRequest;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.ResultSet;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaCalculateResults;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaResult;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaTestActivity;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.ishihara.IshiharaThreshold;

/**
 * A simple {@link Fragment} subclass.
 */
public class IshiharaTestResultsFragment extends Fragment {

    private static final String TAG = "IshiharaTestResults";
    TestInfo testInfo;

    Button btnSend;
    Button btnExit;
    TextView tvTitle;
    TextView tvResults;

    RequestQueue queue;

    OnGetActivityDataListener callback;


    public IshiharaTestResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        try {
            this.callback = (OnGetActivityDataListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnGetActivityDataListener");
        }

        this.testInfo = this.callback.getTestInfo();

        this.queue = Volley.newRequestQueue(getContext());

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ishihara_test_results, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.btnSend = view.findViewById(R.id.btn_send_and_exit);
        this.btnExit = view.findViewById(R.id.btn_exit);
        this.tvTitle = view.findViewById(R.id.tv_test_result_title);
        this.tvResults = view.findViewById(R.id.tv_result_summary);

        this.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "sending results");
                sendResultsAndFinish();
            }
        });

        this.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "exiting");
                getActivity().finish();
            }
        });

        String titleString = testInfo.getName() + " " + getResources().getString(R.string.tv_test_result_title_stub);
        this.tvTitle.setText(titleString);
        this.tvResults.setText(formatResults());

        super.onViewCreated(view, savedInstanceState);
    }

    private void sendResultsAndFinish() {
        Gson gson = new Gson();

        String payload = gson.toJson(this.callback.getResultSet());
        Log.d(TAG, "Payload: " + payload);

        GsonPostRequest<String> request = new GsonPostRequest<>(this.testInfo.getResourceUrl(), payload,
                String.class, null, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response sent, got" + response);
                Toast.makeText(getContext(), R.string.response_testresult_sent, Toast.LENGTH_LONG).show();
                Log.d(TAG, "exiting");
                getActivity().finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Unable to send response : " + error.getMessage());
                Toast.makeText(getContext(), R.string.response_testresult_sent_error, Toast.LENGTH_LONG).show();
                Log.d(TAG, "exiting");
                getActivity().finish();
            }
        });

        this.queue.add(request);

    }

    private int formatResults() {
        int result = IshiharaCalculateResults.getResult(this.callback.getIshiharaThreshold(),
                this.callback.getResultSet().getResults());

        switch (result) {
            case IshiharaCalculateResults.NORMAL:
                return R.string.results_ishihara_normal;
            case IshiharaCalculateResults.GENERAL_READ_GREEN:
                return R.string.results_ishihara_general_red_green;
            case IshiharaCalculateResults.DEUTAN:
                return R.string.results_ishihara_deutan;
            case IshiharaCalculateResults.PROTAN:
                return R.string.results_ishihara_protan;
            case IshiharaCalculateResults.UNCERTAIN:
                return R.string.results_ishihara_uncertain;
            default:
                return R.string.results_ishihara_uncertain;
        }
    }

    /**
     * Interface for communtication with activity.
     */
    public interface OnGetActivityDataListener {
        IshiharaThreshold getIshiharaThreshold();

        TestInfo getTestInfo();

        ResultSet<IshiharaResult> getResultSet();
    }
}
