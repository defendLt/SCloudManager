package com.platdmit.simplecloudmanager.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.adapters.DomainListAdapter;
import com.platdmit.simplecloudmanager.app.vm.DomainListViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.ListElementsViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiDomainRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.DomainConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.Domain;
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.DomainRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class DomainListFragment extends Fragment {
    private static final String TAG = DomainFragment.class.getSimpleName();

    private DomainListViewModel mDomainListViewModel;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DomainListAdapter mDomainListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mDomainListViewModel = new ViewModelProvider(this).get(DomainListViewModel.class);
        } else {
            mDomainListViewModel = new ViewModelProvider(this,
                    new ListElementsViewModelFactory<DomainBaseRepo>(
                            new DomainRepoImp(
                                    new ApiDomainRepoImp(SCMApp.getActualApiKeyService()), SCMApp.getDb(),
                                    new DomainConvertImp(), new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), DomainBaseRepo.class
                    )).get(DomainListViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_domains_list, container, false);

        mRecyclerView = v.findViewById(R.id.fragments_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDomainListAdapter = new DomainListAdapter();

        mSwipeRefreshLayout = v.findViewById(R.id.update_swipe);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mDomainListViewModel.reloadDomainList());

        mDomainListViewModel.getDomainsLiveData().observe(getViewLifecycleOwner(), this::updateAdapterData);
        mDomainListViewModel.getResultMassage().observe(getViewLifecycleOwner(), this::showResultMassage);

        return v;
    }

    private void showResultMassage(String massage){
        Snackbar.make(getView(), massage, Snackbar.LENGTH_SHORT).show();
    }

    private void updateAdapterData(List<Domain> domains){
        mDomainListAdapter.setContentData(domains);
        mSwipeRefreshLayout.setRefreshing(false);
        if(mRecyclerView.getAdapter() == null){
            mRecyclerView.setAdapter(mDomainListAdapter);
        } else {
            mDomainListAdapter.notifyDataSetChanged();
        }
    }

}
