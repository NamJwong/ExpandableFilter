package com.example.expandablefilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<ChildItem> childItems;
    private LayoutInflater inflater;

    public ChildAdapter(Context context, ArrayList<ChildItem> childItems) {
        this.context = context;
        this.childItems = childItems;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ChildAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { ;
        View view;
        view = inflater.inflate(R.layout.child, parent, false);
        return new ChildAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChildAdapter.CustomViewHolder holder, int position) {
        ChildItem childItem = childItems.get(position);
        holder.tvChildName.setText(childItem.childName);
    }

    @Override
    public int getItemCount() {
        return childItems.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView tvChildName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            tvChildName = (TextView) itemView.findViewById(R.id.tvChildName);
        }
    }
}
