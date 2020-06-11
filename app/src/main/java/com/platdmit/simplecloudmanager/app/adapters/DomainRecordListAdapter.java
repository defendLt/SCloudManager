package com.platdmit.simplecloudmanager.app.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.domain.models.DomainRecord;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DomainRecordListAdapter extends RecyclerView.Adapter<DomainRecordListAdapter.DomainRecordListHolder> {

    private List<DomainRecord> elementList;

    @NonNull
    @Override
    public DomainRecordListAdapter.DomainRecordListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutType = R.layout.fragment_drecord_list_item;
        return new DomainRecordListHolder(layoutInflater, parent, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull DomainRecordListHolder holder, int position) {
        holder.bindData(elementList.get(position));
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    public void setContentData(List<DomainRecord> elements){
        this.elementList = elements;
    }

    class DomainRecordListHolder extends RecyclerView.ViewHolder{

        private TextView mHost = itemView.findViewById(R.id.record_host);
        private TextView mType = itemView.findViewById(R.id.record_type);
        private TextView mTtl = itemView.findViewById(R.id.record_ttl);
        private TextView mPriority = itemView.findViewById(R.id.record_priority);
        private TextView mData = itemView.findViewById(R.id.record_data);

        public DomainRecordListHolder(LayoutInflater inflater, ViewGroup parent, int layoutType) {
            super(inflater.inflate(layoutType, parent, false));
        }

        public void bindData(DomainRecord data){
            mHost.setText(data.getName());
            mType.setText(data.getType());
            mData.setText(data.getData());
            mPriority.setText(data.getPriority());
            mTtl.setText(data.getTtl());
        }
    }
}
