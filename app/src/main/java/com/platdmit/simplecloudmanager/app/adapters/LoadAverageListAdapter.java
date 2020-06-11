package com.platdmit.simplecloudmanager.app.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.domain.models.LoadAverage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoadAverageListAdapter extends RecyclerView.Adapter<LoadAverageListAdapter.LoadAverageListHolder> {

    private List<LoadAverage> elementList;

    @NonNull
    @Override
    public LoadAverageListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutType = R.layout.fragment_loadaverage_item;
        return new LoadAverageListHolder(layoutInflater, parent, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadAverageListHolder holder, int position) {
        holder.bindData(elementList.get(position));
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    public void setContentData(List<LoadAverage> elements){
        this.elementList = elements;
    }

    class LoadAverageListHolder extends RecyclerView.ViewHolder{

        private TextView mTitle = itemView.findViewById(R.id.title);
        private TextView mSize = itemView.findViewById(R.id.size);
        private TextView mValue = itemView.findViewById(R.id.value);
        private ProgressBar mPercent = itemView.findViewById(R.id.progress);

        public LoadAverageListHolder(LayoutInflater inflater, ViewGroup parent, int layoutType) {
            super(inflater.inflate(layoutType, parent, false));
        }

        public void bindData(LoadAverage data){
            mTitle.setText(data.getName());
            mSize.setText(data.getTotal());
            mValue.setText(data.getValue());
            mPercent.setProgress((int)data.getPercent());
        }
    }
}


