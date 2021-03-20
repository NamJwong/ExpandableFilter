package com.example.expandablefilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.CustomViewHolder> implements Filterable {
    private Context context;
    private ArrayList<ParentItem> unFilteredParentItems;
    private ArrayList<ParentItem> filteredParentItems;
    private LayoutInflater layoutInflater;

    public ParentAdapter(ArrayList<ParentItem> list, Context context) {
        this.context = context;
        this.unFilteredParentItems = list;
        this.filteredParentItems = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ParentAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.parent, parent, false);
        return new ParentAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ParentAdapter.CustomViewHolder holder, int position) {
        holder.recyclerView.setAdapter(new ChildAdapter(context, filteredParentItems.get(position).childItems));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        holder.tvParentName.setText(filteredParentItems.get(position).parentName);
    }

    @Override
    public int getItemCount() {
        return filteredParentItems.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView tvParentName;
        Button btnExpandable;

        public CustomViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.childRecyclerView);
            tvParentName = (TextView) itemView.findViewById(R.id.tvParentName);
            btnExpandable = (Button) itemView.findViewById(R.id.btnExpandable);

            btnExpandable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        ParentItem item = filteredParentItems.get(position);
                        if(item.expandable){
                            recyclerView.setVisibility(View.GONE);
                            item.expandable = false;
                            btnExpandable.setText(">");
                        }else {
                            recyclerView.setVisibility(View.VISIBLE);
                            item.expandable = true;
                            btnExpandable.setText("<");
                        }
                    }
                }
            });
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    filteredParentItems = unFilteredParentItems;
                }else {
                    ArrayList<ParentItem> filteringList = new ArrayList<ParentItem>();
                    for(int i = 0; i < unFilteredParentItems.size(); i++) { //parent for
                        int count = 0;
                        ParentItem temp = new ParentItem();
                        temp.parentName = unFilteredParentItems.get(i).parentName;
                        temp.childItems = new ArrayList<ChildItem>();
                        for(int j = 0; j < unFilteredParentItems.get(i).childItems.size(); j++) { //child for
                            if(unFilteredParentItems.get(i).childItems.get(j).childName.contains(charString)) {
                                temp.childItems.add(unFilteredParentItems.get(i).childItems.get(j));
                                count++;
                            }
                        }
                        if(count != 0) {
                            filteringList.add(temp);
                        }
                    }
                    filteredParentItems = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredParentItems;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredParentItems = (ArrayList<ParentItem>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
