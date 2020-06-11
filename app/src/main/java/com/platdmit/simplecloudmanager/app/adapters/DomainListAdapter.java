package com.platdmit.simplecloudmanager.app.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.domain.models.Domain;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.navigation.Navigation.findNavController;

public class DomainListAdapter extends RecyclerView.Adapter<DomainListAdapter.DomainListHolder> {

    private List<Domain> elementList;

    @NonNull
    @Override
    public DomainListAdapter.DomainListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutType = R.layout.fragment_domains_item;
        return new DomainListHolder(layoutInflater, parent, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull DomainListHolder holder, int position) {
        holder.bindData(elementList.get(position));
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    public void setContentData(List<Domain> elements){
        this.elementList = elements;
    }

    class DomainListHolder extends RecyclerView.ViewHolder{

        private TextView mName = itemView.findViewById(R.id.domain_name);
        private TextView mType = itemView.findViewById(R.id.domain_type);

        public DomainListHolder(LayoutInflater inflater, ViewGroup parent, int layoutType) {
            super(inflater.inflate(layoutType, parent, false));
        }

        public void bindData(Domain data){
            mName.setText(data.getName());
            mType.setText(data.getType());

            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putLong("ELEMENT_ID", data.getId());
                findNavController(v).navigate(R.id.domainFragment, bundle);
            });
        }
    }
}
