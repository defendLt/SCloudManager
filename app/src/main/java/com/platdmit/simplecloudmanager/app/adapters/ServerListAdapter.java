package com.platdmit.simplecloudmanager.app.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.domain.models.Server;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.navigation.Navigation.findNavController;

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ServerListHolder> {

    private List<Server> elementList;

    @NonNull
    @Override
    public ServerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        int layoutType = R.layout.fragment_servers_item;
        return new ServerListHolder(layoutInflater, parent, layoutType);
    }

    @Override
    public void onBindViewHolder(@NonNull ServerListHolder holder, int position) {
        holder.bindData(elementList.get(position));
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    public void setContentData(List<Server> elements){
        this.elementList = elements;
    }

    class ServerListHolder extends RecyclerView.ViewHolder{

        private ImageView mImageLogo = itemView.findViewById(R.id.server_image_logo);

        private TextView mName = itemView.findViewById(R.id.server_name);;
        private TextView mUptime = itemView.findViewById(R.id.server_uptime);
        private TextView mId = itemView.findViewById(R.id.server_id);
        private TextView mStatus = itemView.findViewById(R.id.server_status);

        public ServerListHolder(LayoutInflater inflater, ViewGroup parent, int layoutType) {
            super(inflater.inflate(layoutType, parent, false));
        }

        public void bindData(Server data){
            mName.setText(data.getName());
            mUptime.setText(data.getUptime());
            mId.setText(String.valueOf(data.getId()));
            mStatus.setText(data.getStatus());

            itemView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putLong("ELEMENT_ID", data.getId());
                findNavController(v).navigate(R.id.serverFragment, bundle);
            });
        }
    }
}