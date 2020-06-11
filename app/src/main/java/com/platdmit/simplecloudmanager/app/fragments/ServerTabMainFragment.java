package com.platdmit.simplecloudmanager.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.adapters.LoadAverageListAdapter;
import com.platdmit.simplecloudmanager.app.vm.LoadAverageViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.LoadAverageConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.LoadAverage;
import com.platdmit.simplecloudmanager.domain.repo.ServerLoadAveragesRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServerTabMainFragment extends Fragment implements ServerTabFragment<ServerTabMainFragment>{
    private static final String TAG = ServerTabMainFragment.class.getSimpleName();
    private String mTitle;

    private LoadAverageViewModel mLoadAverageViewModel;
    private RecyclerView mLoadAverageRecyclerView;
    private LoadAverageListAdapter mLoadAverageListAdapter;

    public ServerTabMainFragment(){};

    public ServerTabMainFragment(String title) {
        mTitle = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mLoadAverageViewModel = new ViewModelProvider(this).get(LoadAverageViewModel.class);
        } else {
            mLoadAverageViewModel = new ViewModelProvider(this,
                    new SingleElementViewModelFactory<ServerLoadAveragesRepo>(
                            new ServerRepoImp(
                                    new ApiServerRepoImp(SCMApp.getActualApiKeyService()),
                                    SCMApp.getDb(),
                                    new LoadAverageConvertImp(),
                                    new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), ServerLoadAveragesRepo.class, getArguments().getLong("ELEMENT_ID")
                    )).get(LoadAverageViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_server_tab_main, container, false);

        mLoadAverageRecyclerView = v.findViewById(R.id.server_load_average);
        mLoadAverageRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mLoadAverageListAdapter = new LoadAverageListAdapter();

        mLoadAverageViewModel.getLoadAveragesLiveData().observe(getViewLifecycleOwner(), this::updateServerActionData);

        return v;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public ServerTabMainFragment getInstance() {
        return new ServerTabMainFragment();
    }

    private void updateServerActionData(List<LoadAverage> actions){
        mLoadAverageListAdapter.setContentData(actions);
        if(mLoadAverageRecyclerView.getAdapter() == null){
            mLoadAverageRecyclerView.setAdapter(mLoadAverageListAdapter);
        } else {
            mLoadAverageListAdapter.notifyDataSetChanged();
        }
    }
}
