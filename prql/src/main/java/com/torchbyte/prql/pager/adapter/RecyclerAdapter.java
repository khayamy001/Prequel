package com.torchbyte.prql.pager.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.torchbyte.prql.pager.R;
import com.torchbyte.prql.pager.model.Data;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private List<Data> mData = Collections.emptyList();

    public RecyclerAdapter(List<Data> data) {
        this.mData = data;
    }

    public void updateList(List<Data> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.title.setText(mData.get(position).title);
        viewHolder.icon.setImageResource(mData.get(position).thumbnailIconUrl);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView icon;

        public RecyclerViewHolder(View v) {
            super(v);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}