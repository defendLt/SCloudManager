package com.platdmit.simplecloudmanager.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.platdmit.simplecloudmanager.helpers.charts.ServerValueFormatter
import com.platdmit.domain.models.ComplexChartsData
import com.platdmit.domain.models.Statistic
import com.platdmit.domain.repo.ServerStatisticsRepo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class StatisticsViewModel
@ViewModelInject
constructor(
        private val serverStatisticsRepo: ServerStatisticsRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    val cpuDataLiveData = MutableLiveData<ComplexChartsData>()
    val ramDataLiveData = MutableLiveData<ComplexChartsData>()

    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    fun setActiveId(id: Long){
        compositeDisposable.add(
                serverStatisticsRepo.getServerStatistics(id).subscribe { generateChart(it) }
        )
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

            ramDataLiveData.postValue(
                    ComplexChartsData(
                            LineData(LineDataSet(ramEntries, "")),
                            ServerValueFormatter(titles)
                    )
            )
            cpuDataLiveData.postValue(
                    ComplexChartsData(
                            LineData(LineDataSet(cpuEntries, "")),
                            ServerValueFormatter(titles)
                    )
            )

        }.observeOn(Schedulers.newThread())
                .doOnError { it.printStackTrace() }
                .subscribe()
    }
}