package no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import no.ntnu.imt3673.group2.colourvisiondeficiencytest.R;
import no.ntnu.imt3673.group2.colourvisiondeficiencytest.core.TestInfo;

/**
 * Adapter for the local tests list and downloadable tests list.
 */
public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.TestListViewHolder> {
    private final LayoutInflater inflater;
    private final List<TestInfo> testInfos;

    /**
     *
     * @param context the context to inflate from
     * @param testInfos TestInfo list to use for this adapter elements.
     */
    public TestListAdapter(Context context, List<TestInfo> testInfos) {
        this.inflater = LayoutInflater.from(context);
        this.testInfos = testInfos;
    }

    /**
     * Add new data to the testInfo object.
     * @param testInfos array of TestInfo object to be added.
     */
    public void setTestInfos(TestInfo[] testInfos) {
        this.testInfos.clear();
        this.testInfos.addAll(Arrays.asList(testInfos));
        notifyDataSetChanged();
    }

    /**
     * Get TestInfo from the testInfos list.
     * @param position at what position is the testInfo object.
     * @return The requested TestInfo object.
     */
    public TestInfo getTestInfo(int position) {
        return this.testInfos.get(position);
    }

    @Override
    public TestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.list_item_test, parent, false);
        return new TestListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TestListViewHolder holder, int position) {
        TestInfo testInfo = testInfos.get(position);
        holder.name.setText(testInfo.getName());
        holder.type.setText(testInfo.getType());

        switch (testInfo.getType()){
            case "Ishihara":
                holder.icon.setImageResource(R.drawable.ishihara_type_icon);
        }

    }

    @Override
    public int getItemCount() {
        return testInfos.size();
    }

    /**
     * The holder for the View, which contains the name, type and icon for each element in the list.
     */
    static class TestListViewHolder extends RecyclerView.ViewHolder {

        final TextView name;
        final TextView type;
        final ImageView icon;


        public TestListViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_test_item_name);
            type = itemView.findViewById(R.id.tv_test_item_type);
            icon = itemView.findViewById(R.id.iv_test_icon);

        }
    }
}
