package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.OnGetActivityDataListener;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.adapters.TestListAdapter;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.GetAllLocalTests;


/**
 * A simple {@link Fragment} subclass.
 * This fragment shows a list with the test
 * that have been already downloaded.
 */
public class LocalTestListFragment extends Fragment {

    private static final String TAG = "LcTestListFrag";
    private TestListAdapter testListAdapter;

    private DownloadProcessedReceiver downloadProcessedReceiver;

    private OnGetActivityDataListener callback;

    public LocalTestListFragment() {
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

        getActivity().setTitle(R.string.app_name_local_fragment);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local_test_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Downloead button and Change into StartDownloadFragment
        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_open_download_list);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.startDownloadFragment();
            }
        });


        //Populate RecycleView
        RecyclerView recyclerView = view.findViewById(R.id.rv_local_test_list);
        this.testListAdapter = new TestListAdapter(getContext(), this.callback.getLocalTestInfos());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Divider between elements: https://stackoverflow.com/a/27037230
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        recyclerView.setAdapter(testListAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);

        //OBS OBS OBS!!!
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext()));
        Log.d(TAG, "View created");

        this.downloadProcessedReceiver = new DownloadProcessedReceiver();
        getActivity().registerReceiver(this.downloadProcessedReceiver, new IntentFilter(DownloadProcessedReceiver.ACTION_RESP));

        //
        this.updateLocalList();
    }


    /**
     * Update the list with the test that have been already downloaded.
     */
    public void updateLocalList() {
        //Get Local Tests from DB
        new GetAllLocalTests(getContext()) {
            @Override
            protected void onPostExecute(List<TestInfo> testInfos) {
                testListAdapter.setTestInfos(testInfos.toArray(new TestInfo[0]));
            }
        }.execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "Destroyed");
        getActivity().unregisterReceiver(this.downloadProcessedReceiver);
    }

    /**
     * This method shows the welcome fragment when running a test
     *
     * @param testInfo Object that contains the details about a test.
     */
    private void showTestWelcomeFragment(TestInfo testInfo) {
        this.callback.startWelcomeFragment(testInfo);
    }

    /**
     * Handles clicks on the test local test list
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
                showTestWelcomeFragment(testListAdapter.getTestInfo(rv.getChildAdapterPosition(child)));
            }

            return false;
        }
    }


    /**
     * Update the list that shows the test that have been downloaded already
     */
    public class DownloadProcessedReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "DlProcessedReciver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Processed done received");
            updateLocalList();
        }
    }
}
