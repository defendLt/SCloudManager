package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.platdmit.simplecloudmanager.app.helpers.charts.ServerValueFormatter
import com.platdmit.simplecloudmanager.domain.models.ComplexChartsData
import com.platdmit.simplecloudmanager.domain.models.Statistic
import com.platdmit.simplecloudmanager.domain.repo.ServerStatisticsRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class StatisticsViewModel(
        private val mServerStatisticsRepo: ServerStatisticsRepo,
        serverId: Long
) : BaseViewModel() {
    private val mCpuDataCharts = MutableLiveData<ComplexChartsData>()
    private val mRamDataCharts = MutableLiveData<ComplexChartsData>()

    init {
        mCompositeDisposable.add(mServerStatisticsRepo.getServerStatistics(serverId).subscribe { generateChart(it) })
    }

    private fun generateChart(statistics: List<Statistic>) {

        Completable.fromAction {
            val cpuEntries: MutableList<Entry> = ArrayList()
            val ramEntries: MutableList<Entry> = ArrayList()
            val titles: MutableMap<Float, String> = HashMap()

            for ((i, v) in statistics.withIndex()) {
                cpuEntries.add(Entry((i + 1).toFloat(), v.cpuVal))
                ramEntries.add(Entry((i + 1).toFloat(), v.ramVal))
                titles[i.toFloat() + 1] = v.time
            }

            mRamDataCharts.postValue(
                    ComplexChartsData(
                            LineData(LineDataSet(ramEntries, "")),
                            ServerValueFormatter(titles)
                    )
            )
            mCpuDataCharts.postValue(
                    ComplexChartsData(
                            LineData(LineDataSet(cpuEntries, "")),
                            ServerValueFormatter(titles)
                    )
            )

        }.observeOn(Schedulers.newThread())
                .doOnError { it.printStackTrace() }
                .subscribe()
    }

    val cpuDataLiveData: LiveData<ComplexChartsData>
        get() = mCpuDataCharts

    val ramDataLiveData: LiveData<ComplexChartsData>
        get() = mRamDataCharts

    companion object {
        private val TAG = StatisticsViewModel::class.java.simpleName
    }
}