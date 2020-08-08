package com.platdmit.feature_servers.screens.server.tabstatistics

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.SavedStateHandle
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.platdmit.feature_base.vm.BaseViewModel
import com.platdmit.feature_servers.domain.repositories.ServerStatisticsRepo
import com.platdmit.feature_servers.screens.server.tabstatistics.helpers.ServerValueFormatter
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class StatisticsViewModel
@ViewModelInject
constructor(
        private val serverStatisticsRepo: ServerStatisticsRepo,
        @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel<StatisticsState>() {
    val statisticsStateLiveData = LiveDataReactiveStreams.fromPublisher(stateProvider)
    val messageLiveData = LiveDataReactiveStreams.fromPublisher(messageProvider)

    init {
        stateProvider.onNext(StatisticsState.Loading)
        savedStateHandle.get<Long>("ELEMENT_ID")?.let {
            setStateIntent(StateIntent.SetServerId(it))
        }
    }

    fun setStateIntent(stateIntent: StateIntent){
        when(stateIntent){
            is StateIntent.SetServerId -> {
                setActiveId(stateIntent.id)
            }
            is StateIntent.RefreshResult -> {}
        }
    }

    private fun setActiveId(id: Long){
        compositeDisposable.add(
                serverStatisticsRepo.getServerStatistics(id).subscribe ({
                    generateChart(it)
                },{
                    stateProvider.onNext(StatisticsState.Error)
                })
        )
    }

    private fun generateChart(statistics: List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Statistic>) {
        Completable.fromAction {
            val cpuEntries: MutableList<Entry> = ArrayList()
            val ramEntries: MutableList<Entry> = ArrayList()
            val titles: MutableMap<Float, String> = HashMap()

            for ((i, v) in statistics.withIndex()) {
                cpuEntries.add(Entry((i + 1).toFloat(), v.cpuVal))
                ramEntries.add(Entry((i + 1).toFloat(), v.ramVal))
                titles[i.toFloat() + 1] = v.time
            }
            stateProvider.onNext(
                    StatisticsState.Success(
                            _root_ide_package_.com.platdmit.feature_servers.domain.models.ComplexChartsData(
                                    LineData(LineDataSet(ramEntries, "")),
                                    ServerValueFormatter(titles)
                            ),
                            _root_ide_package_.com.platdmit.feature_servers.domain.models.ComplexChartsData(
                                    LineData(LineDataSet(cpuEntries, "")),
                                    ServerValueFormatter(titles)
                            )
                    )
            )
        }.observeOn(Schedulers.newThread())
                .doOnError {
                    stateProvider.onNext(StatisticsState.Error)
                    it.printStackTrace()
                }
                .subscribe()
    }

    sealed class StateIntent {
        data class SetServerId(val id: Long) : StateIntent()
        object RefreshResult : StateIntent()
    }
}