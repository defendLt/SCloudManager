package com.platdmit.simplecloudmanager.app.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.domain.models.Backup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BackupListAdapter extends RecyclerView.Adapter<BackupListAdapter.BackupListHolder> {
    private List<Backup> elementList;

    @NonNull
    @Override
    public BackupListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutType = R.layout.fragment_backup_item;
        return new BackupListHolder(layoutInflater, parent, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull BackupListHolder holder, int position) {
        holder.bindData(elementList.get(position));
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    public void setContentData(List<Backup> elements){
        this.elementList = elements;
    }

    class BackupListHolder extends RecyclerView.ViewHolder{

        private TextView mId = itemView.findViewById(R.id.backup_id);
        private TextView mDate = itemView.findViewById(R.id.backup_date);
        private TextView mOs = itemView.findViewById(R.id.backup_oc);
        private TextView mSize = itemView.findViewById(R.id.backup_size);
        private TextView mPrice = itemView.findViewById(R.id.backup_price);

        public BackupListHolder(LayoutInflater inflater, ViewGroup parent, int layoutType) {
            super(inflater.inflate(layoutType, parent, false));
        }

        public void bindData(Backup data){
            mId.setText(String.valueOf(data.getId()));
            mDate.setText(data.getCreatedAt());
            mOs.setText(data.getDistribution());
            mSize.setText(data.getMinDiskSize());
            mPrice.setText(data.getPriceHourly());
        }
    }
}
