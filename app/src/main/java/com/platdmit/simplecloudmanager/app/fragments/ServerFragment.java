package com.platdmit.simplecloudmanager.app.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.adapters.ServerViewStateAdapter;
import com.platdmit.simplecloudmanager.app.vm.ServerViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.ServerConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.Server;
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class ServerFragment extends Fragment {
    private static final String TAG = ServerFragment.class.getSimpleName();

    private ServerViewModel mServerViewModel;

    private ImageView mServerImageLogo;
    private TextView mServerImageName;
    private TextView mServerIp;
    private TextView mServerStatus;
    private TextView mServerId;
    private TextView mServerPrice;
    private TextView mServerPaymentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mServerViewModel = new ViewModelProvider(this).get(ServerViewModel.class);
        } else {
            mServerViewModel = new ViewModelProvider(this,
                    new SingleElementViewModelFactory<ServerBaseRepo>(
                            new ServerRepoImp(
                                    new ApiServerRepoImp(SCMApp.getActualApiKeyService()),
                                    SCMApp.getDb(),
                                    new ServerConvertImp(),
                                    new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), ServerBaseRepo.class, getArguments().getLong("ELEMENT_ID")
                    )).get(ServerViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_server, container, false);

        mServerImageLogo = v.findViewById(R.id.server_image_logo);
        mServerImageName = v.findViewById(R.id.server_image_name);
        mServerIp = v.findViewById(R.id.server_ip);
        mServerStatus = v.findViewById(R.id.server_status);

        mServerId = v.findViewById(R.id.server_id);
        mServerPrice = v.findViewById(R.id.server_price);
        mServerPaymentDate = v.findViewById(R.id.server_paymentDate);

        mServerViewModel.getServerLiveData().observe(getViewLifecycleOwner(), this::initData);

        ServerViewStateAdapter serverViewPagerAdapter = new ServerViewStateAdapter.Builder()
                .add(new ServerTabMainFragment(getResources().getString(R.string.server_tab_main)))
                .add(new ServerTabActionsFragment(getResources().getString(R.string.server_tab_action)))
                .add(new ServerTabBackupsFragment(getResources().getString(R.string.server_tab_backups)))
                .add(new ServerTabStatisticsFragment(getResources().getString(R.string.server_tab_statistics)))
                .build(getChildFragmentManager(), getLifecycle(), getArguments().getLong("ELEMENT_ID"));


        ViewPager2 viewPager = v.findViewById(R.id.view_pager);
        viewPager.setAdapter(serverViewPagerAdapter);

        TabLayout tabLayout = v.findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(serverViewPagerAdapter.getPageTitle(position))).attach();

        return v;
    }

    @SuppressLint("SetTextI18n")
    private void initData(Server server){
        mServerImageName.setText(server.getName());
        mServerIp.setText(getResources().getString(R.string.server_ip_pref) + server.getV4Ip());
        mServerStatus.setText(server.getStatus());
        mServerId.setText(getResources().getString(R.string.server_id_pref) + server.getId());
        mServerPrice.setText(server.getPaymentPrice());
        mServerPaymentDate.setText(server.getPaymentDate());
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(server.getName());
    }
}
