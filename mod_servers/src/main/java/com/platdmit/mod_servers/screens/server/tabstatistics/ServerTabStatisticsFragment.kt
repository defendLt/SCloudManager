package com.platdmit.mod_servers.screens.server.tabstatistics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.platdmit.mod_servers.domain.models.ComplexChartsData
import com.platdmit.mod_servers.screens.server.ServerTabFragment
import com.platdmit.simplecloudmanager.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server_tab_statistics.*

@AndroidEntryPoint
class ServerTabStatisticsFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_statistics), ServerTabFragment<ServerTabStatisticsFragment> {
    private val statisticsViewModel: StatisticsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chartStyleInit()
        cpu_chart.setScaleEnabled(false)
        ram_chart.setScaleEnabled(false)

        statisticsViewModel.statisticsStateLiveData.observe(viewLifecycleOwner, Observer { stateHandler(it) })
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
            cpu_chart.data = cpuData.lineData
            cpu_chart.xAxis.valueFormatter = cpuData.valueFormatter
            cpu_chart.invalidate()
        } catch (e: NullPointerException) {
        }
    }

    private fun updateRamData(ramData: ComplexChartsData) {
        try {
            ram_chart.data = ramData.lineData
            ram_chart.xAxis.valueFormatter = ramData.valueFormatter
            ram_chart.invalidate()
        } catch (e: NullPointerException) {
        }
    }

    private fun chartStyleInit() {
        try {
            val chartDataColor = requireContext().getColor(R.color.colorBaseLight)
            val chartDescString = resources.getString(R.string.chart_cpu_desc)

            cpu_chart.description.text = chartDescString
            cpu_chart.description.textColor = chartDataColor
            cpu_chart.legend.isEnabled = false
            cpu_chart.xAxis.textColor = chartDataColor
            cpu_chart.axisLeft.textColor = chartDataColor

            ram_chart.description.text = chartDescString
            ram_chart.description.textColor = chartDataColor
            ram_chart.legend.isEnabled = false
            ram_chart.xAxis.textColor = chartDataColor
            ram_chart.axisLeft.textColor = chartDataColor

            cpu_chart.animateXY(3000, 3000)
            ram_chart.animateXY(3000, 3000)

        } catch (e: NullPointerException) {
        }
    }
}