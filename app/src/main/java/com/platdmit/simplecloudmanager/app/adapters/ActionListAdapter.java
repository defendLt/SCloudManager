package com.platdmit.simplecloudmanager.app.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.domain.models.Action;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActionListAdapter extends RecyclerView.Adapter<ActionListAdapter.ActionListHolder> {

    private List<Action> elementList;

    @NonNull
    @Override
    public ActionListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutType = R.layout.fragment_actions_item;
        return new ActionListHolder(layoutInflater, parent, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionListHolder holder, int position) {
        holder.bindData(elementList.get(position));
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    public void setContentData(List<Action> elements){
        this.elementList = elements;
    }

    class ActionListHolder extends RecyclerView.ViewHolder{

        private TextView mId = itemView.findViewById(R.id.action_id);
        private TextView mStartedAt = itemView.findViewById(R.id.action_start);
        private TextView mCompletedAt = itemView.findViewById(R.id.action_complete);
        private TextView mType = itemView.findViewById(R.id.action_type);
        private TextView mInitiator = itemView.findViewById(R.id.action_initiator);
        private TextView mStatus = itemView.findViewById(R.id.action_status);

        public ActionListHolder(LayoutInflater inflater, ViewGroup parent, int layoutType) {
            super(inflater.inflate(layoutType, parent, false));
        }

        @SuppressLint("SetTextI18n")
        public void bindData(Action data){
            mId.setText(String.valueOf(data.getId()));
            mStartedAt.setText(data.getStartedAt());
            mCompletedAt.setText(" - "+data.getCompletedAt());
            mType.setText(data.getType());
            mInitiator.setText(data.getInitiator());
            mStatus.setText(data.getStatus());
        }
    }
}


