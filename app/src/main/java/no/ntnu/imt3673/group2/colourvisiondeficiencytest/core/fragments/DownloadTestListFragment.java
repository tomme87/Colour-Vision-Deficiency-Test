package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.GsonRequest;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.MainActivity;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.adapters.TestListAdapter;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.database.GetAllLocalTests;


/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadTestListFragment extends Fragment {

    private static final String TAG = "DlTestListFrag";
    private RecyclerView recyclerView;
    private TestListAdapter testListAdapter;

    private GsonRequest<TestInfo[]> request;
    private RequestQueue queue;

    private MainActivity mainActivity;

    public DownloadTestListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.mainActivity = (MainActivity) getActivity();

        this.queue = Volley.newRequestQueue(getContext());


        //Get Available Tests.
        request = new GsonRequest<>(getString(R.string.url_test_list), TestInfo[].class, null, new Response.Listener<TestInfo[]>() {
            @Override
            public void onResponse(TestInfo[] response) {
                Log.d(TAG, "Got response! " + response.length);
                testListAdapter.setTestInfos(updateAvailableTestList(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error downloading list: " + error.getMessage());
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download_test_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.recyclerView = getView().findViewById(R.id.rv_download_test_list);
        this.testListAdapter = new TestListAdapter(getContext(), mainActivity.getDownloadableTestInfos());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        // Divider between elements: https://stackoverflow.com/a/27037230
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());

        this.recyclerView.setAdapter(testListAdapter);
        this.recyclerView.addItemDecoration(dividerItemDecoration);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext()));

        Log.d(TAG, "View created");
    }

    @Override
    public void onStart() {
        super.onStart();
        getNewList();
    }

    

    private void getNewList() {
        this.queue.add(this.request);
    }

    private void showDownloadInfoFragment(TestInfo testInfo) {
        mainActivity.startDownloadInfoFragment(testInfo);
    }

    public TestInfo[] updateAvailableTestList(TestInfo[] response){
        //Get Local Tests from DB
        List <TestInfo> localTestsList=mainActivity.getLocalTestInfos();

        TestInfo[] notDownloadedTests = new TestInfo[response.length-localTestsList.size()];

        Log.d("UpdateTestList","Response size: "+ response.length+ " LocalListTest Size: " +localTestsList.size() + " NotDownloaded Size: " + notDownloadedTests.length );
        boolean downloaded = false;
        int count=0;

        for (int i=0; i < response.length; i++) {

            for( int j=0; j < localTestsList.size() ; j++){

                if(response[i].getId().equals(localTestsList.get(j).getId())){
                    Log.d("UpdateTestList", "Equal Test Response: " +i+ "Equal Test Local:" +j);
                    downloaded=true;
                }
            }

            if(downloaded==false) {
                Log.d("UpdateTestList", "Count: "+count );
                notDownloadedTests[count]=response[i];
                count++;
            }
            downloaded = false;

        }

        return notDownloadedTests;
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
                showDownloadInfoFragment(testListAdapter.getTestInfo(rv.getChildAdapterPosition(child)));
            }

            return false;
        }

    }
}
