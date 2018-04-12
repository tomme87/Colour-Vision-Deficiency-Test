package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;

/**
 * Created by Tomme on 12.04.2018.
 */

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.TestListViewHolder> {
    private final LayoutInflater inflater;

    TestListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public TestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.list_item_test, parent, false);
        return new TestListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class TestListViewHolder extends RecyclerView.ViewHolder {

        public TestListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
