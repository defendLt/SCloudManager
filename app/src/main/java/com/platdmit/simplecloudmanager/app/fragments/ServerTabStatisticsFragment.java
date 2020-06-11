package com.platdmit.simplecloudmanager.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.vm.StatisticsViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.StatisticConvertImp;
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService;
import com.platdmit.simplecloudmanager.domain.models.ComplexChartsData;
import com.platdmit.simplecloudmanager.domain.repo.ServerStatisticsRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp;
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class ServerTabStatisticsFragment extends Fragment implements ServerTabFragment<ServerTabStatisticsFragment>{
    private static final String TAG = ServerTabStatisticsFragment.class.getSimpleName();
    private String mTitle;

    private StatisticsViewModel mStatisticsViewModel;
    private LineChart mCpuChart;
    private LineChart mRamChart;

    public ServerTabStatisticsFragment(){};

    public ServerTabStatisticsFragment(String title) {
        mTitle = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mStatisticsViewModel = new ViewModelProvider(this).get(StatisticsViewModel.class);
        } else {
            mStatisticsViewModel = new ViewModelProvider(this,
                    new SingleElementViewModelFactory<ServerStatisticsRepo>(
                            new ServerRepoImp(
                                    new ApiServerRepoImp(SCMApp.getActualApiKeyService()),
                                    SCMApp.getDb(),
                                    new StatisticConvertImp(),
                                    new ContentUpdateService(new UpdateScheduleRepImp(SCMApp.getDb()))
                            ), ServerStatisticsRepo.class, getArguments().getLong("ELEMENT_ID")
                    )).get(StatisticsViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_server_tab_statistics, container, false);

        mCpuChart = v.findViewById(R.id.cpu_chart);
        mRamChart = v.findViewById(R.id.ram_chart);

        chartStyleInit();

        mCpuChart.setScaleEnabled(false);
        mRamChart.setScaleEnabled(false);

        mStatisticsViewModel.getCpuDataLiveData().observe(getViewLifecycleOwner(), this::updateCpuData);
        mStatisticsViewModel.getRamDataLiveData().observe(getViewLifecycleOwner(), this::updateRamData);
        return v;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public ServerTabStatisticsFragment getInstance() {
        return new ServerTabStatisticsFragment();
    }

    private void updateCpuData(ComplexChartsData cpuData) {
        try {
            mCpuChart.setData(cpuData.getLineData());
            mCpuChart.getXAxis().setValueFormatter(cpuData.getValueFormatter());
            mCpuChart.invalidate();
        } catch (NullPointerException e) {
        }
    }

    private void updateRamData(ComplexChartsData ramData) {
        try {
            mRamChart.setData(ramData.getLineData());
            mRamChart.getXAxis().setValueFormatter(ramData.getValueFormatter());
            mRamChart.invalidate();
        } catch (NullPointerException e) {
        }
    }

    private void chartStyleInit() {
        try {
            int chartDataColor = getContext().getColor(R.color.colorBaseLight);
            String chartDescString = getResources().getString(R.string.chart_cpu_desc);

            mCpuChart.getDescription().setText(chartDescString);
            mRamChart.getDescription().setText(chartDescString);

            mCpuChart.getDescription().setTextColor(chartDataColor);
            mRamChart.getDescription().setTextColor(chartDataColor);

            mCpuChart.getLegend().setEnabled(false);
            mRamChart.getLegend().setEnabled(false);

            mCpuChart.getXAxis().setTextColor(chartDataColor);
            mRamChart.getXAxis().setTextColor(chartDataColor);

            mCpuChart.getAxisLeft().setTextColor(chartDataColor);
            mRamChart.getAxisLeft().setTextColor(chartDataColor);

            mCpuChart.animateXY(3000, 3000);
            mRamChart.animateXY(3000, 3000);
        } catch (NullPointerException e) {
        }
    }
}
