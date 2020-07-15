package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.LineChart
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.vm.StatisticsViewModel
import com.platdmit.domain.models.ComplexChartsData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server_tab_statistics.*

@AndroidEntryPoint
class ServerTabStatisticsFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_statistics), ServerTabFragment<ServerTabStatisticsFragment> {
    private val statisticsViewModel: StatisticsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            statisticsViewModel.setActiveId(requireArguments().getLong("ELEMENT_ID"))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chartStyleInit()
        cpu_chart.setScaleEnabled(false)
        ram_chart.setScaleEnabled(false)

        statisticsViewModel.cpuDataLiveData.observe(viewLifecycleOwner, Observer { updateCpuData(it) })
        statisticsViewModel.ramDataLiveData.observe(viewLifecycleOwner, Observer { updateRamData(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabStatisticsFragment {
        return ServerTabStatisticsFragment()
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