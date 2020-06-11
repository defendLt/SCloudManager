package com.platdmit.simplecloudmanager.app.vm;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.platdmit.simplecloudmanager.app.helpers.charts.serverValueFormatter;
import com.platdmit.simplecloudmanager.domain.models.ComplexChartsData;
import com.platdmit.simplecloudmanager.domain.models.Statistic;
import com.platdmit.simplecloudmanager.domain.repo.ServerStatisticsRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StatisticsViewModel extends BaseViewModel {
    private static final String TAG = StatisticsViewModel.class.getSimpleName();
    private ServerStatisticsRepo mServerStatisticsRepo;

    private MutableLiveData<ComplexChartsData> mCpuDataCharts = new MutableLiveData<>();
    private MutableLiveData<ComplexChartsData> mRamDataCharts = new MutableLiveData<>();

    public StatisticsViewModel(ServerStatisticsRepo serverStatisticsRepo, long serverId) {
        mServerStatisticsRepo = serverStatisticsRepo;
        mCompositeDisposable.add(mServerStatisticsRepo.getServerStatistics(serverId).subscribe(this::generateChart));
    }

    private void generateChart(List<Statistic> statistics) {
        Completable.fromAction(() -> {
            List<Entry> CpuEntries = new ArrayList<Entry>();
            List<Entry> RamEntries = new ArrayList<Entry>();
            Map<Float, String> titles = new HashMap<>();
            for (int i = 0; i < statistics.size(); i++) {
                CpuEntries.add(new Entry(i+1, statistics.get(i).getCpuVal()));
                RamEntries.add(new Entry(i+1, statistics.get(i).getRamVal()));
                titles.put((float)i+1, statistics.get(i).getTime());
            }
            LineDataSet dataSetRam = new LineDataSet(RamEntries, "");
            mRamDataCharts.postValue(new ComplexChartsData(new LineData(dataSetRam), new serverValueFormatter(titles)));

            LineDataSet dataSetCpu = new LineDataSet(CpuEntries, "");
            mCpuDataCharts.postValue(new ComplexChartsData(new LineData(dataSetCpu), new serverValueFormatter(titles)));
        }).observeOn(Schedulers.newThread())
                .doOnError(Throwable::printStackTrace)
                .subscribe();
    }

    public LiveData<ComplexChartsData> getCpuDataLiveData() {
        return mCpuDataCharts;
    }
    public LiveData<ComplexChartsData> getRamDataLiveData() {
        return mRamDataCharts;
    }
}
