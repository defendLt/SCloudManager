package com.platdmit.simplecloudmanager.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.adapters.BackupListAdapter;
import com.platdmit.simplecloudmanager.app.vm.BackupsViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.BackupConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.Backup;
import com.platdmit.simplecloudmanager.domain.repo.ServerBackupRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServerTabBackupsFragment extends Fragment implements ServerTabFragment<ServerTabBackupsFragment>{
    private static final String TAG = ServerTabBackupsFragment.class.getSimpleName();
    private String mTitle;

    private BackupsViewModel mBackupsViewModel;
    private RecyclerView mRecyclerView;
    private BackupListAdapter mAdapter;

    public ServerTabBackupsFragment(){};

    public ServerTabBackupsFragment(String title) {
        mTitle = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mBackupsViewModel = new ViewModelProvider(this).get(BackupsViewModel.class);
        } else {
            mBackupsViewModel = new ViewModelProvider(this,
                    new SingleElementViewModelFactory<ServerBackupRepo>(
                            new ServerRepoImp(
                                    new ApiServerRepoImp(SCMApp.getActualApiKeyService()),
                                    SCMApp.getDb(),
                                    new BackupConvertImp(),
                                    new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), ServerBackupRepo.class, getArguments().getLong("ELEMENT_ID")
                    )).get(BackupsViewModel.class);
        }
        
        View v = inflater.inflate(R.layout.fragment_server_tab_backups, container, false);

        mRecyclerView = v.findViewById(R.id.server_backup_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new BackupListAdapter();

        mBackupsViewModel.getBackupsLiveData().observe(getViewLifecycleOwner(), this::updateServerActionData);

        return v;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public ServerTabBackupsFragment getInstance() {
        return new ServerTabBackupsFragment();
    }

    private void updateServerActionData(List<Backup> backups){
        mAdapter.setContentData(backups);
        if(mRecyclerView.getAdapter() == null){
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
