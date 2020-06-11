package com.platdmit.simplecloudmanager.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.adapters.ServerListAdapter;
import com.platdmit.simplecloudmanager.app.vm.ServerListViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.ListElementsViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.ServerConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.Server;
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServerListFragment extends Fragment {
    private static final String TAG = ServerListFragment.class.getSimpleName();

    private ServerListViewModel mServerListViewModel;
    private RecyclerView mRecyclerView;
    private ServerListAdapter mServerListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mServerListViewModel = new ViewModelProvider(this).get(ServerListViewModel.class);
        } else {
            mServerListViewModel = new ViewModelProvider(this,
                    new ListElementsViewModelFactory<ServerBaseRepo>(
                            new ServerRepoImp(
                                    new ApiServerRepoImp(SCMApp.getActualApiKeyService()),
                                    SCMApp.getDb(),
                                    new ServerConvertImp(),
                                    new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), ServerBaseRepo.class
                    )).get(ServerListViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_servers_list, container, false);

        mRecyclerView = v.findViewById(R.id.fragments_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mServerListAdapter = new ServerListAdapter();

        mServerListViewModel.getServersLiveData().observe(getViewLifecycleOwner(), this::updateAdapterData);
        mServerListViewModel.getResultMassage().observe(getViewLifecycleOwner(), this::showResultMassage);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_data:
                mServerListViewModel.reloadServerList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showResultMassage(String massage){
        Snackbar.make(getView(), massage, Snackbar.LENGTH_SHORT).show();
    }

    private void updateAdapterData(List<Server> servers){
        mServerListAdapter.setContentData(servers);
        if(mRecyclerView.getAdapter() == null){
            mRecyclerView.setAdapter(mServerListAdapter);
        } else {
            mServerListAdapter.notifyDataSetChanged();
        }
    }
}
