package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.GsonGetRequest;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.OnGetActivityDataListener;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.adapters.TestListAdapter;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * This fragment shows a list of available test that
 * have not been downloaded yet.
 */
public class DownloadTestListFragment extends Fragment {

    private static final String TAG = "DlTestListFrag";
    private TestListAdapter testListAdapter;

    private ProgressBar pbLoadingDlList;

    private GsonGetRequest<TestInfo[]> request;
    private RequestQueue queue;

    //private MainActivity mainActivity;
    private OnGetActivityDataListener callback;

    public DownloadTestListFragment() {
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

        getActivity().setTitle(R.string.app_name_available_fragment);

        this.queue = Volley.newRequestQueue(getContext());


        //Get Available Tests.
        request = new GsonGetRequest<>(getString(R.string.url_test_list), TestInfo[].class, null, new Response.Listener<TestInfo[]>() {
            @Override
            public void onResponse(TestInfo[] response) {
                Log.d(TAG, "Got response! " + response.length);
                testListAdapter.setTestInfos(updateAvailableTestList(response));
                pbLoadingDlList.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error downloading list: " + error.getMessage());
                pbLoadingDlList.setVisibility(View.GONE);
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download_test_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        pbLoadingDlList = view.findViewById(R.id.pb_loading_dl_list);
        pbLoadingDlList.setVisibility(View.GONE);

        RecyclerView recyclerView = view.findViewById(R.id.rv_download_test_list);
        this.testListAdapter = new TestListAdapter(getContext(), callback.getDownloadableTestInfos());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Divider between elements: https://stackoverflow.com/a/27037230
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.setAdapter(testListAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext()));

        Log.d(TAG, "View created");
    }

    @Override
    public void onStart() {
        super.onStart();
        getNewList();
    }


    /**
     * Fetchs a new test list from the server
     */
    private void getNewList() {
        pbLoadingDlList.setVisibility(View.VISIBLE);
        this.queue.add(this.request);
    }

    /**
     * Shows a fragment with detailed information of a test
     *
     * @param testInfo Information Object about a test
     */
    private void showDownloadInfoFragment(TestInfo testInfo) {
        this.callback.startDownloadInfoFragment(testInfo);
    }

    /**
     * Gets a list with available test that have not been downloaded yet
     *
     * @param response Array with test info objects.
     * @return Array with test info objects.
     */
    private TestInfo[] updateAvailableTestList(TestInfo[] response) {
        //Get Local Tests from DB
        List<TestInfo> localTestsList = this.callback.getLocalTestInfos();

        TestInfo[] notDownloadedTests = new TestInfo[response.length - localTestsList.size()];

        Log.d("UpdateTestList", "Response size: " + response.length + " LocalListTest Size: " + localTestsList.size() + " NotDownloaded Size: " + notDownloadedTests.length);
        boolean downloaded = false;
        int count = 0;

        for (int i = 0; i < response.length; i++) {

            for (int j = 0; j < localTestsList.size(); j++) {

                if (response[i].getId().equals(localTestsList.get(j).getId())) {
                    Log.d("UpdateTestList", "Equal Test Response: " + i + "Equal Test Local:" + j);
                    downloaded = true;
                }
            }

            if (!downloaded) {
                Log.d("UpdateTestList", "Count: " + count);
                notDownloadedTests[count] = response[i];
                count++;
            }
            downloaded = false;

        }

        return notDownloadedTests;
    }

    /**
     * Handles clicks on the available test list.
     */
    private class RecyclerTouchListener extends RecyclerView.SimpleOnItemTouchListener {

        private final GestureDetector gestureDetector;

        RecyclerTouchListener(Context context) {
            this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && gestureDetector.onTouchEvent(e)) {
                showDownloadInfoFragment(testListAdapter.getTestInfo(rv.getChildAdapterPosition(child)));
            }

            return false;
        }

    }
}
