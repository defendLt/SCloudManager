package com.platdmit.simplecloudmanager.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.adapters.ActionListAdapter;
import com.platdmit.simplecloudmanager.app.vm.ActionViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.ActionConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.Action;
import com.platdmit.simplecloudmanager.domain.repo.ServerActionsRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ServerTabActionsFragment extends Fragment implements ServerTabFragment<ServerTabActionsFragment>{
    private static final String TAG = ServerTabActionsFragment.class.getSimpleName();
    private String mTitle;

    private ActionViewModel mActionViewModel;
    private RecyclerView mActionRecyclerView;
    private ActionListAdapter mActionListAdapter;

    public ServerTabActionsFragment(){};

    public ServerTabActionsFragment(String title) {
        mTitle = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mActionViewModel = new ViewModelProvider(this).get(ActionViewModel.class);
        } else {
            mActionViewModel = new ViewModelProvider(this,
                    new SingleElementViewModelFactory<ServerActionsRepo>(
                            new ServerRepoImp(
                                    new ApiServerRepoImp(SCMApp.getActualApiKeyService()),
                                    SCMApp.getDb(),
                                    new ActionConvertImp(),
                                    new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), ServerActionsRepo.class, getArguments().getLong("ELEMENT_ID")
                    )).get(ActionViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_server_tab_actions, container, false);

        mActionRecyclerView = v.findViewById(R.id.server_actions_list);
        mActionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mActionListAdapter = new ActionListAdapter();

        mActionViewModel.getActionsLiveData().observe(getViewLifecycleOwner(), this::updateServerActionData);

        return v;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public ServerTabActionsFragment getInstance() {
        return new ServerTabActionsFragment();
    }

    private void updateServerActionData(List<Action> actions){
        mActionListAdapter.setContentData(actions);
        if(mActionRecyclerView.getAdapter() == null){
            mActionRecyclerView.setAdapter(mActionListAdapter);
        } else {
            mActionListAdapter.notifyDataSetChanged();
        }
    }
}
