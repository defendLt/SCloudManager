package com.platdmit.simplecloudmanager.screens.server.tabstatistics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.ComplexChartsData
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.databinding.FragmentServerTabStatisticsBinding
import com.platdmit.simplecloudmanager.screens.server.ServerTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerTabStatisticsFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_statistics), ServerTabFragment<ServerTabStatisticsFragment> {
    private val statisticsViewModel: StatisticsViewModel by viewModels()
    private val statisticsViewBinding: FragmentServerTabStatisticsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chartStyleInit()
        statisticsViewBinding.cpuChart.setScaleEnabled(false)
        statisticsViewBinding.ramChart.setScaleEnabled(false)

        statisticsViewModel.statisticsStateLiveData.observe(viewLifecycleOwner, { stateHandler(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabStatisticsFragment {
        return ServerTabStatisticsFragment()
    }

    private fun stateHandler(statisticsState: StatisticsState){
        when(statisticsState){
            is StatisticsState.Loading -> {}
            is StatisticsState.Success -> {
                updateCpuData(statisticsState.cpuData)
                updateRamData(statisticsState.ramData)
            }
            is StatisticsState.Empty -> {}
            is StatisticsState.Error -> {}
        }
    }

    private fun setStateIntent(stateIntent: StatisticsViewModel.StateIntent){
        statisticsViewModel.setStateIntent(stateIntent)
    }

    private fun updateCpuData(cpuData: ComplexChartsData) {
        try {
            statisticsViewBinding.cpuChart.data = cpuData.lineData
            statisticsViewBinding.cpuChart.xAxis.valueFormatter = cpuData.valueFormatter
            statisticsViewBinding.cpuChart.invalidate()
        } catch (e: NullPointerException) {
        }
    }

    private fun updateRamData(ramData: ComplexChartsData) {
        try {
            statisticsViewBinding.ramChart.data = ramData.lineData
            statisticsViewBinding.ramChart.xAxis.valueFormatter = ramData.valueFormatter
            statisticsViewBinding.ramChart.invalidate()
        } catch (e: NullPointerException) {
        }
    }

    private fun chartStyleInit() {
        try {
            val chartDataColor = requireContext().getColor(R.color.colorBaseLight)
            val chartDescString = resources.getString(R.string.chart_cpu_desc)

            statisticsViewBinding.cpuChart.run {
                description.text = chartDescString
                description.textColor = chartDataColor
                legend.isEnabled = false
                xAxis.textColor = chartDataColor
                axisLeft.textColor = chartDataColor
                animateXY(3000, 3000)
            }

            statisticsViewBinding.ramChart.run {
                description.text = chartDescString
                description.textColor = chartDataColor
                legend.isEnabled = false
                xAxis.textColor = chartDataColor
                axisLeft.textColor = chartDataColor
                animateXY(3000, 3000)
            }
        } catch (e: NullPointerException) {
        }
    }
}