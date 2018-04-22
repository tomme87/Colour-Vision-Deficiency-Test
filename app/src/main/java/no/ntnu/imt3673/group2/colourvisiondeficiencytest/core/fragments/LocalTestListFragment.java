package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.GsonRequest;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.MainActivity;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.adapters.TestListAdapter;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.GetAllLocalTests;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalTestListFragment extends Fragment {

    private static final String TAG = "LcTestListFrag";
    private RecyclerView recyclerView;
    private TestListAdapter testListAdapter;
    MainActivity mainActivity;

    private GsonRequest<TestInfo[]> request;



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

        // Downloead button and Change into StartDownloadFragment
        FloatingActionButton floatingActionButton = getView().findViewById(R.id.fab_open_download_list);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.startDownloadFragment();
            }
        });


        //Populate RecycleView
        this.recyclerView = getView().findViewById(R.id.rv_local_test_list);
        this.testListAdapter = new TestListAdapter(getContext(), mainActivity.getLocalTestInfos());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Divider between elements: https://stackoverflow.com/a/27037230
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        this.recyclerView.setAdapter(testListAdapter);
        this.recyclerView.addItemDecoration(dividerItemDecoration);
        this.recyclerView.setLayoutManager(layoutManager);

        //OBS OBS OBS!!!
        this.recyclerView.addOnItemTouchListener( new RecyclerTouchListener(getContext()));
        Log.d(TAG, "View created");

        //
        new GetAllLocalTests(getContext()) {
            @Override
            protected void onPostExecute(List<TestInfo> testInfos) {
                testListAdapter.setTestInfos(testInfos.toArray(new TestInfo[0]));
            }
        }.execute();
    }


    /*

     @Override
     protected List<EntryEntity> doInBackground(EntriesListViewAdapter... entriesListViewAdapters) {
        this.adapter = entriesListViewAdapters[0];
        AppDatabase.getAppDatabase(this.appContext).entryEntityDAO().delete();
        List<EntryEntity> localTestList = AppDatabase.getAppDatabase(this.appContext).entryEntityDAO().GetAllLocalTests();
        return localTestList;
    }
     */

    private void showTestWelcomeFragment(TestInfo testInfo) {
        mainActivity.startWelcomeFragment(testInfo);
    }

    private class RecyclerTouchListener extends RecyclerView.SimpleOnItemTouchListener {
        private GestureDetector gestureDetector;

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





}
