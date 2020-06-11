package com.platdmit.simplecloudmanager.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.adapters.DomainRecordListAdapter;
import com.platdmit.simplecloudmanager.app.vm.DomainViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiDomainRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.DomainConvertImp;
import com.platdmit.simplecloudmanager.domain.converters.implement.DomainRecordConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.Domain;
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.DomainRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DomainFragment extends Fragment {
    private static final String TAG = DomainFragment.class.getSimpleName();

    private DomainViewModel mDomainViewModel;
    private RecyclerView mRecyclerView;
    private DomainRecordListAdapter mDomainRecordListAdapter;

    private TextView mName;
    private TextView mType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mDomainViewModel = new ViewModelProvider(this).get(DomainViewModel.class);
        } else {
            mDomainViewModel = new ViewModelProvider(this,
                    new SingleElementViewModelFactory<DomainBaseRepo>(
                            new DomainRepoImp(
                                    new ApiDomainRepoImp(SCMApp.getActualApiKeyService()), SCMApp.getDb(),
                                    new DomainConvertImp(), new DomainRecordConvertImp(),
                                    new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), DomainBaseRepo.class, getArguments().getLong("ELEMENT_ID")
                    )).get(DomainViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_domain, container, false);

        mRecyclerView = v.findViewById(R.id.domain_record_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDomainRecordListAdapter = new DomainRecordListAdapter();

        mName = v.findViewById(R.id.domain_name);
        mType = v.findViewById(R.id.domain_type);

        mDomainViewModel.getDomainLiveData().observe(getViewLifecycleOwner(), this::initData);

        return v;
    }

    private void initData(Domain domain){
        mName.setText(domain.getName());
        mType.setText(domain.getType());
        mDomainRecordListAdapter.setContentData(domain.getDomainRecords());
        if(mRecyclerView.getAdapter() == null){
            mRecyclerView.setAdapter(mDomainRecordListAdapter);
        } else {
            mDomainRecordListAdapter.notifyDataSetChanged();
        }
    }
}
